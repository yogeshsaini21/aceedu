package com.masai.team6.Services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.masai.team6.Entities.Section;
import com.masai.team6.Exception.ResourceNotFoundException;

import com.masai.team6.Payloads.SectionDto;
import com.masai.team6.Repository.SectionRepo;
import com.masai.team6.Services.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepo sectionRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SectionDto saveSection(SectionDto sectionDto) {
		Section section = this.modelMapper.map(sectionDto, Section.class);
		sectionRepo.save(section);

		return this.modelMapper.map(section, SectionDto.class);
	}
	
	@Override
	public List<SectionDto> allSections() {
		
		List<Section> allSections=sectionRepo.findAll();
		
		List<SectionDto> SectionDtos = allSections.stream().map(section -> this.modelMapper.map(section, SectionDto.class))
				.collect(Collectors.toList());
		return SectionDtos;
	}


	@Override
	public List<SectionDto> activeSections() {
		
		List<Section> SectionList = sectionRepo.findAll();
		
				List<Section> SectionListing = new ArrayList<>();
				for(int i = 0; i< SectionList.size(); i++) {
					if(SectionList.get(i).getStatus().equals("Active"))
					SectionListing.add(SectionList.get(i));
				}
				
				List<SectionDto> SectionDtos = SectionListing.stream().map(section -> this.modelMapper.map(section, SectionDto.class))
						.collect(Collectors.toList());
				return SectionDtos;
	}



	@Override
	public SectionDto deleteSection(Integer id) {
	
		Optional<Section> section = sectionRepo.findById(id);
		if(section.isPresent()) {
			section.get().setStatus("InActive");
			
		}else {
			System.out.println( "Section does not exists or already deleted");
		}
		
		
		return this.modelMapper.map(section, SectionDto.class);
		
	}
	
	public Map<Integer, String> getSectionNameMap(){
		
		Map<Integer, String> map = new HashMap<>();
		List<Section> sectionList= sectionRepo.findAll();
		sectionList.forEach(c -> map.put(c.getSectionId(), c.getSectionsName()));
		return map;
	}




	

}
