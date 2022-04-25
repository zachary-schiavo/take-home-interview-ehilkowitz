package com.capitalone.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

//DID NOT USE THE UPDATE REQUEST
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateScheduledTransferRequest {

    @NotNull(message = "Transfer amount cannot be empty.")
    @Min(value = 0, message = "Transfer amount must be greater than 0.")
    @Max(value = 1000000, message = "Transfer must be less that $1,000,000.00")
    private BigDecimal amount;

    @Size(max = 10, message = "Memo must not be larger than 10 characters.")
    private String memo;

    @NotNull(message = "Transfer date cannot be empty")
    @Future(message = "Transfer date must be in the future")
    private LocalDate transferDate;


}
