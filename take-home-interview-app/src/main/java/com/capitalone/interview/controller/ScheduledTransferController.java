
package com.capitalone.interview.controller;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.exception.ConversionException;
import com.capitalone.interview.exception.ExceptionUUIDNotFound;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import com.capitalone.interview.repository.ScheduledTransferRepository;
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




    //CREATE scheduled transfer
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/transfers")
    public CreateScheduledTransferResponse create(@RequestBody @Valid CreateScheduledTransferRequest request){


        ScheduledTransfer scheduledTransfer = scheduledTransferService.createScheduledTransfer(request);

        return CreateScheduledTransferResponse.builder().confirmationNumber(scheduledTransfer.getId()).build();

    }

    //GET scheduled transfer "/transfer/{parameter}" returns transactions where the parameter is present as
    // either To or From Account Number
    @RequestMapping(value = "/transfers/{id}", method = RequestMethod.GET)
    public List<ScheduledTransfer> getScheduledTransfersByAccountNumber(@PathVariable("id") String id){

        return scheduledTransferService.getScheduledTransfersBy(id);

    }


    //PUT/UPDATE scheduled transfer  via confirmation number -ID
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/transfers/{id}")
    public String updateScheduleTransfer(@PathVariable("id") UUID id, @RequestBody ScheduledTransfer updateTransfer) throws ConversionException {
        //prevents updating transfers to a past date
        if(id == null || updateTransfer.getId() == null){
            throw new ExceptionUUIDNotFound("Confirmation Number not found");
        }
        if(updateTransfer.getTransferDate().isAfter(LocalDate.now())) {

            scheduledTransferService.updateScheduledTransferByUUID(updateTransfer, id);
            return "Scheduled Transfer UUID :" + id + " has been updated";

        } else {
            return "Update Error, cannot edit date to be in the past";
        }

    }

    //DELETE scheduled transfer as long as the date is in the future
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/transfers/{id}")
    public String deleteScheduleTransfer(@PathVariable("id") UUID id) throws ExceptionUUIDNotFound{

            String response;
            try {

                scheduledTransferService.deleteByUUID(id);
                response ="Confirmation Number: " + id + "has been deleted";
            } catch(ExceptionUUIDNotFound e){
                throw new ExceptionUUIDNotFound("Confirmation Number " + "Not Found.");
            }
            return response;
        }


    //TODO Offer a soft delete method as alt to delete.
    //SOFT DELETE METHOD would be a good alternative to DELETE
    // via confirmation number - action 'Cancel' isCancel Boolean
    //WILL need to add additional column in table to soft delete, ALTER TABLE



}
