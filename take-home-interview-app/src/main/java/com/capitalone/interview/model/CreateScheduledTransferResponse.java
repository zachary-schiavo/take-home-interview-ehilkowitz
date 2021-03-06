package com.capitalone.interview.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateScheduledTransferResponse {

    private UUID confirmationNumber;

}
