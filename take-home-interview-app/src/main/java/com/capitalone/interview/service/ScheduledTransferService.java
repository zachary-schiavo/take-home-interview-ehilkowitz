package com.capitalone.interview.service;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ConversionException;
import com.capitalone.interview.exception.ExceptionUUIDNotFound;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduledTransferService {


    @Resource
    ScheduledTransferRepository transfersRepository;

    @Resource
    ConversionService conversionService;



    public ScheduledTransfer createScheduledTransfer(CreateScheduledTransferRequest request) {


        ScheduledTransfer convertedTransfer =
                Optional.ofNullable(conversionService.convert(request, ScheduledTransfer.class))
                        .orElseThrow(ConversionException::new);

        return   transfersRepository.save(convertedTransfer);

    }

    //will return List<scheduledtransfers> if ToAccount OR FromAccount match
    public List<ScheduledTransfer> getScheduledTransfersBy(String id){

        return transfersRepository.getByToAccountNumberOrFromAccountNumber(id, id);


    }

    //Update Scheduled Transfer
    public ScheduledTransfer updateScheduledTransferByUUID(ScheduledTransfer updateTransfer, UUID id) {


        return transfersRepository.save(updateTransfer);

    }


    //Delete by UUID
    public void deleteByUUID(UUID id) throws ExceptionUUIDNotFound {

        transfersRepository.deleteById(id);


    }

}

