package com.masai.team6.Services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.Batch;
import com.masai.team6.Entities.Section;
import com.masai.team6.Exception.ResourceNotFoundException;
import com.masai.team6.Payloads.BatchDto;
import com.masai.team6.Payloads.SectionDto;
import com.masai.team6.Repository.BatchRepo;
import com.masai.team6.Services.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepo batchRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BatchDto saveBatches(BatchDto batchDto) {
		Batch batch = this.modelMapper.map(batchDto, Batch.class);
		batchRepo.save(batch);
		
		return this.modelMapper.map(batch, BatchDto.class);
	}

	@Override
	public List<BatchDto> allBatches() {
		
		List<Batch> allBatches= batchRepo.findAll();
		
		List<BatchDto> BatchDtos = allBatches.stream().map(batch -> this.modelMapper.map(batch, BatchDto.class))
				.collect(Collectors.toList());
		return BatchDtos;
	}
	

	@Override
	public List<BatchDto> activeBatches() {
		
		List<Batch> BatchList = batchRepo.findAll();
		
		List<Batch> BatchListing = new ArrayList<>();
		for(int i = 0; i< BatchList.size(); i++) {
			if(BatchList.get(i).getStatus().equals("Active"))
			BatchListing.add(BatchList.get(i));
		}
		List<BatchDto> BatchDtos = BatchListing.stream().map(batch -> this.modelMapper.map(batch, BatchDto.class))
				.collect(Collectors.toList());
		return BatchDtos;
		
	}

	
	@Override
	public  BatchDto deleteBatch(Integer id) {
		Optional<Batch> batch = batchRepo.findById(id);
		
		if(batch.isPresent()) {
			batch.get().setStatus("InActive");
		}
		else	
		{
			System.out.println("batch does not exixts or already deleted");
		}
		
		return this.modelMapper.map(batch, BatchDto.class);
	}
	
	
	public Map<Integer, String> getBatchNameMap(){
		
		Map<Integer, String> map = new HashMap<>();
		List<Batch> sectionList= batchRepo.findAll();
		sectionList.forEach(c -> map.put(c.getBatchId(), c.getBatchName()));
		return map;
	}

	



}

