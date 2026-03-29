package org.example.authapi.repository;

import java.util.UUID;
import org.example.authapi.model.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessingLogRepository extends JpaRepository<ProcessingLog, UUID> {
}
