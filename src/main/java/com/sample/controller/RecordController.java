package com.sample.controller;
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

import javax.servlet.ServletContext;


@RestController
@RequestMapping("/landrecord")
public class RecordController {

    @Autowired
    private RecordService recordService;
    
    @Autowired
    private ServletContext servletContext;

    

    
        @PostMapping("/save")
        public ResponseEntity<Record> saveRecord(@RequestParam("scanCopyFiles") MultipartFile[] scanCopyFiles,
                                                 @RequestParam("mutationFiles") MultipartFile[] mutationFiles,
                                                 @RequestParam("conversionFiles") MultipartFile[] conversionFiles,
                                                 @RequestParam("documentFiles") MultipartFile[] documentFiles,
                                                 @RequestParam("areaMapFiles")  MultipartFile[] areaMapFiles,
                                                 @RequestParam("hcdocumentFiles") MultipartFile[] hcdocumentFiles) {
            try {
                Record record = new Record();

                // Process scanCopy files
                record.setScanCopy(processFiles(scanCopyFiles));

                // Process mutation files
                record.setMutationFile(processFiles(mutationFiles));

                // Process conversion files
                record.setConversionFile(processFiles(conversionFiles));

                // Process document files
                record.setDocumentFile(processFiles(documentFiles));
                
                // Process areaMap files
                record.setAreaMapFile(processFiles(areaMapFiles));

                // Process hcdocument files
                record.setHcdocumentFile(processFiles(hcdocumentFiles));

                // Save record to database (assuming recordService is correctly injected)
                Record savedRecord = recordService.saveRecord(record);

                // Return response with the saved record and HTTP status created
                return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // Helper method to process MultipartFile[] and return List<String>
        private List<String> processFiles(MultipartFile[] files) throws IOException {
            List<String> filePaths = new ArrayList<>();
            String uploadsDir = "C:\\Users\\BMH\\Desktop\\New folder (3)";

            for (MultipartFile file : files) {
                String realPathToUploads = servletContext.getRealPath(uploadsDir);
                if (!new File(realPathToUploads).exists()) {
                    new File(realPathToUploads).mkdir();
                }

                String originalFileName = file.getOriginalFilename();
                String filePath = Paths.get(realPathToUploads, originalFileName).toString();
                Path path = Paths.get(filePath);
                Files.write(path, file.getBytes());
                filePaths.add(filePath);
            }

            return filePaths;
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
    @PatchMapping("/update/{id}")
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
