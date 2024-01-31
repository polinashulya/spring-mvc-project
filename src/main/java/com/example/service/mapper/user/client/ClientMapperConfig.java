package com.example.service.mapper.user.client;

import com.example.entity.ClientEntity;
import com.example.service.dto.ClientDto;
import com.example.service.mapper.core.AbstractMapper;
import com.example.service.mapper.user.UserMapper;
import org.mapstruct.MapperConfig;

@MapperConfig(uses = UserMapper.class)
public interface ClientMapperConfig extends AbstractMapper<ClientDto, ClientEntity> {

}
