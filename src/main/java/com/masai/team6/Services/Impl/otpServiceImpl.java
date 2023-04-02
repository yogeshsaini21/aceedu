package com.masai.team6.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.otpsaver;
import com.masai.team6.Repository.otpsaverRepo;

@Service
public class otpServiceImpl {
	
	@Autowired
	private otpsaverRepo otprepo;
	
	public otpsaver Saveotp(otpsaver otpSaver) {
		otpsaver save = this.otprepo.save(otpSaver);
		return save;
	}
	
	
	public otpsaver FindByEmail(String email) {
		otpsaver otpSaver = this.otprepo.findByEmail(email);
		return otpSaver;
	}
	public void clearOTP( otpsaver os) {
	   otprepo.delete(os);
	}
}
