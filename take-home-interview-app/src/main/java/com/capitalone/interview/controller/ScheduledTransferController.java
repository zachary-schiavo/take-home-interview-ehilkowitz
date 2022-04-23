package com.capitalone.interview.controller;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import com.capitalone.interview.service.ScheduledTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class ScheduledTransferController {

    @Resource
    private ScheduledTransferService scheduledTransferService;
    //CREATE scheduled transfer endpoint
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/transfers")
    public CreateScheduledTransferResponse create(@RequestBody @Valid CreateScheduledTransferRequest request){

        ScheduledTransfer scheduledTransfer = scheduledTransferService.createScheduledTransfer(request);

        return CreateScheduledTransferResponse.builder().confirmationNumber(scheduledTransfer.getId()).build();

    }

    //GET scheduled transfer "/transfer/{parameter}" for account 987654321





    //PUT/UPDATE scheduled transfer allowing to update 'memo', 'start date', 'transfer amount', via confimation number
    //    @ResponseStatus(code = HttpStatus.ACCEPTED)
    //    @PostMapping("/transfers")




    //DELETE scheduled transfer as long as the date is in the future
    // @ResponseStatus(code = HttpStatus.ACCEPTED)
    // @PostMapping("/transfers")


    //SOFT DELETE for transfer via confirmation number - action 'Cancel' isCancel Boolean
    //WILL need to add additional column in table to soft delete

}
