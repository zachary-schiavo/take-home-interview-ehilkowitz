package com.capitalone.interview.controller;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ConversionException;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import com.capitalone.interview.service.ScheduledTransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ScheduledTransferControllerTest {

    @InjectMocks
    ScheduledTransferController scheduledTransferController;

    @Mock
    ScheduledTransferService mockedScheduledTransferService;

    @Test
    void createSuccess() {

        CreateScheduledTransferRequest testRequest = CreateScheduledTransferRequest.builder()
                .amount(BigDecimal.ONE)
                .fromAccountNumber("123456789")
                .toAccountNumber("987654321")
                .memo("Savings")
                .build();

        UUID expectedId = UUID.randomUUID();
        ScheduledTransfer scheduledTransfer = ScheduledTransfer.builder()
                .amount(testRequest.getAmount())
                .fromAccountNumber(testRequest.getFromAccountNumber())
                .toAccountNumber(testRequest.getToAccountNumber())
                .id(expectedId)
                .build();

        when(mockedScheduledTransferService.createScheduledTransfer(testRequest)).thenReturn(scheduledTransfer);

        CreateScheduledTransferResponse actualResponse = scheduledTransferController.create(testRequest);

        assertEquals(expectedId, actualResponse.getConfirmationNumber());
    }

    @Test
    void createGetSuccess() {
        //create scheduled transfer to test Get Method
        CreateScheduledTransferRequest testRequest = CreateScheduledTransferRequest.builder()
                .amount(BigDecimal.ONE)
                .fromAccountNumber("123456789")
                .toAccountNumber("987654321")
                .memo("Savings")
                .build();

        UUID expectedId = UUID.randomUUID();
        ScheduledTransfer scheduledTransfer = ScheduledTransfer.builder()
                .amount(testRequest.getAmount())
                .fromAccountNumber(testRequest.getFromAccountNumber())
                .toAccountNumber(testRequest.getToAccountNumber())
                .id(expectedId)
                .build();

        when(mockedScheduledTransferService.createScheduledTransfer(testRequest)).thenReturn(scheduledTransfer);

        CreateScheduledTransferResponse actualResponse = scheduledTransferController.create(testRequest);

        assertEquals(expectedId, actualResponse.getConfirmationNumber());

        List<ScheduledTransfer> testScheduledTransfer = scheduledTransferController.getScheduledTransfersByAccountNumber("123456789");

        assertNotNull(testScheduledTransfer);

    }

    @Test
    void createGetFailure(){
        //create scheduled transfer to test Get Method failure when AccountNumber is wrong
        CreateScheduledTransferRequest testRequest = CreateScheduledTransferRequest.builder()
                .amount(BigDecimal.ONE)
                .fromAccountNumber("123456789")
                .toAccountNumber("987654321")
                .memo("Savings")
                .build();

        UUID expectedId = UUID.randomUUID();
        ScheduledTransfer scheduledTransfer = ScheduledTransfer.builder()
                .amount(testRequest.getAmount())
                .fromAccountNumber(testRequest.getFromAccountNumber())
                .toAccountNumber(testRequest.getToAccountNumber())
                .id(expectedId)
                .build();

        when(mockedScheduledTransferService.createScheduledTransfer(testRequest)).thenReturn(scheduledTransfer);

        CreateScheduledTransferResponse actualResponse = scheduledTransferController.create(testRequest);

        assertEquals(expectedId, actualResponse.getConfirmationNumber());

        //expected empty List
        List<ScheduledTransfer> expected = new ArrayList<>();

        //actual
        List<ScheduledTransfer> testScheduledTransfer = scheduledTransferController.getScheduledTransfersByAccountNumber("");

        assertEquals(expected, testScheduledTransfer);


    }

    @Test
    void createFailure() {
        CreateScheduledTransferRequest testRequest = CreateScheduledTransferRequest.builder()
                .amount(BigDecimal.ONE)
                .fromAccountNumber("123456789")
                .toAccountNumber("987654321")
                .memo("Savings")
                .build();

        String expectedErrorMessage = "Conversion failed";

        when(mockedScheduledTransferService.createScheduledTransfer(testRequest))
                .thenThrow(new ConversionException(expectedErrorMessage));

        ConversionException thrown = assertThrows(
                ConversionException.class,
                () -> scheduledTransferController.create(testRequest),
                "Expected scheduledTransferController.create(testRequest) to throw a ConversionException " +
                        "but it did not.");

        assertEquals(expectedErrorMessage, thrown.getMessage());
    }
}