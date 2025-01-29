package fr.imtmines.logs.repository;

import fr.imtmines.logs.entity.LogEntry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntryRepository extends ElasticsearchRepository<LogEntry, String> {
}
