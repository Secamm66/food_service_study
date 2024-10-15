package ru.ershov.project.common.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

}
