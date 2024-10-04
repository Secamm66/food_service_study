package ru.ershov.project.deliveryservice.models;

import lombok.Data;
import ru.ershov.project.deliveryservice.models.statuses.CourierStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "courier")
@Data
public class Courier implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private CourierStatus status;

    @Column(name = "coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "courier")
    private Set<Order> orders;

}
