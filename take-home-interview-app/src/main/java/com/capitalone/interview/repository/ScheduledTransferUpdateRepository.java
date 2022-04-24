package com.capitalone.interview.repository;

import com.capitalone.interview.domain.ScheduledTransfer;
import com.capitalone.interview.service.ScheduledTransferService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledTransferUpdateRepository extends JpaRepository<ScheduledTransfer, UUID> {



//    ScheduledTransfer findByUUID(UUID id);

//    void updateScheduledTransfer(ScheduledTransfer updateTransfer, UUID id);

    //delete by confirmation number
    void deleteById(UUID id);


}
