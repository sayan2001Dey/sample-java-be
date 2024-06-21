package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sample.model.Record;
import com.sample.service.record.RecordService;

import java.util.List;


@RestController
@RequestMapping("/landrecord")
public class RecordController {

    @Autowired
    private RecordService recordService;

    // Create a new Record
    @PostMapping("/save")
    public ResponseEntity<Record> saveRecord(@RequestBody Record record) {
        Record savedRecord = recordService.saveRecord(record);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    // Get all Records
    @GetMapping("/getAll")
    public ResponseEntity<List<Record>> getAllRecords() {
        List<Record> records = recordService.getAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    // Get a single Record by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        Record record = recordService.getRecordById(id);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing Record
    @PutMapping("/update/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record record) {
        Record currentRecord = recordService.getRecordById(id);
        if (currentRecord != null) {
            record.setId(id);
            Record updatedRecord = recordService.saveRecord(record);
            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Record
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        Record record = recordService.getRecordById(id);
        if (record != null) {
            recordService.deleteRecord(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
