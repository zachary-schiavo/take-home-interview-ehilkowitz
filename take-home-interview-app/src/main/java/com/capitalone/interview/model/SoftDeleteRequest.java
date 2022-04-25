package com.capitalone.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


//Soft Delete Request Option would switch a newly added Column(isDeleted)
//Would need to ALTER Table to add additional column. And once isDeleted is true you would only it would be omitted
//from CRUD operations but would remind in (name="scheduled_transfers") TABLE for records

public class SoftDeleteRequest {

    private UUID confirmationNumber;

    private boolean isDeleted;
}



