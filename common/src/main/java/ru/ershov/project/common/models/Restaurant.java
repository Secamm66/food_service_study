package ru.ershov.project.common.models;

import lombok.Data;
import lombok.ToString;
import ru.ershov.project.common.models.statuses.RestaurantStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Column(name = "coordinates")
    private String coordinates;

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantMenuItem> restaurantMenuItems;

}
