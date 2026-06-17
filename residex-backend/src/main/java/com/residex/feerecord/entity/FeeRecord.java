package com.residex.feerecord.entity;

import com.residex.common.entity.BaseEntity;
import com.residex.student.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fee_records")
public class FeeRecord extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Integer month;

    private Integer year;

    private Double baseFee;

    private Double deductions;

    private Double penalties;

    private Double finalAmount;

    private Boolean paid;
}
