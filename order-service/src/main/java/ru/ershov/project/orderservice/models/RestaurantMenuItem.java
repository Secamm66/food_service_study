package ru.ershov.project.orderservice.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurant_menu_item")
@Data
public class RestaurantMenuItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String imagePath;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurantMenuItem")
    private List<OrderItem> orderItems;
}
