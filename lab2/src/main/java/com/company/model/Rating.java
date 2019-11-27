package com.company.model;

@Table(name = "ratings")
public class Rating {

    @Primary
    @Column(name = "rid")
    private Long id;

    @Column(name = "uid")
    private Long userId;

    @Column(name = "aid")
    private Long answerId;

    private Long quantity;

    public Rating() {}
    public Rating(Long id, Long userId, Long answerId, Long quantity) {
       this.id = id;
       this.userId = userId;
       this.answerId = answerId;
       this.quantity = quantity;
    }

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
