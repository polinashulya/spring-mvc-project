package com.example.service;

import com.example.entity.ClientEntity;
import com.example.service.dto.ClientDto;
import com.example.service.dto.search.UserSearchCriteriaDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getAll(UserSearchCriteriaDto clientSearchCriteriaDto);

    void add(ClientDto clientDto);

    void deleteById(Long clientId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
    
}
