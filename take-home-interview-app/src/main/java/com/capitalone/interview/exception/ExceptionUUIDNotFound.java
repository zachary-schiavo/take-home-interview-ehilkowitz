package com.capitalone.interview.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionUUIDNotFound extends RuntimeException{

    private String message = "UUID Not Found.";


}
