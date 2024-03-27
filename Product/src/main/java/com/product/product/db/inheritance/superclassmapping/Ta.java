package com.product.product.db.inheritance.superclassmapping;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "scm_ta")
public class Ta extends User{
    private String sessionTime;
}
