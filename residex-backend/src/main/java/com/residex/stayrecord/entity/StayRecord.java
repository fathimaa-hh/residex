package com.residex.stayrecord.entity;

import com.residex.common.entity.BaseEntity;
import com.residex.student.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "stay_records")
@Getter
@Setter
public class StayRecord extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Integer month;

    private Integer year;

    private Integer stayedDays;

    private Integer approvedLeaveDays;

    private Integer unauthorizedAbsenceDays;

    private Integer totalDays;
}