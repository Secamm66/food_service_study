package ru.ershov.project.deliveryservice.models;

import lombok.Data;
import ru.ershov.project.deliveryservice.models.statuses.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private OrderStatus status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restourant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems;

}
