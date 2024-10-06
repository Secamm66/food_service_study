package ru.ershov.project.orderservice.models;

import lombok.Data;
import ru.ershov.project.orderservice.models.statuses.CourierStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "courier")
@Data
public class Courier implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    @Column(name = "coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Order> orders;

}
