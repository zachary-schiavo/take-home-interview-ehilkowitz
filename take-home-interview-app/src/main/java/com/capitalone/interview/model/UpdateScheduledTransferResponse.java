package com.capitalone.interview.model;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

//DID NOT USE UPDATE RESPONSE

@Data
@Builder
public class UpdateScheduledTransferResponse {

    private UUID confirmationNumber;

}
