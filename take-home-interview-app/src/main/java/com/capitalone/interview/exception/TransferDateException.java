package com.capitalone.interview.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDateException extends RuntimeException {

    private String message = "Update cannot occur, Transfer Date is in past";


}
