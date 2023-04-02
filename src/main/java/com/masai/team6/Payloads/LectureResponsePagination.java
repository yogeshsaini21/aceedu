package com.masai.team6.Payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class LectureResponsePagination {

private List<LectureResponseDto> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean lastPage;
}
