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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
class ScheduledTransferControllerTest {

    @InjectMocks
    ScheduledTransferController scheduledTransferController;

    @Mock
    ScheduledTransferService mockedScheduledTransferService;

    @Autowired
    MockMvc mockMvc;



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

    @Test
    public void getScheduledTransfersByAccountNumber_success() throws Exception{

        CreateScheduledTransferRequest testRequest_1 = CreateScheduledTransferRequest.builder()
                .amount(BigDecimal.ONE)
                .fromAccountNumber("123456789")
                .toAccountNumber("987654321")
                .memo("Savings")
                .build();

        UUID expectedId = UUID.randomUUID();
        ScheduledTransfer scheduledTransfer_1 = ScheduledTransfer.builder()
                .amount(testRequest_1.getAmount())
                .fromAccountNumber(testRequest_1.getFromAccountNumber())
                .toAccountNumber(testRequest_1.getToAccountNumber())
                .id(expectedId)
                .build();

        CreateScheduledTransferRequest testRequest_2 = CreateScheduledTransferRequest.builder()
                .amount(BigDecimal.TEN)
                .fromAccountNumber("123456789")
                .toAccountNumber("987654321")
                .memo("Savings_2")
                .build();

        UUID expectedId_2 = UUID.randomUUID();
        ScheduledTransfer scheduledTransfer_2 = ScheduledTransfer.builder()
                .amount(testRequest_2.getAmount())
                .fromAccountNumber(testRequest_2.getFromAccountNumber())
                .toAccountNumber(testRequest_2.getToAccountNumber())
                .id(expectedId_2)
                .build();



        List<ScheduledTransfer> transferList = new ArrayList<>(Arrays.asList(scheduledTransfer_1, scheduledTransfer_2));

        List<ScheduledTransfer> actualList = new ArrayList<>();

        actualList = mockedScheduledTransferService.getScheduledTransfersBy("123456789");

        when(mockedScheduledTransferService.getScheduledTransfersBy((scheduledTransfer_1).getToAccountNumber()))
                .thenReturn(transferList);


//      HAVING TROUBLE IMPLEMENTING UNIT TESTING
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/transfers/987654321")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", notNullValue()));



        assertEquals(transferList, actualList);

    }


}