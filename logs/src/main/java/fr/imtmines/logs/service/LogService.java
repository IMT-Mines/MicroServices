package fr.imtmines.logs.service;

import fr.imtmines.logs.entity.LogEntry;
import fr.imtmines.logs.repository.LogEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogEntryRepository logEntryRepository;

    public LogService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    public void log(LogEntry log) {
        logEntryRepository.save(log);
    }

}
