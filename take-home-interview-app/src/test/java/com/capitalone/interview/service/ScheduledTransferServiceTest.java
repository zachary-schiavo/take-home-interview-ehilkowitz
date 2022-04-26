package com.capitalone.interview.service;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ConversionException;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class ScheduledTransferServiceTest {

    @InjectMocks
    ScheduledTransferService scheduledTransferService;

    @Mock
    ConversionService mockedConversionService;

    @Mock
    ScheduledTransferRepository mockedScheduledTransferRepository;



    @Test
    void testHappyPathCreateScheduledTransfer() {

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

        ScheduledTransfer actual = scheduledTransferService.createScheduledTransfer(request);

        assertNotNull(actual);
        assertEquals(request.getToAccountNumber(), actual.getToAccountNumber());
        assertEquals(request.getFromAccountNumber(), actual.getFromAccountNumber());
        assertEquals(request.getAmount(), actual.getAmount());
        assertEquals(request.getMemo(), actual.getMemo());
        assertEquals(expectedUUID, actual.getId());
    }

    @Test
    void testConversionException() {
        CreateScheduledTransferRequest request = CreateScheduledTransferRequest.builder()
                .toAccountNumber("123456789")
                .fromAccountNumber("987654321")
                .amount(BigDecimal.TEN)
                .memo("foobar")
                .build();

        String expectedErrorMessage = "Conversion failed";

        when(mockedConversionService.convert(request, ScheduledTransfer.class)).thenReturn(null);

        ConversionException thrown = assertThrows(
                ConversionException.class,
                () -> scheduledTransferService.createScheduledTransfer(request),
                "Expected scheduledTransferService.createScheduledTransfer(request) to throw a ConversionException " +
                        "but it did not.");

        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void testGetSuccessByAccountNumberTo() {
        //create scheduled transfer to test Get Method

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

        ScheduledTransfer actual = scheduledTransferService.createScheduledTransfer(request);

       List<ScheduledTransfer> testList = new ArrayList<>();


       testList = mockedScheduledTransferRepository.getByToAccountNumberOrFromAccountNumber("123456789","987654321");

       assertNotNull(testList);

    }

    @Test
    void testHappyPathDelete_Success() throws Exception{
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

        ScheduledTransfer actual = scheduledTransferService.createScheduledTransfer(request);

        assertNotNull(actual);
        assertEquals(request.getToAccountNumber(), actual.getToAccountNumber());
        assertEquals(request.getFromAccountNumber(), actual.getFromAccountNumber());
        assertEquals(request.getAmount(), actual.getAmount());
        assertEquals(request.getMemo(), actual.getMemo());
        assertEquals(expectedUUID, actual.getId());

        when(mockedScheduledTransferRepository.getById(expectedUUID))
                .thenReturn(scheduledTransfer);

//        when(mockedScheduledTransferRepository.deleteById(expectedUUID))
//                .then()

        }


    }