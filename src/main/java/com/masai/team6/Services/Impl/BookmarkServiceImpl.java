package com.masai.team6.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.Bookmark;

import com.masai.team6.Repository.BookmarkRepo;


import com.masai.team6.Services.BookmarkService;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	 

	@Autowired
	private BookmarkRepo bookMarkRepo;

	@Override
	public Bookmark saveBookmark(Bookmark bookmarks) throws Exception{
      
		
		try {
			return bookMarkRepo.save(bookmarks);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Lecture is already added in bookmark, It must be Unique!!!");
		
		}
		
	}

	@Override
	public List<Integer> lectureByUserId(Integer userId) {
		
		List<Integer> lectureID = this.bookMarkRepo.findByUserId(userId);

		return lectureID;
	}

	@Override
	public void deleteLectureFromBookMark(Integer userId, Integer LectureId) {
		Bookmark bookmark = this.bookMarkRepo.findByUserIdAndLectureId(userId, LectureId);
		this.bookMarkRepo.delete(bookmark);
		
	}
	
	

}
