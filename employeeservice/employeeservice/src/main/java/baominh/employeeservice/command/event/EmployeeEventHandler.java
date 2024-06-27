package baominh.employeeservice.command.event;

import baominh.employeeservice.command.data.Employee;
import baominh.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeEventHandler {
     @Autowired
     EmployeeRepository employeeRepository;

     @EventHandler
     public void onEmployeeCreatedEvent(EmployeeCreatedEvent event) {
         Employee employee = new Employee();
         BeanUtils.copyProperties(event, employee);
         employeeRepository.save(employee);
     }

     @EventHandler
     public void onEmployeeUpdatedEvent(EmployeeUpdatedEvent event) {
         Optional<Employee> employee = employeeRepository.findById(event.getEmployeeId());
         if(employee.isPresent()) {
             Employee updatedEmployee = employee.get();
             updatedEmployee.setFirstName(event.getFirstName());
             updatedEmployee.setLastName(event.getLastName());
             updatedEmployee.setKin(event.getKin());
             updatedEmployee.setDisciplined(event.isDisciplined());
             employeeRepository.save(updatedEmployee);
         }
         else {
             throw new IllegalArgumentException("Book not found with id: " + event.getEmployeeId());
         }
     }

     @EventHandler
    public void onEmployeeeDeletedEvent(EmployeeDeletedEvent event) {
        Optional<Employee> employee = employeeRepository.findById(event.getEmployeeId());
        if(employee.isPresent()) {
            employeeRepository.deleteById(event.getEmployeeId());
        }
        else {
            throw new IllegalArgumentException("Employee not found with id: " + event.getEmployeeId());
        }
    }
}
