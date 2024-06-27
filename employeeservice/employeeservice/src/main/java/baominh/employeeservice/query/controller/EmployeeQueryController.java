package baominh.employeeservice.query.controller;

import baominh.employeeservice.query.model.GetEmployeeResponseModel;
import baominh.employeeservice.query.query.GetAllEmployeesQuery;
import baominh.employeeservice.query.query.GetEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public GetEmployeeResponseModel getEmployee(@PathVariable String employeeId) {
        GetEmployeeQuery query = new GetEmployeeQuery(employeeId);
        return queryGateway.query(query, GetEmployeeResponseModel.class).join();
    }

    @GetMapping()
    public List<GetEmployeeResponseModel> getAllEmployees(){
        GetAllEmployeesQuery query = new GetAllEmployeesQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(GetEmployeeResponseModel.class)).join();
    }
}
