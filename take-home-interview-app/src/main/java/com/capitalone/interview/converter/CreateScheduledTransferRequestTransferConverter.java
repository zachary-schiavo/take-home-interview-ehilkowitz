package com.capitalone.interview.converter;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateScheduledTransferRequestTransferConverter implements Converter<CreateScheduledTransferRequest, ScheduledTransfer> {

    @Override
    public ScheduledTransfer convert(CreateScheduledTransferRequest source) {
        return ScheduledTransfer.builder()
                .toAccountNumber(source.getToAccountNumber())
                .fromAccountNumber(source.getFromAccountNumber())
                .amount(source.getAmount())
                .memo(source.getMemo())
                .transferDate(source.getTransferDate())
                .build();
    }
}
