package com.product.product.db.inheritance.join;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "j_ta")
public class Ta extends User {
    private String sessionTime;
}
