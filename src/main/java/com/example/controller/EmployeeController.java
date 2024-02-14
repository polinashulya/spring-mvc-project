package com.example.controller;

import com.example.exception.ControllerCustomException;
import com.example.service.dto.CountryDto;
import com.example.service.dto.EmployeeDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
import com.example.service.impl.CountryServiceImpl;
import com.example.service.impl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final EmployeeServiceImpl employeeService;
    private final CountryServiceImpl countryService;

    @GetMapping
    public String findAllEmployees(Model model,
                                   @ModelAttribute UserSearchCriteriaDto employeeSearchCriteriaDto) {
        try {
            List<EmployeeDto> employees = employeeService.getAll(employeeSearchCriteriaDto);

            int totalEmployees = employeeService.getTotalResult(employeeSearchCriteriaDto.getSortBy(), employeeSearchCriteriaDto.getSortType(),
                    employeeSearchCriteriaDto.getCountryId(), employeeSearchCriteriaDto.getSearch());

            model.addAttribute("totalUsers", totalEmployees);
            model.addAttribute("employees", employees);
            model.addAttribute("sortBy", employeeSearchCriteriaDto.getSortBy());
            model.addAttribute("sortType", employeeSearchCriteriaDto.getSortType());
            model.addAttribute("currentCountryId", employeeSearchCriteriaDto.getCountryId());

            setCountriesToModel(model);

            return "employees";
        } catch (Exception e) {
            logger.error("Error while executing find all employees", e);
            throw new ControllerCustomException("Error while executing find all employees", e);
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
            return "add_employee";
        } catch (Exception e) {
            logger.error("Error while executing adding form", e);
            throw new ControllerCustomException("Error while executing adding form", e);
        }
    }

    @PostMapping
    public EmployeeDto save(@ModelAttribute EmployeeDto employeeDto) {
        try {
            employeeService.add(employeeDto);
            return employeeDto;
        } catch (Exception e) {
            logger.error("Error while executing saving", e);
            throw new ControllerCustomException("Error while executing saving", e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") String employeeId) {
        try {
            employeeService.deleteById(Long.valueOf(employeeId));
        } catch (Exception e) {
            logger.error("Error while executing deleting", e);
            throw new ControllerCustomException("Error while executing deleting", e);
        }
    }

}