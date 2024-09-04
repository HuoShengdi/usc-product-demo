package com.usc.productDemo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sp_order")
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "SP_ORDER_SEQ")
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"password","profiles","enabled","credentialsNonExpired","accountNonExpired","accountNonLocked","authorities"})
    private User user;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> purchases;
    
    @Column(nullable = false)
    private Date purchasingDate;

    public Order() {
    }

    public Order(int id, User user, List<OrderProduct> purchases, Date purchasingDate) {
        this.id = id;
        this.user = user;
        this.purchases = purchases;
        this.purchasingDate = purchasingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderProduct> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<OrderProduct> purchases) {
        this.purchases = purchases;
    }

    public Date getPurchasingDate() {
        return purchasingDate;
    }

    public void setPurchasingDate(Date purchasingDate) {
        this.purchasingDate = purchasingDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", purchasedProducts=" + purchases +
                ", purchasingDate=" + purchasingDate +
                '}';
    }
}