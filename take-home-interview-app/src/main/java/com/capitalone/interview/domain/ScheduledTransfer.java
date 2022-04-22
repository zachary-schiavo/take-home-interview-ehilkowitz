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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name="scheduled_transfers")
public class ScheduledTransfer {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name ="transfer_date")
    private LocalDate transferDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "toAccountNumber")
    private String toAccountNumber;

    @Column(name = "fromAccountNumber")
    private String fromAccountNumber;

    @Column(name = "memo")
    private String memo;

}
