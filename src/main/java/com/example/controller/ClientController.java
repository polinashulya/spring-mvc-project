package com.example.controller;

import com.example.dao.impl.DeletionStatus;
import com.example.exception.ControllerCustomException;
import com.example.service.ClientService;
import com.example.service.CountryService;
import com.example.service.dto.ClientDto;
import com.example.service.dto.CountryDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
import lombok.AllArgsConstructor;
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
            PageableDto<ClientDto> clients = clientService.getAll(clientSearchCriteriaDto);

            model.addAttribute("clientPageable", clients);
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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id, Model model) {
        DeletionStatus deletionStatus = clientService.deleteById(Long.valueOf(id));
        model.addAttribute("deletionStatus", deletionStatus.getStatusCode());
        return "redirect:/clients";
    }
}