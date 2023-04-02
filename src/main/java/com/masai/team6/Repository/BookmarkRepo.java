package com.masai.team6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.team6.Entities.Bookmark;

@Repository
public interface BookmarkRepo extends JpaRepository<Bookmark, Integer> {

	@Query("select b.lectureId from Bookmark b where userId =:id")
	List<Integer> findByUserId(@Param("id") Integer userId);

	Bookmark findByUserIdAndLectureId(Integer userId, Integer LectureId);
}
