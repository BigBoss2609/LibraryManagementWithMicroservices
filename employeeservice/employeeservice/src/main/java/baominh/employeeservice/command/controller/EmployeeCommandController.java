package baominh.employeeservice.command.controller;

import baominh.employeeservice.command.command.AddEmployeeCommand;
import baominh.employeeservice.command.command.DeleteEmployeeCommand;
import baominh.employeeservice.command.command.UpdateEmployeeCommand;
import baominh.employeeservice.command.model.AddEmployeeRequestModel;
import baominh.employeeservice.command.model.UpdateEmployeeRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {
    @Autowired
    CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@RequestBody AddEmployeeRequestModel model) {
        String employeeId = UUID.randomUUID().toString();
        AddEmployeeCommand command = new AddEmployeeCommand(employeeId, model.getFirstName(), model.getLastName(), model.getKin(), model.isDisciplined());
        commandGateway.sendAndWait(command);
        return "Employee added successfully!";
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId, @RequestBody UpdateEmployeeRequestModel model) {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new IllegalArgumentException("Employee id must not be null or empty");
        }
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId, model.getFirstName(), model.getLastName(), model.getKin(), model.isDisciplined());
        commandGateway.sendAndWait(command);
        return "Employee updated successfully!";
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new IllegalArgumentException("Employee id must not be null or empty");
        }
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        commandGateway.sendAndWait(command);
        return "Employee deleted successfully!";
    }
}
