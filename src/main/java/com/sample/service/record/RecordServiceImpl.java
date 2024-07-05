package com.sample.service.record;

import com.sample.model.Record;
import com.sample.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private Environment env;

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
    @Transactional
    public Record updateRecord(Record record) {
        if (record.getId() != null && recordRepository.existsById(record.getId())) {
            return recordRepository.save(record);
        }
        return null;
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }

    public ResponseEntity<String> saveAttachment(String fieldName, Long id, byte[] blobData, String ext) {
    	String uploadDir = env.getProperty("upload.dir"); // Assuming a property named upload.dir is defined

        if (uploadDir == null || uploadDir.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload directory not configured! Please set 'upload.dir' property.");
        }

        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Entry not found.");
        }

        String fileName = UUID.randomUUID().toString() + id +'.' + ext;

        switch (fieldName) {
            case "scanCopy":
                record.getScanCopy().add(fileName);
                break;
            case "mutationFile":
                record.getMutationFile().add(fileName);
                break;
            case "conversionFile":
                record.getConversionFile().add(fileName);
                break;
            case "documentFile":
                record.getDocumentFile().add(fileName);
                break;
            case "areaMapFile":
                record.getAreaMapFile().add(fileName);
                break;
            case "hcDocumentFile":
                record.getHcdocumentFile().add(fileName);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Unsupported field name: " + fieldName);
        }

        try{
            Files.write(Paths.get(uploadDir, fieldName, fileName), blobData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
        recordRepository.save(record);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }
}
