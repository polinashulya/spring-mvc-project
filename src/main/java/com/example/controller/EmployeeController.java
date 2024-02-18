package com.example.controller;

import com.example.dao.impl.DeletionStatus;
import com.example.exception.ControllerCustomException;
import com.example.service.CountryService;
import com.example.service.EmployeePositionService;
import com.example.service.EmployeeService;
import com.example.service.ProcedureService;
import com.example.service.dto.*;
import com.example.service.dto.search.UserSearchCriteriaDto;
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

    private final EmployeeService employeeService;
    private final CountryService countryService;
    private final EmployeePositionService employeePositionService;
    private final ProcedureService procedureService;

    @GetMapping
    public String findAllEmployees(Model model,
                                   @ModelAttribute UserSearchCriteriaDto employeeSearchCriteriaDto) {
        try {
            PageableDto<EmployeeDto> employees = employeeService.getAll(employeeSearchCriteriaDto);

            model.addAttribute("employeePageable", employees);
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

    private void setEmployeePositionsToModel(Model model) {
        List<EmployeePositionDto> employeePositions = this.employeePositionService.findAll();
        model.addAttribute("positions", employeePositions);
    }

    private void setProceduresToModel(Model model) {
        List<ProcedureDto> procedures = this.procedureService.findAll();
        model.addAttribute("procedures", procedures);
    }

    @GetMapping("/adding_form")
    public String addingForm(Model model) {
        try {
            setCountriesToModel(model);
            setEmployeePositionsToModel(model);
            setProceduresToModel(model);

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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id, Model model) {
        DeletionStatus deletionStatus = employeeService.deleteById(Long.valueOf(id));
        model.addAttribute("deletionStatus", deletionStatus.getStatusCode());
        return "redirect:/clients";
    }

}