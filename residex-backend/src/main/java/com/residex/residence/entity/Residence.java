package com.residex.residence.entity;

import com.residex.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "residences")
public class Residence extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String block;

    private String address;

    @Column(nullable = false)
    private Double feePerDay;
}