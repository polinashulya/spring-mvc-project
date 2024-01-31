package com.example.controller;

import com.example.exception.ControllerCustomException;
import com.example.service.dto.ClientDto;
import com.example.service.dto.CountryDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
import com.example.service.impl.CountryServiceImpl;
import com.example.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);

    private final ClientServiceImpl clientService;
    private final CountryServiceImpl countryService;

    @GetMapping
    public String findAllClients(Model model, @ModelAttribute UserSearchCriteriaDto clientSearchCriteriaDto) {
        try {
            List<ClientDto> clients = clientService.getAll(clientSearchCriteriaDto.getSortBy(), clientSearchCriteriaDto.getSortType(),
                    clientSearchCriteriaDto.getCountryId(), clientSearchCriteriaDto.getSearch(),
                    clientSearchCriteriaDto.getPage(), clientSearchCriteriaDto.getPageSize());

            int totalClients = clientService.getTotalResult(clientSearchCriteriaDto.getSortBy(), clientSearchCriteriaDto.getSortType(),
                    clientSearchCriteriaDto.getCountryId(), clientSearchCriteriaDto.getSearch());

            model.addAttribute("totalUsers", totalClients);
            model.addAttribute("users", clients);
            model.addAttribute("sortBy", clientSearchCriteriaDto.getSortBy());
            model.addAttribute("sortType", clientSearchCriteriaDto.getSortType());
            model.addAttribute("currentCountryId", clientSearchCriteriaDto.getCountryId());

            setCountriesToModel(model);

            return "users";
        } catch (Exception e) {
            logger.error("Error while executing find all clients", e);
            throw new ControllerCustomException("Error while executing find all clients", e);
        }
    }

    private void setCountriesToModel(Model model) {
        List<CountryDto> countries = this.countryService.findAll();
        model.addAttribute("countries", countries);
    }

    @GetMapping("/adding_form")
    public String addingForm(Model model) {
        try {
            setCountriesToModel(model);
            return "add_client";
        } catch (Exception e) {
            logger.error("Error while executing adding form", e);
            throw new ControllerCustomException("Error while executing adding form", e);
        }
    }

    @PostMapping
    public String save(@ModelAttribute ClientDto clientDto) {
        try {
            clientService.add(clientDto);
            return "redirect:/users";
        } catch (Exception e) {
            logger.error("Error while executing saving", e);
            throw new ControllerCustomException("Error while executing saving", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String clientId) {
        try {
            clientService.deleteById(Long.valueOf(clientId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error while executing deleting", e);
            throw new ControllerCustomException("Error while executing deleting", e);
        }
    }

}