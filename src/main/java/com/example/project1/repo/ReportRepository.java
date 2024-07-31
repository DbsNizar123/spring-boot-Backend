package com.example.project1.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project1.model.Report;
import com.example.project1.model.ReportStatus;
import com.example.project1.model.ReportType;
import com.example.project1.model.User;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	List<Report> findByUser(User user);
	  List<Report> findByStatus(ReportStatus status);
	  List<Report> findByType(ReportType type);
	  List<Report> findByCreationTimeBetween(LocalDate startDate, LocalDate endDate);
	  Page<Report> findAll(Pageable pageable);
	  
	  

}

