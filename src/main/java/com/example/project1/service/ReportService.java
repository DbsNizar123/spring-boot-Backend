package com.example.project1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project1.model.Report;
import com.example.project1.model.ReportStatus;
import com.example.project1.model.ReportType;
import com.example.project1.model.ResourceNotFoundException;
import com.example.project1.model.User;
import com.example.project1.repo.ReportRepository;
import com.example.project1.repo.UserRepository;

@Service
public class ReportService {
	
	
	@Autowired
	private ReportRepository reportRepository;	
	@Autowired	
	UserRepository userRepository;
	
	
	 public Page<Report> getAllReports(Pageable pageable) {
	        return reportRepository.findAll(pageable);
	    }
	 public List<Report> getAllReports() {
			return reportRepository.findAll();
		}

	public List<Report> getReportsByUser(User user) {
		return reportRepository.findByUser(user);
	}

	public Report getReportById(Long id) {
		return reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report not found"));
	}

	public Report createReport(Report report) {
		return reportRepository.save(report);
	}
	
	public Report updateReport(Report report) {
        return reportRepository.save(report);
    }
	
	public List<Report> getReportsByStatus(ReportStatus status) {
        return reportRepository.findByStatus(status);
    }
	
	public List<Report> getReportsByType(ReportType type) {
        return reportRepository.findByType(type);
    }
	
	public List<Report> getReportsByCreationTimeBetween(LocalDate startDate, LocalDate endDate)
    {
    	return reportRepository.findByCreationTimeBetween(startDate, endDate);
    	
    	}
	
    	
    	public Map<ReportStatus, List<Report>> getReportsGroupedByStatusCreated(LocalDate startDate, LocalDate endDate) {
           
    		List<Report> reports = getReportsByCreationTimeBetween(startDate, endDate);
         
    	return reports.stream()
                          .filter(report -> report.getStatus() == ReportStatus.PEINDING)
                          .collect(Collectors.groupingBy(Report::getStatus));
       }
    	
    	

	public void deleteReport(Long id) {
		Report report = getReportById(id);
		reportRepository.delete(report);
	}
	
}
