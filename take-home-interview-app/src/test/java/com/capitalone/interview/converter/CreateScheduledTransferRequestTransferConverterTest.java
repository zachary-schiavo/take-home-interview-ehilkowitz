package com.capitalone.interview.converter;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class CreateScheduledTransferRequestTransferConverterTest {

    @InjectMocks
    private CreateScheduledTransferRequestTransferConverter converter;

    @Test
    void testHappyPathConvert(){

        CreateScheduledTransferRequest expected = CreateScheduledTransferRequest.builder()
                .toAccountNumber("123456789")
                .fromAccountNumber("987654321")
                .amount(BigDecimal.TEN)
                .memo("foobar")
                .build();

        ScheduledTransfer actual = converter.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getFromAccountNumber(), actual.getFromAccountNumber());
        assertEquals(expected.getToAccountNumber(), actual.getToAccountNumber());
        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getMemo(), actual.getMemo());
    }

    @Test
    void testHappyPathConvertWithNoMemo(){

        CreateScheduledTransferRequest expected = CreateScheduledTransferRequest.builder()
                .toAccountNumber("123456789")
                .fromAccountNumber("987654321")
                .amount(BigDecimal.TEN)
                .memo(null)
                .build();

        ScheduledTransfer actual = converter.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getFromAccountNumber(), actual.getFromAccountNumber());
        assertEquals(expected.getToAccountNumber(), actual.getToAccountNumber());
        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getMemo(), actual.getMemo());
    }

}