package com.capitalone.interview.repository;

import com.capitalone.interview.domain.ScheduledTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransfer, Long> {

    List<ScheduledTransfer> getByToAccountNumberOrFromAccountNumber(String toAccountNumber, String fromAccountNumber);

}
