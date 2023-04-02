package com.masai.team6.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.team6.Entities.Batch;
import com.masai.team6.Entities.Bookmark;
import com.masai.team6.Entities.Section;
import com.masai.team6.Payloads.ApiResponse;
import com.masai.team6.Payloads.BatchDto;
import com.masai.team6.Repository.BatchRepo;
import com.masai.team6.Services.BatchService;

@RestController
@RequestMapping("/batch")
public class BatchController {

	@Autowired
    private BatchService batchService;
	
	@Autowired
	private BatchRepo batchRepo;
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<String> saveBatches(@RequestBody BatchDto batchDto){
		 try {
			 
             if(batchDto.getBatchName()!=null) {
            	 BatchDto batchNew = this.batchService.saveBatches(batchDto);
            	 return new ResponseEntity<String>("Batch Added In Bookmark", HttpStatus.OK);
             }
             else
             {
            	 return new ResponseEntity<String>("Batch can't be null or Wrong entry",HttpStatus.NOT_ACCEPTABLE);
             }
		
			}
		 catch (Exception e) {
				return new ResponseEntity<String>("Lecture already Added In Bookmark", HttpStatus.ALREADY_REPORTED);
				
			}
		
		
		
		//return new ResponseEntity<BatchDto>(batchNew, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/allBatches")
	public ResponseEntity<List<BatchDto>> allBatches(){

		List<BatchDto> batch= this.batchService.allBatches();
		return new ResponseEntity<List<BatchDto>>(batch,HttpStatus.OK);
	}
	
	@GetMapping("/activeBatches")
	public ResponseEntity<List<BatchDto>> activeBatches(){

		List<BatchDto> batch= this.batchService.activeBatches();
		return new ResponseEntity<List<BatchDto>>(batch,HttpStatus.OK);
	}
	

	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteBatch(@PathVariable Integer id){

		this.batchService.deleteBatch(id);
		Optional<Batch> batch = batchRepo.findById(id);
		if(batch.isPresent()) {
	        Batch bat=batch.get();
			bat.setStatus("InActive");
			batchRepo.save(bat);
			System.out.println( "Batch status has been set to Deleted");
			return new ResponseEntity<ApiResponse>(new ApiResponse("Batch deleted successfully", true), HttpStatus.OK);
		}else {
			System.out.println( "Batch does not exists or already deleted");
		return new ResponseEntity<ApiResponse>(new ApiResponse("Batch deleted successfully", true), HttpStatus.OK);
	}
}
}