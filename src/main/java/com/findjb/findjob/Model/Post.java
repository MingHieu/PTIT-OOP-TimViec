package com.findjb.findjob.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "expect_salary")
    private String salary;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "description", length = 5000)
    private String description;
    @Column(name = "requirement")
    private String requirement;
    @Column(name = "benefit")
    private String benefit;
    @CreationTimestamp
    private Date created_at;
    @Column(name = "expired_at")
    private Date expired_at;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;
}
