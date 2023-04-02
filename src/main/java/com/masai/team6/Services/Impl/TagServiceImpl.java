package com.masai.team6.Services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.Tag;
import com.masai.team6.Exception.ResourceNotFoundException;
import com.masai.team6.Payloads.TagDto;
import com.masai.team6.Repository.TagRepo;
import com.masai.team6.Services.TagService;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagRepo tagRepo;

	@Autowired
	private ModelMapper modelmapper;

	private static List<Tag> TagList;

	@PostConstruct
	void inti() {
		TagList = tagRepo.findAll();
	}

	@Override
	public TagDto createTag(TagDto tagDto) {

		Tag tag = this.modelmapper.map(tagDto, Tag.class);
		Tag addedtag = this.tagRepo.save(tag);

		return this.modelmapper.map(addedtag, TagDto.class);
	}

	@Override
	public TagDto updateTag(TagDto tagDto, Integer tagId) {
		Tag tag = this.tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("tag", "tag Id", tagId));

		tag.setTagTitle(tagDto.getTagTitle());

		Tag updatedtag = this.tagRepo.save(tag);

		return this.modelmapper.map(updatedtag, TagDto.class);

	}

	@Override
	public void deleteTag(Integer tagId) {

		Tag tag = this.tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("tag", "tag id", tagId));
		this.tagRepo.delete(tag);

	}

	@Override
	public TagDto getTag(Integer tagId) {
		Tag tag = this.tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "tag id", tagId));

		return this.modelmapper.map(tag, TagDto.class);
	}

	@Override
	public List<TagDto> getTags() {
		List<TagDto> tagDtos = TagList.stream().map((cat) -> this.modelmapper.map(cat, TagDto.class))
				.collect(Collectors.toList());
//		System.out.println(tagDtos.toString());
		return tagDtos;
	}


	public Map<Integer, String> getTagNameMap(){

		Map<Integer, String> map = new HashMap<>();
		TagList.forEach(c -> map.put(c.getTagId(), c.getTagTitle()));
		return map;
	}

}
