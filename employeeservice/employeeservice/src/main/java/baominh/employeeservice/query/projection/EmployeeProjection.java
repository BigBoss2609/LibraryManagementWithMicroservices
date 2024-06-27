package baominh.employeeservice.query.projection;

import baominh.employeeservice.command.data.Employee;
import baominh.employeeservice.command.data.EmployeeRepository;
import baominh.employeeservice.query.model.GetEmployeeResponseModel;
import baominh.employeeservice.query.query.GetAllEmployeesQuery;
import baominh.employeeservice.query.query.GetEmployeeQuery;
import com.baominh.commonservice.model.EmployeeResponseCommonModel;
import com.baominh.commonservice.query.GetEmployeeDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponseCommonModel handle(GetEmployeeDetailQuery query) {
        Optional<Employee> employee = employeeRepository.findById(query.getEmployeeId());
        if(employee.isPresent()){
            Employee employeeDetail = employee.get();
            EmployeeResponseCommonModel response = new EmployeeResponseCommonModel();
            BeanUtils.copyProperties(employeeDetail, response);
            return response;
        }
        else {
            throw new IllegalArgumentException("Employee not found with id: " + query.getEmployeeId());
        }

    }
    @QueryHandler
    public GetEmployeeResponseModel handle(GetEmployeeQuery query) {
        Optional<Employee> employee = employeeRepository.findById(query.getEmployeeId());
        if(employee.isPresent()){
           Employee employeeDetail = employee.get();
           GetEmployeeResponseModel response = new GetEmployeeResponseModel();
           BeanUtils.copyProperties(employeeDetail, response);
           return response;
        }
        else {
           throw new IllegalArgumentException("Employee not found with id: " + query.getEmployeeId());
        }
    }

    @QueryHandler
    public List<GetEmployeeResponseModel> handle(GetAllEmployeesQuery query) {
        List<Employee> employees = employeeRepository.findAll();
        List<GetEmployeeResponseModel> response = new ArrayList<>();
        for(Employee employee : employees) {
            GetEmployeeResponseModel model = new GetEmployeeResponseModel();
            BeanUtils.copyProperties(employee, model);
            response.add(model);
        }
        return response;
    }
}
