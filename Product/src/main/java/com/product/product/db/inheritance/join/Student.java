package com.product.product.db.inheritance.join;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "j_student")
public class Student extends User {
    private String psp;
}
