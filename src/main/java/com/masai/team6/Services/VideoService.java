package com.masai.team6.Services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.masai.team6.Exception.LectureException;
import com.masai.team6.Payloads.VideoResourceDto;

public interface VideoService {

	public String saveVideo(MultipartFile file, Integer lectureId) throws IOException , LectureException;
	
	VideoResourceDto getVideoById(Integer id) throws LectureException;

	String deleteVideoById(Integer videoId)throws LectureException;

	String updateVideo(MultipartFile file, Integer videoId, Integer lectureId)throws LectureException , IOException;
	
	
	public VideoResourceDto getVideoByLectureId(Integer lectureid) throws LectureException;
	
}
