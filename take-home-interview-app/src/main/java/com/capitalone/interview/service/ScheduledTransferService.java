package com.capitalone.interview.service;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ConversionException;
import com.capitalone.interview.exception.ExceptionUUIDNotFound;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import com.capitalone.interview.repository.ScheduledTransferUpdateRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
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

    @Resource
    ScheduledTransferUpdateRepository updateRepository;

    public ScheduledTransfer createScheduledTransfer(CreateScheduledTransferRequest request) {


            ScheduledTransfer convertedTransfer =
                    Optional.ofNullable(conversionService.convert(request, ScheduledTransfer.class))
                            .orElseThrow(ConversionException::new);

            return   transfersRepository.save(convertedTransfer);

    }


    public List<ScheduledTransfer> getScheduledTransfersBy(String id){

        return transfersRepository.getByToAccountNumberOrFromAccountNumber(id, id);


    }


    public ScheduledTransfer updateScheduledTransferByUUID(ScheduledTransfer updateTransfer, UUID id) {

            return transfersRepository.save(updateTransfer);

    }



//    public ScheduledTransfer findScheduledTransferByUUID(UUID id){
//        return updateRepository.findByUUID(id);
//    }


    //ADD logic for not allowing past date scheduled transfers from being deleted.

    public void deleteByUUID(UUID id) throws ExceptionUUIDNotFound {
        //logic for date

            updateRepository.deleteById(id);


    }

}
