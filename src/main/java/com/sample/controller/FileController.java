package com.sample.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sample.service.record.RecordService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/attachments")
public class FileController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/{fieldName}")
    public ResponseEntity<String> uploadFile(@RequestBody byte[] fileBytes,
                                            @RequestParam(value = "id") Long id,
                                            @RequestParam(value = "ext") String extension,
                                            @PathVariable String fieldName) {
        return recordService.saveAttachment(fieldName, id, fileBytes,extension);
    }
}
