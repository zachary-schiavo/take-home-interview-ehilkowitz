package com.capitalone.interview.repository;

import com.capitalone.interview.domain.ScheduledTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Repository Interface for our Domain Model 'ScheduledTransfers'
//JpaRepository Interface provides you with methods to manage your data: save(), findAll(), count(), delete()


//TODO Should @Repository annotation be used here?

@Repository
public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransfer, Long> {

    List<ScheduledTransfer> getByToAccountNumberOrFromAccountNumber(String toAccountNumber, String fromAccountNumber);

    ScheduledTransfer findByUUID(UUID id);

    //delete by confirmation number
    void deleteById(UUID id);



}
