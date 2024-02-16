package com.example.service;

import com.example.dao.impl.DeletionStatus;
import com.example.service.dto.ClientDto;
import com.example.service.dto.search.UserSearchCriteriaDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getAll(UserSearchCriteriaDto clientSearchCriteriaDto);

    void add(ClientDto clientDto);

    DeletionStatus deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
    
}
