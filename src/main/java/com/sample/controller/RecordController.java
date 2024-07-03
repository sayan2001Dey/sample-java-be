package com.sample.controller;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sample.model.Record;
import com.sample.service.record.RecordService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@RestController
@CrossOrigin()
@RequestMapping("api/landrecord")
public class RecordController {

    @Autowired
    private RecordService recordService;
    
    @Autowired
    private Environment env;

    @PostMapping()
    public Record saveRecord(@RequestBody Record record) {
        
        return recordService.saveRecord(record);
    }

    
//    @PostMapping()
//    public ResponseEntity<Record> saveRecord(@RequestParam("scanCopyFileRAW") MultipartFile[] scanCopyFileRAW,
//                                             @RequestParam("mutationFileRAW") MultipartFile[] mutationFileRAW,
//                                             @RequestParam("conversionFileRAW") MultipartFile[] conversionFileRAW,
//                                             @RequestParam("documentFileRAW") MultipartFile[] documentFileRAW,
//                                             @RequestParam("areaMapFileRAW") MultipartFile[] areaMapFileRAW,
//                                             @RequestParam("hcdocumentFileRAW") MultipartFile[] hcdocumentFileRAW) throws IOException {
//        try {
//            Record record = new Record();
//
//            // Process scanCopy files
//            record.setScanCopy(processFiles(scanCopyFileRAW));
//
//            // Process mutation files
//            record.setMutationFile(processFiles(mutationFileRAW));
//
//            // Process conversion files
//            record.setConversionFile(processFiles(conversionFileRAW));
//
//            // Process document files
//            record.setDocumentFile(processFiles(documentFileRAW));
//
//            // Process areaMap files
//            record.setAreaMapFile(processFiles(areaMapFileRAW));
//
//            // Process hcdocument files
//            record.setHcdocumentFile(processFiles(hcdocumentFileRAW));
//
//            // Save record to database
//            Record savedRecord = recordService.saveRecord(record);
//
//            // Return response with the saved record and HTTP status created
//            return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    private List<String> processFiles(MultipartFile[] files) throws IOException {
//        List<String> filePaths = new ArrayList<>();
//        String uploadDir = env.getProperty("upload.dir"); // Assuming a property named upload.dir is defined
//
//        if (uploadDir == null || uploadDir.isEmpty()) {
//            throw new RuntimeException("Upload directory not configured! Please set 'upload.dir' property.");
//        }
//
//        // Create the directory if it doesn't exist
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        for (MultipartFile file : files) {
//            String originalFileName = file.getOriginalFilename();
//            String filePath = Paths.get(uploadDir, originalFileName).toString();
//            Path path = Paths.get(filePath);
//            Files.write(path, file.getBytes());
//            filePaths.add(filePath);
//        }
//
//        return filePaths;
//    }
    

    // Get all Records
    @GetMapping()
    public ResponseEntity<List<Record>> getAllRecords() {
        List<Record> records = recordService.getAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    // Get a single Record by ID
    @GetMapping("{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        Record record = recordService.getRecordById(id);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing Record
    @PatchMapping("{id}")
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
    @DeleteMapping("{id}")
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