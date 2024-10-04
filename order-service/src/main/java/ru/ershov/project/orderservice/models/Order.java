package ru.ershov.project.orderservice.models;

import lombok.Data;

import org.hibernate.annotations.Cascade;
import ru.ershov.project.orderservice.models.statuses.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
