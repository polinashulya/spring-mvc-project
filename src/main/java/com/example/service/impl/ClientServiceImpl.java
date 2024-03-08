package com.example.service.impl;

import com.example.entity.DeletionStatus;
import com.example.entity.ClientEntity;
import com.example.entity.UserRoleEntity;
import com.example.entity.UserRoles;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.ClientRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.ClientService;
import com.example.service.dto.ClientDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
import com.example.service.mapper.user.client.ClientMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final UserRoleRepository userRoleRepository;

    private final ClientMapper clientMapper;

    private PasswordEncoder passwordEncoder;

    @Override
    public PageableDto<ClientDto> getAll(UserSearchCriteriaDto clientSearchCriteriaDto) {
        try {
            List<ClientEntity> entities = clientRepository.findAll(
                    clientSearchCriteriaDto.getSortBy(),
                    clientSearchCriteriaDto.getSortType(),
                    clientSearchCriteriaDto.getCountryId(),
                    clientSearchCriteriaDto.getSearch(),
                    clientSearchCriteriaDto.getPage(),
                    clientSearchCriteriaDto.getPageSize());

            int totalResult = getTotalResult(clientSearchCriteriaDto.getSortBy(), clientSearchCriteriaDto.getSortType(),
                    clientSearchCriteriaDto.getCountryId(), clientSearchCriteriaDto.getSearch());

           return PageableDto.<ClientDto>builder()
                    .elements(clientMapper.toDtoList(entities))
                    .totalSize(totalResult)
                    .build();
        } catch (DAOException e) {
            logger.error("Error while getting all clients: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(ClientDto clientDto) {

        if (clientRepository.findByEmail(clientDto.getEmail()).isPresent()) {
            throw new ServiceException("Email is already in use!");
        }

        ClientEntity clientEntity = clientMapper.toEntity(clientDto);

        UserRoleEntity clientRole = userRoleRepository.findByName(UserRoles.CLIENT.name())
                .orElseThrow(() -> new ServiceException("Default role 'CLIENT' not found"));

        String encodedPassword = passwordEncoder.encode(clientDto.getPassword());

        clientEntity.setPassword(encodedPassword);
        clientEntity.setUserRoles(Set.of(clientRole));
        clientEntity.setRegistrationDate(LocalDate.now());

        try {
            clientRepository.save(clientEntity);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while adding a client: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }


    @Override
    public DeletionStatus deleteById(Long id) {
        try {
            if (id == null || clientRepository.findById(id).isEmpty()) {
                return DeletionStatus.NOT_FOUND;
            }
            return clientRepository.deleteById(id);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while deleting a client: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return clientRepository.getTotalResult(sortBy, sortType, countryId, search);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while getting a total result of users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

}
