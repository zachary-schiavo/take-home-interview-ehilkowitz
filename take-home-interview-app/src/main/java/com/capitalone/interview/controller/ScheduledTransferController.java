package com.capitalone.interview.controller;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ExceptionUUIDNotFound;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import com.capitalone.interview.model.DeleteScheduledTransferResponse;
import com.capitalone.interview.model.UpdateScheduledTransferResponse;
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
import java.util.concurrent.ExecutionException;

@RestController
public class ScheduledTransferController {

    @Resource
     private ScheduledTransferService scheduledTransferService;

     private ScheduledTransferRepository scheduledTransferRepository;

     private ScheduledTransferUpdateRepository updateRepository;


    //CREATE scheduled transfer
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/transfers")
    public CreateScheduledTransferResponse create(@RequestBody @Valid CreateScheduledTransferRequest request){


            ScheduledTransfer scheduledTransfer = scheduledTransferService.createScheduledTransfer(request);

            return CreateScheduledTransferResponse.builder().confirmationNumber(scheduledTransfer.getId()).build();

    }


    //GET scheduled transfer "/transfer/{parameter}" for account 987654321 which return all 3 transfers
    @RequestMapping(value = "/transfers/{id}", method = RequestMethod.GET)
    public List<ScheduledTransfer> getScheduledTransfersByAccountNumber(@PathVariable("id") String id){

            return scheduledTransferService.getScheduledTransfersBy(id);

    }




    //PUT/UPDATE scheduled transfer allowing to update 'memo', 'start date', 'transfer amount', via confimation number -ID
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/transfers/{id}")
    public String updateScheduleTransfer(@PathVariable("id") UUID id, @Valid @RequestBody ScheduledTransfer updateTransfer){
    //prevents updating transfers that are passed date
        if(updateTransfer.getTransferDate().isAfter(LocalDate.now())) {


            scheduledTransferService.updateScheduledTransferByUUID(updateTransfer, id);
            return "Scheduled Transfer UUID :" + id + " has been updated";

        }if(updateTransfer.getTransferDate().isBefore(LocalDate.now())){
            return "Update error, you cannot update a transfer date that has already passed";

        } else {
            return "Update Error";
        }

    }




    //DELETE scheduled transfer as long as the date is in the future
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/transfers/{id}")
    public String deleteScheduleTransfer(@PathVariable("id") UUID id){

        String response = null;
        try {

            scheduledTransferService.deleteByUUID(id);
            response ="Confirmation Number: " + id + "has been deleted";
        } catch(ExceptionUUIDNotFound e){
            response = "Confirmation Number Not Found";
        }
        return response;
    }


    //TODO Offer a soft delete method as alt to delete.
    //SOFT DELETE for transfer via confirmation number - action 'Cancel' isCancel Boolean
    //WILL need to add additional column in table to soft delete, ALTER TABLE

}
