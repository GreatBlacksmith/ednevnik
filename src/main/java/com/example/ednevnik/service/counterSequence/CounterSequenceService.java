package com.example.ednevnik.service.counterSequence;

import com.example.ednevnik.model.CounterSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class CounterSequenceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CounterSequenceService.class);
    private final MongoOperations mongo;

    @Autowired
    public CounterSequenceService(MongoOperations mongo) {
        this.mongo = mongo;
    }

    public Long getNextSequenceValue(String sequenceId) {

        LOGGER.info("Called getNextSequenceValue() with sequenceId: {}", sequenceId);

        Query query = Query.query(Criteria.where("_id").is(sequenceId));
        Update update = new Update().inc("value", 1);
        FindAndModifyOptions options = options().returnNew(true).upsert(true);

        CounterSequence counter = mongo.findAndModify(query, update, options, CounterSequence.class);

        return counter.getValue();
    }

}
