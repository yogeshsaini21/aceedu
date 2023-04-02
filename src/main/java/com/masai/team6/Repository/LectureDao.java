package com.masai.team6.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.team6.Entities.Lecture;

@Repository
public interface LectureDao extends JpaRepository<Lecture, Integer> {

	@Query("SELECT L FROM Lecture L WHERE " + "(:title IS NULL OR L.title LIKE %:title%) AND "
			+ "(:categoryId IS NULL OR L.categoryId = :categoryId) AND "
			+ "(:optional IS NULL OR L.optional = :optional) AND "
			+ "(:batchId IS NULL OR L.batchId = :batchId) AND "
			+ "(:sectionId IS NULL OR L.sectionId = :sectionId) AND " + "(:typeId IS NULL OR L.typeId = :typeId) AND"
			+  "(:startTime IS NULL OR L.startTime between :startTime and :endTime) AND"
			+  "(:createdBy IS NULL OR L.createdBy = :createdBy) And "
			+ "(:week IS NULL OR L.week = :week) AND "
			+ "(:day IS NULL OR L.day = :day)"
			)
	List<Lecture> searchLectures(@Param("title") String title,@Param("categoryId") Integer category,@Param("optional") String optional , @Param("batchId") Integer batch,
			@Param("sectionId") Integer section, @Param("typeId") Integer type , @Param("startTime") LocalDateTime sdate, @Param("endTime") LocalDateTime edate , @Param("createdBy") Integer createdBy , @Param("week") Integer week , @Param("day") Integer day);

	
	List<Lecture> findByBatchIdAndSectionId(Integer batchId, Integer sectionId);



	@Query("SELECT L FROM Lecture L WHERE L.batchId =:batchId AND " + " L.sectionId =:sectionId AND " + " L.startTime between :startTime and :endTime")
	List<Lecture> findByScheduleDate(@Param("batchId")Integer batchId,@Param("sectionId") Integer sectionId,@Param("startTime") LocalDateTime sTime1, @Param("endTime") LocalDateTime eTime1);

}