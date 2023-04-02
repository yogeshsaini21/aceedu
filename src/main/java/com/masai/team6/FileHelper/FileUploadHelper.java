package com.masai.team6.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR = "C:\\Users\\yagam\\OneDrive\\Desktop\\masai project\\Team6-backend\\src\\main\\resources\\static\\video";
//	public final String UPLOAD_DIR = new ClassPathResource("static/video/").getFile().getAbsolutePath();
	
	public FileUploadHelper() throws IOException{
		
	}
	
	public boolean uploadFile(MultipartFile multipartFile) {
		
		boolean present = false;
		
		try {
			Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.pathSeparator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

			present = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return present;
	}

}
