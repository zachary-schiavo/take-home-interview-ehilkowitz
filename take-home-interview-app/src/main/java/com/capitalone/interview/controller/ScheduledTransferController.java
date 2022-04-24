package com.capitalone.interview.controller;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import com.capitalone.interview.repository.ScheduledTransferUpdateRepository;
import com.capitalone.interview.service.ScheduledTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class ScheduledTransferController {

    @Resource
     private ScheduledTransferService scheduledTransferService;

     private ScheduledTransferRepository scheduledTransferRepository;

     private ScheduledTransferUpdateRepository updateRepository;

    //CREATE scheduled transfer endpoint
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/transfers")
    public CreateScheduledTransferResponse create(@RequestBody @Valid CreateScheduledTransferRequest request){


            ScheduledTransfer scheduledTransfer = scheduledTransferService.createScheduledTransfer(request);

            return CreateScheduledTransferResponse.builder().confirmationNumber(scheduledTransfer.getId()).build();

    }

//    TODO GET findall
//    GET scheduled transfer "/transfer/{parameter}" for account 987654321 which return all 3 transfers
    @RequestMapping(value = "/transfers/{id}", method = RequestMethod.GET)
    public List<ScheduledTransfer> getScheduledTransfersByAccountNumber(@PathVariable("id") String id){

            return scheduledTransferService.getScheduledTransfersBy(id);

    }



    //TODO Update scheduled transfer memo, start date, and amount via confirmation number
    //PUT/UPDATE scheduled transfer allowing to update 'memo', 'start date', 'transfer amount', via confimation number -ID
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/transfers/{id}")
    public ScheduledTransfer updateScheduleTransfer(@PathVariable("id") UUID id, @Valid @RequestBody ScheduledTransfer updateTransfer){

        if(updateTransfer.getTransferDate().isAfter(LocalDate.now())) {

            System.out.println("Scheduled Transfer UUID :" + id + " has been updated");
            return scheduledTransferService.updateScheduledTransferByUUID(updateTransfer, id);
        }
        return null;
    }



    //TODO delete schedule transfers
    //DELETE scheduled transfer as long as the date is in the future
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/transfers/delete/{id}")
    public String deleteScheduleTransfer(@PathVariable("id") UUID id){

       scheduledTransferService.deleteByUUID(id);

       return "Scheduled Transfer UUID :" + id + " has been deleted";

    }


    //TODO Offer a soft delete method as alt to delete.
    //SOFT DELETE for transfer via confirmation number - action 'Cancel' isCancel Boolean
    //WILL need to add additional column in table to soft delete, ALTER TABLE

}
