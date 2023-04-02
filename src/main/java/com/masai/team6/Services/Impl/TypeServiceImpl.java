package com.masai.team6.Services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.Type;
import com.masai.team6.Exception.ResourceNotFoundException;
import com.masai.team6.Payloads.TypeDto;
import com.masai.team6.Repository.TypeRepo;
import com.masai.team6.Services.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeRepo typeRepo;

	@Autowired
	private ModelMapper modelmapper;

	private static List<Type> typeList;

	@PostConstruct
	void inti() {
		typeList = typeRepo.findAll();
	}

	@Override
	public TypeDto createType(TypeDto typeDto) {
		Type type = this.modelmapper.map(typeDto, Type.class);
		Type addedtype = this.typeRepo.save(type);

		return this.modelmapper.map(addedtype, TypeDto.class);
	}

	@Override
	public TypeDto updateType(TypeDto typeDto, Integer typeId) {
		Type cat = this.typeRepo.findById(typeId)
				.orElseThrow(() -> new ResourceNotFoundException("Type", "type Id", typeId));

		cat.setTypeTitle(typeDto.getTypeTitle());

		Type updatedcat = this.typeRepo.save(cat);

		return this.modelmapper.map(updatedcat, TypeDto.class);
	}

	@Override
	public void deleteType(Integer typeId) {
		// TODO Auto-generated method stub
		Type cat = this.typeRepo.findById(typeId)
				.orElseThrow(() -> new ResourceNotFoundException("Type", "type id", typeId));
		this.typeRepo.delete(cat);

	}

	@Override
	public TypeDto getType(Integer typeId) {
		Type cat = this.typeRepo.findById(typeId)
				.orElseThrow(() -> new ResourceNotFoundException("Type", "type id", typeId));

		return this.modelmapper.map(cat, TypeDto.class);
	}

	@Override
	public List<TypeDto> getTypes() {
  
		List<TypeDto> typesDtos = typeList.stream().map((cat) -> this.modelmapper.map(cat, TypeDto.class))
				.collect(Collectors.toList());
		return typesDtos;
	}
	

	public Map<Integer, String> getTypeNameMap(){
		
		Map<Integer, String> map = new HashMap<>();
		typeList.forEach(c -> map.put(c.getTypeId(), c.getTypeTitle()));
		return map;
	}
 
}
