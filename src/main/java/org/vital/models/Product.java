package org.vital.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @Column(name = "product_name")
    @NotEmpty(message = "Product Name should not be empty")
    @Size(min = 2, max = 30, message = "Product Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "product_price")
    /*@NotEmpty(message = "Price should not be empty")*/
    private double price;

    @Column(name = "amount")
   /* @NotEmpty(message = "Amount should not be empty")*/
    private int amount;

    public Product() {

    }

    public Product(int id, String name, double price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        order.setProduct(this);
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
