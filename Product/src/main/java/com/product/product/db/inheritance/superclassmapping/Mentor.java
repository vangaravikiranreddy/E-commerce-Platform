package com.product.product.db.inheritance.superclassmapping;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "scm_mentor")
public class Mentor extends User{
    private String rating;
}
