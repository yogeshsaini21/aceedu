package com.masai.team6.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.masai.team6.Entities.User;
import com.masai.team6.Entities.test;
import com.masai.team6.Repository.UserRepo;
import com.masai.team6.Repository.testRepo;
import com.masai.team6.Services.EmailService;

@Component
public class WeeklyScheduler {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private testRepo tRepo;
	
  @Scheduled(cron = "0 0 0 ? * SAT")
  public void runTask() {
    
	  List<User> users = userRepo.findAll();
	  for(User user : users) {
		  if(user.getBatchId()!=0) {
			  String email = user.getEmail();
			  List<test> tests = tRepo.getListOfTest(email);
			  int size = tests.size();
			  float sum=0;
			  float totalpercentage = 0;
			  int Attendence =0;
			  for(test tes : tests)
			  {
				  float percent = (tes.getGetMarks()/tes.getMaxMarks())*100;
				  totalpercentage+=percent;
				  if(tes.getAttendence().equals("present")) {
					  Attendence++;
				  }
				  				  
			  }
			  float totalProgress = (totalpercentage/size)*100;
			  int absent =size - Attendence;
			  boolean flag = emailService.sendEmail(user.getParentEmail(), user.getName()+" weekly Repot Card", user.getName()+" weekly progress is "+totalProgress+" ,"+" Attendence in Test out of "+size+" "+"is "+Attendence);
						
		  }
		  
		   }
	  
	  
  }

}
