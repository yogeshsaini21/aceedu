package com.masai.team6.Exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDetailsPage {

	private LocalDateTime timeStamp;
	private String message;
	private String details;
}
