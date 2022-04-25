package com.capitalone.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

//JPA Entity Class

//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder(toBuilder = true)
//@Entity
//@Table(name="scheduled_transfers")
////Entity to update scheduled_transfer table
//public class UpdateScheduledTransfer {
//
//
//    @Column(name = "transfer_date")
//    private LocalDate transferDate;
//
//    @Column(name = "amount")
//    private BigDecimal amount;
//
//    @Column(name = "memo")
//    private String memo;
//
//}
