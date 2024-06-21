package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;
import com.sample.model.Record;
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
//	 List<Record> findByUser(User user);
}
