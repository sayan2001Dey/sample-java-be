//package com.sample.controller;
//
//import com.sample.model.Record;
//import com.sample.service.record.AttachmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//@CrossOrigin()
//@RequestMapping("api/landrecord")
//public class AttachmentController {
//
//
//    @Autowired
//    private AttachmentService attachmentService;
//
//    @PostMapping("/{collectionName}/{extension}/{id}")
//    public Map<String, String> saveFile(@PathVariable String collectionName,
//                           @PathVariable String extension,
//                           @PathVariable Long id,
//                           @RequestBody byte[] fileBytes){
//        return Collections.singletonMap(
//                "fileName",
//                attachmentService.saveFile(collectionName, extension, id, fileBytes));
//    }
//
//}
