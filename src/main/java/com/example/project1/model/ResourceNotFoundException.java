package com.example.project1.model;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException (String message) {
	
		super(message);
	}

}
