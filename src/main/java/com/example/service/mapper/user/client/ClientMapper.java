package com.example.service.mapper.user.client;

import com.example.entity.ClientEntity;
import com.example.entity.UserEntity;
import com.example.service.dto.ClientDto;
import com.example.service.dto.UserDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(config = ClientMapperConfig.class)
public interface ClientMapper extends AbstractMapper<ClientDto, ClientEntity> {

    ClientEntity toEntity(ClientDto clientDto);

    ClientDto toDto(ClientEntity clienEntity);


}
