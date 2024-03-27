package com.authenticate.authservice.services;

import com.authenticate.authservice.dtos.UserDto;
import com.authenticate.authservice.models.Session;
import com.authenticate.authservice.models.SessionStatus;
import com.authenticate.authservice.models.User;
import com.authenticate.authservice.repositories.SessionRepository;
import com.authenticate.authservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AuthService {

    private UserRepository userRepository;

    private SessionRepository sessionRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Create a test key suitable for the desired HMAC-SHA algorithm:
    private  MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256

    @Value("${mysecret.key}")
    private  String key;

    public AuthService(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           SessionRepository sessionRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    private SecretKey generateSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
        // rebuild key using SecretKeySpec
        SecretKey secretKey = new SecretKeySpec(decodedKey, "HmacSHA256");
        return secretKey;
    }

    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password,user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }
        if (user.getSessions().size() == 2) {
            throw new RuntimeException("User Limit exceeded");
        }
        UserDto userDto = UserDto.from(user);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (1 * 24 * 60 * 60 * 1000)); // Convert days to milliseconds


        //JSON -> Key : Value
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("email", user.getEmail());
        jsonMap.put("roles", List.of(user.getRoles()));
        jsonMap.put("createdAt", new Date());
        jsonMap.put("expiryAt", expiryDate);

        SecretKey secretKey = generateSecretKey();

        // Create the compact JWS:
        String jws = Jwts.builder()
                .claims(jsonMap)
                .signWith(secretKey, alg)
                .compact();

        Session session = new Session();
        session.setToken(jws);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setExpirngAt(expiryDate);
        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token="+jws);

        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);
        return response;
    }


    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (optionalSession.isEmpty()) {
            return null;
        }

        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }


    public UserDto signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User userSaved = userRepository.save(user);
        return UserDto.from(userSaved);
    }


    public SessionStatus validate(String token) {
        try {
            // Decode and verify the token
            SecretKey secretKey = generateSecretKey();
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            String email = (String) claimsJws.getBody().get("email");
            List<String> roles = (List<String>) claimsJws.getBody().get("roles");

            System.out.println(email+"  "+roles);

            Optional<Session> optionalSession = sessionRepository.findByToken(token);
            if (optionalSession.isEmpty()) {
                return null;
            }
            Session session = optionalSession.get();
            if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
                return SessionStatus.ENDED;
            }
            Date currentTime = new Date();
            if (session.getExpirngAt().before(currentTime)) {
                return SessionStatus.ENDED;
            }
            return session.getSessionStatus();
        }catch (Exception e) {
           return null;
        }
    }

}