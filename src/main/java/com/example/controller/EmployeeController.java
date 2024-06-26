package com.example.controller;

import com.example.entity.DeletionStatus;
import com.example.exception.ControllerCustomException;
import com.example.service.EmployeePositionService;
import com.example.service.EmployeeService;
import com.example.service.ProcedureService;
import com.example.service.dto.EmployeeDto;
import com.example.service.dto.EmployeePositionDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.ProcedureDto;
import com.example.service.dto.search.EmployeeSearchCriteriaDto;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final EmployeePositionService employeePositionService;
    private final ProcedureService procedureService;

    @GetMapping
    public String findAllEmployees(Model model,
                                   @ModelAttribute EmployeeSearchCriteriaDto employeeSearchCriteriaDto) {
        try {
            PageableDto<EmployeeDto> employees = employeeService.getAll(employeeSearchCriteriaDto);

            model.addAttribute("employeePageable", employees);
            model.addAttribute("sortBy", employeeSearchCriteriaDto.getSortBy());
            model.addAttribute("sortType", employeeSearchCriteriaDto.getSortType());
            model.addAttribute("currentCountryId", employeeSearchCriteriaDto.getCountryId());
            model.addAttribute("currentPositionCode", employeeSearchCriteriaDto.getPositionCode());
            model.addAttribute("currentProcedureCode", employeeSearchCriteriaDto.getProcedureCode());

            setEmployeePositionsToModel(model);
            setProceduresToModel(model);

            return "user/employee/employees";
        } catch (Exception e) {
            logger.error("Error while executing find all employees", e);
            throw new ControllerCustomException("Error while executing find all employees", e);
        }
    }

    private void setEmployeePositionsToModel(Model model) {
        List<EmployeePositionDto> employeePositions = this.employeePositionService.findAll();
        model.addAttribute("positions", employeePositions);
    }

    private void setProceduresToModel(Model model) {
        List<ProcedureDto> procedures = this.procedureService.findAll();
        model.addAttribute("procedures", procedures);
    }

    @PreAuthorize("hasAnyRole( 'ADMIN')")
    @GetMapping("/adding_form")
    public String addingForm(Model model) {
        try {
            setEmployeePositionsToModel(model);
            setProceduresToModel(model);

            EmployeeDto employeeDto = new EmployeeDto();
            model.addAttribute("employeeForm", employeeDto);

            return "user/employee/addEmployee";
        } catch (Exception e) {
            logger.error("Error while executing adding form", e);
            throw new ControllerCustomException("Error while executing adding form", e);
        }
    }

    @PreAuthorize("hasAnyRole( 'ADMIN')")
    @PostMapping("/adding_form")
    public String save(Model model, @Validated @ModelAttribute("employeeForm") EmployeeDto employeeDto,
                       BindingResult bindingResult) {
        try {

            if (bindingResult.hasErrors()) {
                setEmployeePositionsToModel(model);
                setProceduresToModel(model);
                return "user/employee/addEmployee";
            }

            employeeService.save(employeeDto);

            return "redirect:/employees";
        } catch (Exception e) {
            logger.error("Error while executing saving", e);
            throw new ControllerCustomException("Error while executing saving", e);
        }
    }

//    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id, Model model) {
        DeletionStatus deletionStatus = employeeService.deleteById(Long.valueOf(id));
        model.addAttribute("deletionStatus", deletionStatus.getStatusCode());
        return "redirect:/employees";
    }

}