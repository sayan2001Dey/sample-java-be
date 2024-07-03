//package com.sample.service.record;
//
//import com.sample.model.Record;
//import com.sample.repository.RecordRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//
//import java.io.FileOutputStream;
//
//@Service
//public class AttachmentServiceImpl implements AttachmentService{
//    @Autowired
//    private Environment env;
//
//    @Autowired
//    private RecordRepository recordRepository;
//
//    public String saveFile(String collectionName, String extension, Long id, byte[] fileBytes) {
//        Record record = null;
//        try {
//            record = recordRepository.getReferenceById(id);
//            System.out.print("ypoouuuuuuuuuuu");
//        } finally {
//            if(record != null) {
//                String filePath = "output.txt";
////                FileOutputStream fos = new FileOutputStream(filePath);
//            }
//        }
//        return null;
//    }
//}
