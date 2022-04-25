package com.capitalone.interview.service;

import com.capitalone.interview.controller.ScheduledTransferController;
import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ScheduledTransferServiceDeleteTest {


    @InjectMocks
    ScheduledTransferService scheduledTransferService;

    ScheduledTransferController scheduledTransferController;

    @Mock
    ConversionService mockedConversionService;

    @Mock
    ScheduledTransferRepository mockedScheduledTransferRepository;


    @Test
    void testHappyPathDeleteScheduledTransfer() {

        CreateScheduledTransferRequest request = CreateScheduledTransferRequest.builder()
                .toAccountNumber("123456789")
                .fromAccountNumber("987654321")
                .amount(BigDecimal.TEN)
                .memo("foobar")
                .build();

        ScheduledTransfer scheduledTransfer = ScheduledTransfer.builder()
                .toAccountNumber(request.getToAccountNumber())
                .fromAccountNumber(request.getFromAccountNumber())
                .amount(request.getAmount())
                .memo(request.getMemo())
                .build();

        when(mockedConversionService.convert(request, ScheduledTransfer.class))
                .thenReturn(scheduledTransfer);

        UUID expectedUUID = UUID.randomUUID();

        when(mockedScheduledTransferRepository.save(scheduledTransfer)).thenReturn(scheduledTransfer.toBuilder()
                .id(expectedUUID)
                .build());

        scheduledTransferController.deleteScheduleTransfer(expectedUUID);

        assertNull(scheduledTransfer.getId());


        ScheduledTransfer actual = scheduledTransferService.createScheduledTransfer(request);

        assertNotNull(actual);
        assertEquals(request.getToAccountNumber(), actual.getToAccountNumber());
        assertEquals(request.getFromAccountNumber(), actual.getFromAccountNumber());
        assertEquals(request.getAmount(), actual.getAmount());
        assertEquals(request.getMemo(), actual.getMemo());
        assertEquals(expectedUUID, actual.getId());



    }
}
