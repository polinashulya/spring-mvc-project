package com.example.service.impl;

import com.example.entity.ClientEntity;
import com.example.entity.UserRoleEntity;
import com.example.entity.UserRoles;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import com.example.repository.ClientRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.ClientService;
import com.example.service.dto.ClientDto;
import com.example.service.mapper.user.client.ClientMapper;
import com.example.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private final ClientRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final CountryRepository countryRepository;

    private final UserValidator validator;

    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            List<ClientEntity> entities = userRepository.findAll(sortBy, sortType, countryId, search, page, pageSize);
            return entities.stream()
                    .map(clientMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Error while getting all users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(ClientDto clientDto) {

        if (!validator.validate(clientDto.getLogin(), clientDto.getPassword(),
                clientDto.getName(), clientDto.getSurname(), LocalDate.parse(clientDto.getBirthDate()))) {
            throw new ServiceException("Information is not valid!");
        }

//        if (clientDto.getCountry().getId() == null || countryRepository.findById(clientDto.getCountry().getId()).isEmpty()) {
//            throw new ServiceException("Country is null or did not find!");
//        }

        if (userRepository.findByLogin(clientDto.getLogin()).isPresent()) {
            throw new ServiceException("Login is already in use!");
        }

        ClientEntity clientEntity = clientMapper.toEntity(clientDto);

        UserRoleEntity clientRole = userRoleRepository.findByName(UserRoles.CLIENT.name())
                .orElseThrow(() -> new ServiceException("Default role 'CLIENT' not found"));

        clientEntity.setUserRole(clientRole);
        clientEntity.setLoyaltyPoints(0);

        try {
            userRepository.save(clientEntity);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while adding a user: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }


    @Override
    public void deleteById(Long userId) {
        try {
            if (userId == null) {
                throw new ServiceException("Client ID is required.");
            }
            userRepository.deleteById(userId);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while deleting a user: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return userRepository.getTotalResult(sortBy, sortType, countryId, search);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while getting a total result of users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

}
