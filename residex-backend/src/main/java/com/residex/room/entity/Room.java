package com.residex.room.entity;

import com.residex.common.entity.BaseEntity;
import com.residex.residence.entity.Residence;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    @Column(nullable = false)
    private String roomNumber;

    private Integer floorNumber;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    private Residence residence;
}