package ru.ershov.project.orderservice.models;

import lombok.Data;
import lombok.ToString;
import ru.ershov.project.orderservice.models.statuses.RestaurantStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Data
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Column(name = "coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantMenuItem> restaurantMenuItems;

}
