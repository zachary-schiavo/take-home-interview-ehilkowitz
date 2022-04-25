package com.capitalone.interview.repository;

import com.capitalone.interview.domain.ScheduledTransfer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ScheduledTransferUpdateRepository extends JpaRepository<ScheduledTransfer, UUID> {


    //delete by confirmation number
    void deleteById(UUID id);


}
