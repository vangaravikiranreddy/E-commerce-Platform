package com.product.product.db.inheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tpc_student")
public class Student extends User {
    private String psp;
}
