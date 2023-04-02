package com.masai.team6.Services;

import java.util.List;

import com.masai.team6.Payloads.TypeDto;

public interface TypeService {

	TypeDto createType(TypeDto typeDto);

	TypeDto updateType(TypeDto typeDto, Integer typeId);

	public void deleteType(Integer typeId);

	public TypeDto getType(Integer typeId);

	List<TypeDto> getTypes();
}
