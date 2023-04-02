package com.masai.team6.Services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.masai.team6.Entities.Bookmark;
import com.masai.team6.Entities.Lecture;


public interface BookmarkService {

	Bookmark saveBookmark(Bookmark bookmarks) throws Exception ;
	
	List<Integer> lectureByUserId(Integer userId);
	
	
	void deleteLectureFromBookMark(Integer userId , Integer LectureId);
}
