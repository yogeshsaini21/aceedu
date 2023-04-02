package com.masai.team6.Services;

import java.util.List;

import com.masai.team6.Payloads.BatchDto;

public interface BatchService {

	BatchDto saveBatches(BatchDto batchdto);
	
	List<BatchDto> allBatches();
	
	List<BatchDto> activeBatches();
	
	BatchDto deleteBatch(Integer id);
}
