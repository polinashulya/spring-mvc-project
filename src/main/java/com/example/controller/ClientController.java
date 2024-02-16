package com.example.controller;

import com.example.dao.impl.DeletionStatus;
import com.example.exception.ControllerCustomException;
import com.example.service.ClientService;
import com.example.service.CountryService;
import com.example.service.dto.ClientDto;
import com.example.service.dto.CountryDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
import lombok.AllArgsConstructor;
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

    private final ClientService clientService;
    private final CountryService countryService;

    @GetMapping
    public String findAllClients(Model model, @ModelAttribute UserSearchCriteriaDto clientSearchCriteriaDto) {
        try {
            List<ClientDto> clients = clientService.getAll(clientSearchCriteriaDto);

            int totalClients = clientService.getTotalResult(clientSearchCriteriaDto.getSortBy(), clientSearchCriteriaDto.getSortType(),
                    clientSearchCriteriaDto.getCountryId(), clientSearchCriteriaDto.getSearch());

            model.addAttribute("totalUsers", totalClients);
            model.addAttribute("clients", clients);
            model.addAttribute("sortBy", clientSearchCriteriaDto.getSortBy());
            model.addAttribute("sortType", clientSearchCriteriaDto.getSortType());
            model.addAttribute("currentCountryId", clientSearchCriteriaDto.getCountryId());

            setCountriesToModel(model);

            return "clients";
        } catch (Exception e) {
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
            throw new ControllerCustomException("Error while executing adding form", e);
        }
    }

    @PostMapping
    public String save(@ModelAttribute ClientDto clientDto) {
        try {
            clientService.add(clientDto);
            return "redirect:/clients";
        } catch (Exception e) {
            throw new ControllerCustomException("Error while executing saving", e);
        }
    }

@DeleteMapping("/{id}")
@ResponseBody
public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
    DeletionStatus deletionStatus = clientService.deleteById(Long.valueOf(id));
    return switch (deletionStatus) {
        case NO_CONTENT -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletionStatus.name());
        case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletionStatus.name());
    };
}
}