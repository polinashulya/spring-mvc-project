package com.example.service;

import com.example.entity.ClientEntity;
import com.example.service.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    void add(ClientDto clientDto);

    void deleteById(Long clientId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
    
}
