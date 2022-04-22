package com.capitalone.interview.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateScheduledTransferRequest {

    private String toAccountNumber;
    private String fromAccountNumber;
    private String transferDate;
    private BigDecimal amount;
    private String memo;

}
