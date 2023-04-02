package com.masai.team6.Services.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.Lecture;
import com.masai.team6.Exception.LectureException;
import com.masai.team6.Exception.ResourceNotFoundException;
import com.masai.team6.Payloads.LectureDetailResponseDto;
import com.masai.team6.Payloads.LectureDto;
import com.masai.team6.Payloads.LectureResponseDto;
import com.masai.team6.Payloads.LectureResponsePagination;
import com.masai.team6.Payloads.LectureSortingComparator;
import com.masai.team6.Repository.BatchRepo;
import com.masai.team6.Repository.LectureDao;
import com.masai.team6.Repository.SectionRepo;
import com.masai.team6.Repository.UserRepo;
import com.masai.team6.Services.LectureService;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	private LectureDao lectureDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BatchRepo batchRepo;

	@Autowired
	private SectionRepo sectionRepo;

	@Autowired
	private BatchServiceImpl batchService;

	@Autowired
	private SectionServiceImpl sectionService;

	@Autowired
	private TypeServiceImpl typeService;

	@Autowired
	private TagServiceImpl tagService;

	@Autowired
	private CategoryServiceImpl categoryService;

	@Autowired
	private VideoServiceImpl videoService;

	@Override
	public String registerNewLecture(LectureDto lectureDto) {
		Lecture lecture = modelMapper.map(lectureDto, Lecture.class);
		Lecture savedLecture = lectureDao.save(lecture);
		return "Lecture Added successfully";
	}

	@Override
	public void deleteLectureById(Integer lectureId) throws LectureException {

		Lecture lecture = lectureDao.findById(lectureId)
				.orElseThrow(() -> new LectureException("Lecture does not exists or already deleted"));

		lecture.setStatus('D');
		lectureDao.save(lecture);

	}

	@Override
	public String updateLecture(LectureDto lectureDto, Integer LectureId) throws LectureException {

		Lecture lecture = lectureDao.findById(LectureId)
				.orElseThrow(() -> new ResourceNotFoundException("Lecture", "id", LectureId));

		if (lecture.getStatus() != 'D') {

			if(lectureDto.getTitle() != null)
			lecture.setTitle(lectureDto.getTitle());
			else
				lecture.setTitle(lecture.getTitle());
			
			if(lectureDto.getCreatedBy() != null)
			lecture.setCreatedBy(lectureDto.getCreatedBy());
			else
				lecture.setCreatedBy(lecture.getCreatedBy());
			
			if(lectureDto.getUpdatedBy() != null)
			lecture.setUpdatedBy(lectureDto.getUpdatedBy());
			else
				lecture.setUpdatedBy(lecture.getUpdatedBy());
			
			if(lectureDto.getStartTime() != null)
			lecture.setStartTime(lectureDto.getStartTime());
			else
				lecture.setStartTime(lecture.getStartTime());
			
			if(lectureDto.getConcludeTime() != null)
			lecture.setConcludeTime(lectureDto.getConcludeTime());
			else
				lecture.setConcludeTime(lecture.getConcludeTime());
			
			if(lectureDto.getZoomLink() != null)
			lecture.setZoomLink(lectureDto.getZoomLink());
			else
				lecture.setZoomLink(lecture.getZoomLink());
			
			if(lectureDto.getTypeId() != null)
			lecture.setTypeId(lectureDto.getTypeId());
			else
				lecture.setTypeId(lecture.getTypeId());
			
			if(lectureDto.getBatchId() != null)
			lecture.setBatchId(lectureDto.getBatchId());
			else
				lecture.setBatchId(lecture.getBatchId());
			
			if(lectureDto.getCategoryId() != null)
			lecture.setCategoryId(lectureDto.getCategoryId());
			else
				lecture.setCategoryId(lecture.getCategoryId());
			
			if(lectureDto.getSectionId() != null)
			lecture.setSectionId(lectureDto.getSectionId());
			else
				lecture.setSectionId(lecture.getSectionId());
			
			if(lectureDto.getTagId() != null)
			lecture.setTagId(lectureDto.getTagId());
			else
				lecture.setTagId(lecture.getTagId());
			
			if(lectureDto.getVideoId() != null)
			lecture.setVideoId(lectureDto.getVideoId());
			else
				lecture.setVideoId(lecture.getVideoId());
			
			if(lectureDto.getCopyLectureFrom() != null)
			lecture.setCopyLectureFrom(lectureDto.getCopyLectureFrom());
			else
				lecture.setCopyLectureFrom(lecture.getCopyLectureFrom());
			
			if(lectureDto.getNotes() != null)
			lecture.setNotes(lectureDto.getNotes());
			else
				lecture.setNotes(lecture.getNotes());
			
			if(lectureDto.getOptional() != null)
			lecture.setOptional(lectureDto.getOptional());
			else
				lecture.setOptional(lecture.getOptional());
			
			if(lectureDto.getHideVideo() != null)
			lecture.setHideVideo(lectureDto.getHideVideo());
			else
				lecture.setHideVideo(lecture.getHideVideo());
			
			if(lectureDto.getWeek() != null)
			lecture.setWeek(lectureDto.getWeek());
			else
				lecture.setWeek(lecture.getWeek());
			
			if(lectureDto.getDay() != null)
			lecture.setDay(lectureDto.getDay());
			else
				lecture.setDay(lecture.getDay());
			
		} else {
			throw new LectureException("Lecture By The ID :" + LectureId + " Already deleted..");
		}
		Lecture UpdatedLecture = lectureDao.save(lecture);
		return "Lecture Updated Successfully";

	}

	@Override
	public LectureResponsePagination getAllLectures(Integer pageNumber, Integer pageSize) throws LectureException {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Lecture> allLecture = lectureDao.findAll(p);

		
		List<Lecture> lectures = allLecture.getContent();
		
		List<LectureResponseDto> responseDto = new ArrayList<>();
		Map<Integer, String> categoriesMap = categoryService.getCategoryNameMap();

		Map<Integer, String> typeMap = typeService.getTypeNameMap();
		Map<Integer, String> tagMap = tagService.getTagNameMap();

		for (int i = 0; i < lectures.size(); i++) {

			if (lectures.get(i).getStatus() != 'D') {

				LectureResponseDto lRD = new LectureResponseDto();
				lRD.setLectureId(lectures.get(i).getLectureID());
				lRD.setTitle(lectures.get(i).getTitle());
				lRD.setCreatedBy(userRepo.findById(lectures.get(i).getCreatedBy()).get().getName());
				lRD.setUpdatedBy(lectures.get(i).getUpdatedBy());
				lRD.setStartTime(lectures.get(i).getStartTime());
				lRD.setConcludeTime(lectures.get(i).getConcludeTime());
				lRD.setZoomLink(lectures.get(i).getZoomLink());
				lRD.setCategory(categoriesMap.get(lectures.get(i).getCategoryId()));
				lRD.setTag(tagMap.get(lectures.get(i).getTagId()));
				lRD.setType(typeMap.get(lectures.get(i).getTypeId()));
				lRD.setBatch(batchRepo.findById(lectures.get(i).getBatchId()).get().getBatchName());
				lRD.setSection(sectionRepo.findById(lectures.get(i).getSectionId()).get().getSectionsName());
				lRD.setOptional(lectures.get(i).getOptional());
				lRD.setHideVideo(lectures.get(i).getHideVideo());
				lRD.setVideoId(lectures.get(i).getVideoId());
				lRD.setCopyLectureFrom(lectures.get(i).getCopyLectureFrom());
				lRD.setNotes(lectures.get(i).getNotes());
				lRD.setWeek(lectures.get(i).getWeek());
				lRD.setDay(lectures.get(i).getDay());
				responseDto.add(lRD);

			}
		}
		LectureResponsePagination lectureResponse = new LectureResponsePagination();
		lectureResponse.setContent(responseDto);
		lectureResponse.setPageSize(allLecture.getSize());
		lectureResponse.setPageNumber(allLecture.getNumber());
		lectureResponse.setTotalElements(allLecture.getTotalElements());
		lectureResponse.setTotalPages(allLecture.getTotalPages());
		lectureResponse.setLastPage(allLecture.isLast());

		return lectureResponse;

	}

	@Override
	public List<LectureResponseDto> getLectureByUserBatchandSection(Integer batchId, Integer sectionId) {
		List<Lecture> lectures = this.lectureDao.findByBatchIdAndSectionId(batchId, sectionId);
		Collections.sort(lectures, new LectureSortingComparator().reversed());
		List<LectureResponseDto> responseDto = new ArrayList<>();
		Map<Integer, String> categoriesMap = categoryService.getCategoryNameMap();
		Map<Integer, String> typesMap = typeService.getTypeNameMap();
		Map<Integer, String> tagsMap = tagService.getTagNameMap();

		for (int i = 0; i < lectures.size(); i++) {

			if (lectures.get(i).getStatus() != 'D') {

				LectureResponseDto lRD = new LectureResponseDto();
				lRD.setLectureId(lectures.get(i).getLectureID());
				lRD.setTitle(lectures.get(i).getTitle());
				lRD.setCreatedBy(userRepo.findById(lectures.get(i).getCreatedBy()).get().getName());
				lRD.setUpdatedBy(lectures.get(i).getUpdatedBy());
				lRD.setStartTime(lectures.get(i).getStartTime());
				lRD.setConcludeTime(lectures.get(i).getConcludeTime());
				lRD.setZoomLink(lectures.get(i).getZoomLink());

				lRD.setCategory(categoriesMap.get(lectures.get(i).getCategoryId()));
				lRD.setTag(tagsMap.get(lectures.get(i).getTagId()));
				lRD.setType(typesMap.get(lectures.get(i).getTypeId()));

				lRD.setBatch(batchRepo.findById(lectures.get(i).getBatchId()).get().getBatchName());
				lRD.setSection(sectionRepo.findById(lectures.get(i).getSectionId()).get().getSectionsName());
				lRD.setOptional(lectures.get(i).getOptional());
				lRD.setHideVideo(lectures.get(i).getHideVideo());
				lRD.setVideoId(lectures.get(i).getVideoId());
//				lRD.setCopyLectureFrom(lectures.get(i).getCopyLectureFrom());
				lRD.setNotes(lectures.get(i).getNotes());

				responseDto.add(lRD);

			}
		}

		return responseDto;
	}

	@Override
	public LectureDto getLectureById(Integer lectureId) throws LectureException {
		Lecture lecture = lectureDao.findById(lectureId)
				.orElseThrow(() -> new LectureException("Lecture does not exists or already deleted"));

		return modelMapper.map(lecture, LectureDto.class);
	}

	@Override
	public List<LectureResponseDto> searchLecturebyFields(String title, Integer category, String optional,
			Integer batch, Integer section, Integer type, LocalDateTime sDate, LocalDateTime eDate, Integer createdBy,
			Integer week, Integer day) throws LectureException {
		if (title != null && title.equals("")) {
			title = null;
		}
		if (batch != null && batch.equals("")) {
			batch = null;
		}
		if (section != null && section.equals("")) {
			section = null;
		}
		if (type != null && type.equals("")) {
			type = null;
		}
		if (createdBy != null && createdBy.equals("")) {
			createdBy = null;
		}
		if (day != null && day.equals("")) {
			day = null;
		}
		if (week != null && week.equals("")) {
			week = null;
		}
		if (category != null && category.equals("")) {
			category = null;
		}
		if (optional != null && optional.equals("")) {
			optional = null;
		}
		
		List<Lecture> searchLecture = this.lectureDao.searchLectures(title, category, optional, batch, section, type,
				sDate, eDate, createdBy, week, day);
		 Collections.sort(searchLecture, new LectureSortingComparator().reversed());
		List<LectureResponseDto> responseDto = new ArrayList<>();
		Map<Integer, String> categoriesMap = categoryService.getCategoryNameMap();

		Map<Integer, String> typeMap = typeService.getTypeNameMap();
		Map<Integer, String> tagMap = tagService.getTagNameMap();
//		Map<Integer, byte[]> videoMap = videoService.getVideoDataMap();

		for (int i = 0; i < searchLecture.size(); i++) {

			if (searchLecture.get(i).getStatus() != 'D') {

				LectureResponseDto lRD = new LectureResponseDto();
				lRD.setLectureId(searchLecture.get(i).getLectureID());
				lRD.setTitle(searchLecture.get(i).getTitle());
				lRD.setCreatedBy(userRepo.findById(searchLecture.get(i).getCreatedBy()).get().getName());
				lRD.setUpdatedBy(searchLecture.get(i).getUpdatedBy());
				lRD.setStartTime(searchLecture.get(i).getStartTime());
				lRD.setConcludeTime(searchLecture.get(i).getConcludeTime());
				
				lRD.setZoomLink(searchLecture.get(i).getZoomLink());

				lRD.setType(typeMap.get(searchLecture.get(i).getTypeId()));

				lRD.setCategory(categoriesMap.get(searchLecture.get(i).getCategoryId()));

				lRD.setTag(tagMap.get(searchLecture.get(i).getTagId()));

				lRD.setBatch(batchRepo.findById(searchLecture.get(i).getBatchId()).get().getBatchName());
				lRD.setSection(sectionRepo.findById(searchLecture.get(i).getSectionId()).get().getSectionsName());
				lRD.setOptional(searchLecture.get(i).getOptional());
				lRD.setHideVideo(searchLecture.get(i).getHideVideo());
				lRD.setVideoId(searchLecture.get(i).getVideoId());
				lRD.setCopyLectureFrom(searchLecture.get(i).getCopyLectureFrom());
				lRD.setWeek(searchLecture.get(i).getWeek());
				lRD.setDay(searchLecture.get(i).getDay());
				lRD.setNotes(searchLecture.get(i).getNotes());
				responseDto.add(lRD);
			}

		}

		return responseDto;
	}

	@Override
	public List<LectureResponseDto> getScheduleForDayByUserBatchandSection(Integer batchId, Integer sectionId,
			LocalDateTime sTime, LocalDateTime eTime) {

		List<Lecture> lecture = this.lectureDao.findByScheduleDate(batchId, sectionId, sTime, eTime);

		 Collections.sort(lecture, new LectureSortingComparator());
		
		List<LectureResponseDto> responseDto = new ArrayList<>();
		Map<Integer, String> categoriesMap = categoryService.getCategoryNameMap();
		Map<Integer, String> typesMap = typeService.getTypeNameMap();
		Map<Integer, String> tagsMap = tagService.getTagNameMap();

		for (int i = 0; i < lecture.size(); i++) {

			if (lecture.get(i).getStatus() != 'D') {

				LectureResponseDto lRD = new LectureResponseDto();
				lRD.setLectureId(lecture.get(i).getLectureID());
				lRD.setTitle(lecture.get(i).getTitle());
				lRD.setCreatedBy(userRepo.findById(lecture.get(i).getCreatedBy()).get().getName());
				lRD.setUpdatedBy(lecture.get(i).getUpdatedBy());
				lRD.setStartTime(lecture.get(i).getStartTime());
				lRD.setConcludeTime(lecture.get(i).getConcludeTime());
				lRD.setZoomLink(lecture.get(i).getZoomLink());

				lRD.setCategory(categoriesMap.get(lecture.get(i).getCategoryId()));
				lRD.setTag(tagsMap.get(lecture.get(i).getTagId()));
				lRD.setType(typesMap.get(lecture.get(i).getTypeId()));

				lRD.setBatch(batchRepo.findById(lecture.get(i).getBatchId()).get().getBatchName());
				lRD.setSection(sectionRepo.findById(lecture.get(i).getSectionId()).get().getSectionsName());
				lRD.setOptional(lecture.get(i).getOptional());
				lRD.setHideVideo(lecture.get(i).getHideVideo());
				lRD.setVideoId(lecture.get(i).getVideoId());
//				lRD.setCopyLectureFrom(lecture.get(i).getCopyLectureFrom());
				lRD.setNotes(lecture.get(i).getNotes());
				responseDto.add(lRD);
			}

		}

		return responseDto;

	}

	public List<LectureResponseDto> getLectureByAllId(List<Integer> lectureIdList) {

		List<Lecture> lecture = lectureDao.findAllById(lectureIdList);

		List<LectureResponseDto> responseDto = new ArrayList<>();
		Map<Integer, String> categoriesMap = categoryService.getCategoryNameMap();
		Map<Integer, String> typesMap = typeService.getTypeNameMap();
		Map<Integer, String> tagsMap = tagService.getTagNameMap();

		for (int i = 0; i < lecture.size(); i++) {

			if (lecture.get(i).getStatus() != 'D') {

				LectureResponseDto lRD = new LectureResponseDto();
				lRD.setLectureId(lecture.get(i).getLectureID());
				lRD.setTitle(lecture.get(i).getTitle());
				lRD.setCreatedBy(userRepo.findById(lecture.get(i).getCreatedBy()).get().getName());
				lRD.setUpdatedBy(lecture.get(i).getUpdatedBy());
				lRD.setStartTime(lecture.get(i).getStartTime());
				lRD.setConcludeTime(lecture.get(i).getConcludeTime());
				lRD.setZoomLink(lecture.get(i).getZoomLink());

				lRD.setCategory(categoriesMap.get(lecture.get(i).getCategoryId()));
				lRD.setTag(tagsMap.get(lecture.get(i).getTagId()));
				lRD.setType(typesMap.get(lecture.get(i).getTypeId()));

				lRD.setBatch(batchRepo.findById(lecture.get(i).getBatchId()).get().getBatchName());
				lRD.setSection(sectionRepo.findById(lecture.get(i).getSectionId()).get().getSectionsName());
				lRD.setOptional(lecture.get(i).getOptional());
				lRD.setHideVideo(lecture.get(i).getHideVideo());
				lRD.setVideoId(lecture.get(i).getVideoId());
//				lRD.setCopyLectureFrom(lecture.get(i).getCopyLectureFrom());
				lRD.setNotes(lecture.get(i).getNotes());
				responseDto.add(lRD);
			}

		}

		return responseDto;

	}

	public LectureDetailResponseDto getDetailLectureById(Integer lectureId) throws LectureException {

		Lecture lecture = lectureDao.findById(lectureId)
				.orElseThrow(() -> new LectureException("Lecture does not exists or already deleted"));

		List<LectureResponseDto> responseDto = new ArrayList<>();
		Map<Integer, String> categoriesMap = categoryService.getCategoryNameMap();
		Map<Integer, String> typesMap = typeService.getTypeNameMap();
		Map<Integer, String> tagsMap = tagService.getTagNameMap();

		LectureDetailResponseDto lRD = new LectureDetailResponseDto();

		if (lecture.getStatus() != 'D') {

			lRD.setLectureId(lecture.getLectureID());
			lRD.setTitle(lecture.getTitle());
			lRD.setCreatedBy(userRepo.findById(lecture.getCreatedBy()).get().getName());
			lRD.setUpdatedBy(lecture.getUpdatedBy());
			lRD.setStartTime(lecture.getStartTime());
			lRD.setConcludeTime(lecture.getConcludeTime());
			lRD.setZoomLink(lecture.getZoomLink());

			lRD.setCategory(categoriesMap.get(lecture.getCategoryId()));
			lRD.setTag(tagsMap.get(lecture.getTagId()));
			lRD.setType(typesMap.get(lecture.getTypeId()));

			lRD.setBatch(batchRepo.findById(lecture.getBatchId()).get().getBatchName());
			lRD.setSection(sectionRepo.findById(lecture.getSectionId()).get().getSectionsName());
			lRD.setOptional(lecture.getOptional());
			lRD.setHideVideo(lecture.getHideVideo());
			lRD.setVideoData(videoService.getVideoById(lecture.getVideoId()).getData());
			lRD.setCopyLectureFrom(lecture.getCopyLectureFrom());
			lRD.setNotes(lecture.getNotes());

		}
		return lRD;
	}

	@Override
	public String copyAndPostUpdatedLecture(LectureDto dto, Integer lectureId) throws LectureException {

		Lecture lecture = lectureDao.findById(lectureId)
				.orElseThrow(() -> new LectureException("Lecture does not exists or already deleted"));

		Lecture lecture2 = new Lecture();

		if (dto.getTitle() == null)
			lecture2.setTitle(lecture.getTitle());
		else {
			lecture2.setTitle(dto.getTitle());
		}

		if (dto.getCreatedBy() == null)
			lecture2.setCreatedBy(lecture.getCreatedBy());
		else {
			lecture2.setCreatedBy(dto.getCreatedBy());
		}

		if (dto.getUpdatedBy() == null)
			lecture2.setUpdatedBy(lecture.getUpdatedBy());
		else {
			lecture2.setUpdatedBy(dto.getUpdatedBy());
		}

		if (dto.getStartTime() == null)
			lecture2.setStartTime(lecture.getStartTime());
		else {
			lecture2.setStartTime(dto.getStartTime());
		}

		if (dto.getConcludeTime() == null)
			lecture2.setConcludeTime(lecture.getConcludeTime());
		else {
			lecture2.setConcludeTime(dto.getConcludeTime());
		}

		if (dto.getTypeId() == null)
			lecture2.setTypeId(lecture.getTypeId());
		else {
			lecture2.setTypeId(dto.getTypeId());
		}

		if (dto.getBatchId() == null)
			lecture2.setBatchId(lecture.getBatchId());
		else {
			lecture2.setBatchId(dto.getBatchId());
		}

		if (dto.getCategoryId() == null)
			lecture2.setCategoryId(lecture.getCategoryId());
		else {
			lecture2.setCategoryId(dto.getCategoryId());
		}

		if (dto.getTagId() == null)
			lecture2.setTagId(lecture.getTagId());
		else {
			lecture2.setTagId(dto.getTagId());
		}

		if (dto.getSectionId() == null)
			lecture2.setSectionId(lecture.getSectionId());
		else {
			lecture2.setSectionId(dto.getSectionId());
		}

		if (dto.getOptional() == null)
			lecture2.setOptional(lecture.getOptional());
		else {
			lecture2.setOptional(dto.getOptional());
		}

		if (dto.getHideVideo() == null)
			lecture2.setHideVideo(lecture.getHideVideo());
		else {
			lecture2.setHideVideo(dto.getHideVideo());
		}

		lecture2.setCopyLectureFrom(lecture.getLectureID());

		if (dto.getNotes() == null)
			lecture2.setNotes(lecture.getNotes());
		else {
			lecture2.setNotes(dto.getNotes());
		}

		lectureDao.save(lecture2);
		return "Copy and Post is Successful!";
	}

}