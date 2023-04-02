package com.masai.team6.Services.Impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.masai.team6.Entities.Lecture;
import com.masai.team6.Entities.Video;
import com.masai.team6.Exception.LectureException;
import com.masai.team6.Payloads.VideoResourceDto;
import com.masai.team6.Repository.LectureDao;
import com.masai.team6.Repository.VideoRepo;
import com.masai.team6.Services.VideoService;



@Service
public class VideoServiceImpl implements VideoService{
	
    @Autowired
    private VideoRepo videoRepository;
    
    
    @Autowired
    private LectureDao lectureDao;
    
//    @Autowired
//    private ModelMapper mapper;

    @Override
    public String saveVideo(MultipartFile file, Integer lectureId) throws IOException , LectureException{
        byte[] data = file.getBytes();
        Video video = new Video();
        video.setData(data);
        video.setLectureId(lectureId);
//        video.setTitle(title);
//        video.setDescription(description);
        videoRepository.save(video);
        
        Lecture lecture =lectureDao.findById(lectureId).get();
        lecture.setVideoId(video.getId());
        
        lectureDao.save(lecture);
       
        return "Video uploaded Successfully: " + file.getOriginalFilename();
    }

    public VideoResourceDto getVideoById(Integer id) throws LectureException{
        Video video = videoRepository.findById(id).orElseThrow(() -> new LectureException("Video not found"));
        return new VideoResourceDto(video.getId(),video.getLectureId(),video.getData());
    }
    
    public VideoResourceDto getVideoByLectureId(Integer lectureid) throws LectureException{
    	Lecture lecture = lectureDao.findById(lectureid).get();
        Video video = videoRepository.findById(lecture.getVideoId()).orElseThrow(() -> new LectureException("Video not found"));
        return new VideoResourceDto(video.getId(),video.getLectureId(),video.getData());
    }

	@Override
	public String deleteVideoById(Integer videoId) throws LectureException {
		
		Video video = videoRepository.findById(videoId).orElseThrow(() -> new LectureException("no video present with ID:" + videoId));
		Lecture lecture= lectureDao.findById(video.getLectureId()).get();
		lecture.setVideoId(null);
		videoRepository.delete(video);
		return "Successfully Deleted Video With ID: " + videoId;
	}

	@Override
	public String updateVideo(MultipartFile file, Integer videoId, Integer lectureId ) throws LectureException, IOException {
	
		
		Video video = videoRepository.findById(videoId).orElseThrow(() -> new LectureException("no video present with ID:" + videoId));
		
		video.setLectureId(lectureId);
		video.setData(file.getBytes());
		
		videoRepository.save(video);
		
		return "Video updated Successfully!";

	}
	
//	public Map<Integer, byte[]> getVideoDataMap(){
//		
//		Map<Integer, byte[]> map = new HashMap<>();
//		List<Video> videoList = videoRepository.findAll();
//		videoList.forEach(c -> map.put(c.getId(), c.getData()));
//		return map;
//	}

	
}
