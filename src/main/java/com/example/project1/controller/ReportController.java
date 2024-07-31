package com.example.project1.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.model.CreateReportForm;
import com.example.project1.model.Report;
import com.example.project1.model.ReportStatus;
import com.example.project1.model.ReportType;
import com.example.project1.model.UpdateReportForm;
import com.example.project1.model.User;
import com.example.project1.service.ReportService;
import com.example.project1.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
        
    @Autowired
    
    private UserService userService;
    
    
    @GetMapping
    public List<Report> getAllReports(){
        return reportService.getAllReports();
    }
    
    @GetMapping("/page")
    public Page<Report> getAllReports(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reportService.getAllReports(pageable);
}
    
    @GetMapping("/user")
    public List<Report> getReportsByUser(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        return reportService.getReportsByUser(user);
    }
    
   
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }
    
    @PostMapping
    public ResponseEntity<Report> saveReport(@RequestBody Report report){
        Report savedReport = reportService.createReport(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }
   
  
   @PostMapping("/add")
    public ResponseEntity<Report> createReport(@Validated @ModelAttribute CreateReportForm form) { 
	   User user = userService.getUserById(form.getUserId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Report report = new Report();
        report.setTitle(form.getTitle());
        report.setDescription(form.getDescription());
        report.setCreationTime(form.getCreationTime());
        report.setUpdateTime(form.getUpdateTime());
        report.setType(form.getType());
        report.setStatus(form.getStatus());
        report.setUser(user);

        Report createdReport = reportService.createReport(report);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }
   @PutMapping("/{id}")
   public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report) {
       report.setId(id);  
       Report updatedReport = reportService.updateReport(report);
       return ResponseEntity.ok(updatedReport);
   }
    @PutMapping
    public ResponseEntity<Report> updateReport(@Validated @ModelAttribute UpdateReportForm form) {
        User user = userService.getUserById(form.getUserId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Report existingReport = reportService.getReportById(form.getId());
        if (existingReport == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        existingReport.setTitle(form.getTitle());
        existingReport.setDescription(form.getDescription());
        existingReport.setCreationTime(form.getCreationTime());
        existingReport.setUpdateTime(form.getUpdateTime());
        existingReport.setType(form.getType());
        existingReport.setStatus(form.getStatus());
        existingReport.setUser(user);

        Report updatedReport = reportService.updateReport(existingReport);
        return ResponseEntity.ok(updatedReport);
    }
    
    @GetMapping("/by-status")
    public ResponseEntity<List<Report>> getReportsByStatus(@RequestParam ReportStatus status) {
        List<Report> reports = reportService.getReportsByStatus(status);
        return ResponseEntity.ok(reports);
    }
    
    @GetMapping("/by-type")
    public ResponseEntity<List<Report>> getReportsByType(@RequestParam ReportType type) {
        List<Report> reports = reportService.getReportsByType(type);
        return ResponseEntity.ok(reports);
    }
    
    @GetMapping("/groupedByStatusCreated")
    public Map<ReportStatus, List<Report>> getReportsGroupedByStatusCreated(
    		 @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate startDate,
             @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate endDate) {
        
        return reportService.getReportsGroupedByStatusCreated(startDate, endDate);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

}
