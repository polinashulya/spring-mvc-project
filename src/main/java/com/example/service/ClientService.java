package com.example.service;

import com.example.entity.DeletionStatus;
import com.example.service.dto.ClientDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.search.UserSearchCriteriaDto;

public interface ClientService {

    PageableDto<ClientDto> getAll(UserSearchCriteriaDto clientSearchCriteriaDto);

    void add(ClientDto clientDto);

    DeletionStatus deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
    
}
