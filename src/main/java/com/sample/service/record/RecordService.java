package com.sample.service.record;

import com.sample.model.Record;


import java.util.List;

public interface RecordService {

    Record saveRecord(Record record);

    Record getRecordById(Long id);

    List<Record> getAllRecords();

    Record updateRecord(Record record);

    void deleteRecord(Long id);
}
