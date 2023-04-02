package com.masai.team6.Services;

import java.util.List;

import com.masai.team6.Payloads.TagDto;

public interface TagService {

	TagDto createTag(TagDto tagDto);

	TagDto updateTag(TagDto tagDto, Integer tagId);

	public void deleteTag(Integer tagId);

	public TagDto getTag(Integer tagId);

	List<TagDto> getTags();
}
