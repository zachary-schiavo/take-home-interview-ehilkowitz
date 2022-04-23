package com.capitalone.interview.stepdefinitions;

import com.capitalone.interview.model.CreateScheduledTransferRequest;
import com.capitalone.interview.model.CreateScheduledTransferResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.net.URI;

import static org.junit.Assert.*;

public class CreateScheduledTransfers {

    private CreateScheduledTransferRequest scheduledTransferRequest;
    private ResponseEntity<CreateScheduledTransferResponse> response;

    private final String uri = "http://localhost:8080/transfers";
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();


    @Given("^a user wants to schedule a transfer$")
    public void aUserWantsToScheduleATransfer() {
        scheduledTransferRequest = CreateScheduledTransferRequest.builder().build();
    }

    @And("^the transfer has a to account number of (.*)$")
    public void theTransferHasATo_account_number(String toAccountNumber) {
        scheduledTransferRequest.setToAccountNumber(toAccountNumber);
    }

    @And("^the transfer has a from account number of (.*)$")
    public void theTransferHasAFrom_account_number(String fromAccountNumber) {
        scheduledTransferRequest.setFromAccountNumber(fromAccountNumber);
    }

    @And("^the transfer has a transfer date of (.*)$")
    public void theTransferHasATransferDateOfTransfer_date(String transferDate) {
        scheduledTransferRequest.setTransferDate(transferDate);
    }

    @And("^the transfer has an amount of (.*)$")
    public void theTransferHasAnAmount(BigDecimal amount) {
        scheduledTransferRequest.setAmount(amount);
    }

    @And("^the transfer has a memo of (.*)$")
    public void theTransferHasAnAmount(String memo) {
        scheduledTransferRequest.setMemo(memo);
    }

    @When("^the user schedules the transfer$")
    public void theUserSchedulesTheTransfer() {
        response = testRestTemplate.postForEntity(uri, scheduledTransferRequest, CreateScheduledTransferResponse.class);
    }

    @Then("^the response is successful$")
    public void theResponseIsSuccessful() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        CreateScheduledTransferResponse createScheduledTransferResponse = response.getBody();
        assertNotNull(createScheduledTransferResponse);
        assertNotNull(createScheduledTransferResponse.getConfirmationNumber());
    }

    @Then("^the response is unsuccessful$")
    public void theResponseIsUnsuccessful() {
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}