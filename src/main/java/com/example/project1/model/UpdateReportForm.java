package com.example.project1.model;

import java.time.LocalDate;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;

@ParameterObject
public class UpdateReportForm {
	
	private Long id;
    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationTime;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate updateTime;

    private ReportType type;
    private ReportStatus status;
    private Long userId;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDate creationTime) {
		this.creationTime = creationTime;
	}
	public LocalDate getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDate updateTime) {
		this.updateTime = updateTime;
	}
	public ReportType getType() {
		return type;
	}
	public void setType(ReportType type) {
		this.type = type;
	}
	public ReportStatus getStatus() {
		return status;
	}
	public void setStatus(ReportStatus status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
    

}
