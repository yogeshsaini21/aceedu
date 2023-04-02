package com.masai.team6.Services;

import java.util.List;

import com.masai.team6.Payloads.SectionDto;

public interface SectionService {

	SectionDto saveSection(SectionDto sectionDto);
	
	List<SectionDto>allSections();
	
	List<SectionDto> activeSections();
	
	SectionDto deleteSection(Integer id);
}

