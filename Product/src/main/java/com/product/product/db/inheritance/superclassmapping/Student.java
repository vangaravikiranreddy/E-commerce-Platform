package com.product.product.db.inheritance.superclassmapping;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "scm_student")
public class Student extends User {
    private String psp;
}
