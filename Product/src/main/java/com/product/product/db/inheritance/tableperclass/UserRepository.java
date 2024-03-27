package com.product.product.db.inheritance.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_userrepository")
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);
}
