package com.threepounds.caseproject.data.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;



import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Entity(name = "advert")
public class Advert {
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
   private boolean active;

    @Column
    @UpdateTimestamp
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Column
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date lastUpdated;
    @Column
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
    public Advert(){

    }

    public UUID getId() {
        return id;
    }

    public Advert(UUID id, String title, String description, boolean active, Date date, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.active = active;
        this.date = date;
        this.price = price;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
