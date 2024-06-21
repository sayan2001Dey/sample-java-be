package com.sample.service.record;

import com.sample.model.Record;

import com.sample.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record getRecordById(Long id) {
        Optional<Record> optionalRecord = recordRepository.findById(id);
        return optionalRecord.orElse(null);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Record updateRecord(Record record) {
        // Ensure the record exists in the database
        if (record.getId() != null && recordRepository.existsById(record.getId())) {
            return recordRepository.save(record);
        }
        return null; // Return null or throw exception if record does not exist
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
