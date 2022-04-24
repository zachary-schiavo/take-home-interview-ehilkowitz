package com.capitalone.interview.controller;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import com.capitalone.interview.repository.ScheduledTransferRepository;
import com.capitalone.interview.service.ScheduledTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduledTransferController {

    @Resource
    private ScheduledTransferService scheduledTransferService;

//    private ScheduledTransferRepository scheduledTransferRepository;

    //CREATE scheduled transfer endpoint
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/transfers")
    public CreateScheduledTransferResponse create(@RequestBody @Valid CreateScheduledTransferRequest request){

        ScheduledTransfer scheduledTransfer = scheduledTransferService.createScheduledTransfer(request);

        return CreateScheduledTransferResponse.builder().confirmationNumber(scheduledTransfer.getId()).build();

    }
    //TODO GET findall
    //GET scheduled transfer "/transfer/{parameter}" for account 987654321
//    @RequestMapping(value = "/transfers", method = RequestMethod.GET)
//    public List<ScheduledTransfer> getScheduledTransfersByAccountNumber(String toAccountNumber){
//
//        List<ScheduledTransfer> scheduledTransfers = new ArrayList<>();
//
//        return scheduledTransfers;
//
//    }



    //TODO Update scheduled transfer memo, start date, and amount via confirmation number
    //PUT/UPDATE scheduled transfer allowing to update 'memo', 'start date', 'transfer amount', via confimation number -ID
    //    @ResponseStatus(code = HttpStatus.ACCEPTED)
    //    @PutMapping("/transfers/{id}")



    //TODO delete schedule transfers
    //DELETE scheduled transfer as long as the date is in the future
    // @ResponseStatus(code = HttpStatus.ACCEPTED)
    // @DeleteMapping("/transfers/{id}")


    //TODO Offer a soft delete method as alt to delete.
    //SOFT DELETE for transfer via confirmation number - action 'Cancel' isCancel Boolean
    //WILL need to add additional column in table to soft delete, ALTER TABLE

}
