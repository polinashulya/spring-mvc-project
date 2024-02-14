package com.example.service.mapper.user.client;

import com.example.entity.ClientEntity;
import com.example.entity.EmployeeEntity;
import com.example.entity.UserEntity;
import com.example.service.dto.ClientDto;
import com.example.service.dto.EmployeeDto;
import com.example.service.dto.UserDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = ClientMapperConfig.class)
public interface ClientMapper extends AbstractMapper<ClientDto, ClientEntity> {

    ClientEntity toEntity(ClientDto clientDto);

    ClientDto toDto(ClientEntity clienEntity);

    List<ClientDto> toDtoList(List<ClientEntity> clientEntities);


}
