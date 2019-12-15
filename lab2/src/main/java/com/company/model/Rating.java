package com.company.model;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private Long id;

    @Column(name = "uid", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "aid", insertable = false, updatable = false)
    private Long answerId;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aid")
    private Answer answer;

    public Rating() {}
    public Rating(Long id, Long userId, Long answerId, Long quantity) {
       this.id = id;
       this.userId = userId;
       this.answerId = answerId;
       this.quantity = quantity;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
