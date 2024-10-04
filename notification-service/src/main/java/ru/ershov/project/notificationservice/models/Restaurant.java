package ru.ershov.project.deliveryservice.models;

import lombok.Data;
import ru.ershov.project.deliveryservice.models.statuses.RestaurantStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Data
public class Restaurant implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private RestaurantStatus status;

    @Column(name = "coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "restaurant")
    private Set<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantMenuItem> restaurantMenuItems;

}
