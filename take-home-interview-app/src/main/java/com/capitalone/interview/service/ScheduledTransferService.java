package com.capitalone.interview.service;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ConversionException;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduledTransferService {

    private UUID id;
    private String toAccountNumber;
    private String fromAccountNumber;

    @Resource
    ScheduledTransferRepository transfersRepository;

    @Resource
    ConversionService conversionService;

    public ScheduledTransfer createScheduledTransfer(CreateScheduledTransferRequest request) {

        ScheduledTransfer convertedTransfer =
                Optional.ofNullable(conversionService.convert(request, ScheduledTransfer.class))
                .orElseThrow(ConversionException::new);

        return transfersRepository.save(convertedTransfer);

    }

    public ScheduledTransfer getScheduledTransferbyUUID(){

        return transfersRepository.findByUUID(id);
    }

    public List<ScheduledTransfer> getScheduledTransfersBy(){

        return transfersRepository.getByToAccountNumberOrFromAccountNumber(toAccountNumber, fromAccountNumber);

    }

    public void deleteByUUID(UUID id){

         transfersRepository.deleteById(id);
    }

}
