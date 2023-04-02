package com.masai.team6.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.test;
import com.masai.team6.Exception.ApiException;
import com.masai.team6.Repository.testRepo;

@Service
public class testServices {
	
	@Autowired
	private testRepo tRepo;
	
	public List<test> getlistoftestsbyuseremail(String userEmail){
		return tRepo.getListOfTest(userEmail);
	}
}
