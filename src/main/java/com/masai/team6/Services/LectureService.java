package com.masai.team6.Services;

import java.time.LocalDateTime;
import java.util.List;

import com.masai.team6.Exception.LectureException;
import com.masai.team6.Payloads.LectureDetailResponseDto;
import com.masai.team6.Payloads.LectureDto;
import com.masai.team6.Payloads.LectureResponseDto;
import com.masai.team6.Payloads.LectureResponsePagination;

public interface LectureService {

	public String registerNewLecture(LectureDto lectureDto);

	public void deleteLectureById(Integer lectureId) throws LectureException;

	public String updateLecture(LectureDto lectureDto , Integer LectureId) throws LectureException;

	public LectureResponsePagination getAllLectures(Integer pageNumber, Integer pageSize) throws LectureException;
	
	public LectureDto getLectureById(Integer lectureId) throws LectureException;

	public List<LectureResponseDto> getLectureByUserBatchandSection(Integer batchId , Integer sectionId);

	public List<LectureResponseDto> searchLecturebyFields(String title,Integer category,String optional, Integer batch, Integer section,Integer type , LocalDateTime eDate , LocalDateTime sDate ,Integer createdBy,Integer week , Integer day) throws LectureException;

	public List<LectureResponseDto> getScheduleForDayByUserBatchandSection(Integer batchId , Integer sectionId, LocalDateTime eTime ,LocalDateTime sTime)throws LectureException;

	public LectureDetailResponseDto getDetailLectureById(Integer lectureId) throws LectureException;
	
	public String copyAndPostUpdatedLecture(LectureDto dto, Integer lectureId)throws LectureException;
}
