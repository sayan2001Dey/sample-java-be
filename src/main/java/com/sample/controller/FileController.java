package com.sample.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sample.service.record.RecordService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/files")
public class FileController {

	@Autowired
    private RecordService recordService;
	
    @Autowired
    private Environment env;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody byte[] fileBytes, @RequestParam("fileName") String fileName) {
        String uploadDir = env.getProperty("upload.dir"); // Assuming a property named upload.dir is defined

        if (uploadDir == null || uploadDir.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload directory not configured! Please set 'upload.dir' property.");
        }

        try {
            // Generate a unique file name if not provided
            if (fileName == null || fileName.isEmpty()) {
                fileName = UUID.randomUUID().toString();
            }

            // Create file path
            String filePath = Paths.get(uploadDir, fileName).toString();

            // Write bytes to file
            Files.write(Paths.get(filePath), fileBytes);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }
}
