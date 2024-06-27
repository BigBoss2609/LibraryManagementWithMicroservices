package baominh.employeeservice.query.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetEmployeeResponseModel {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDisciplined;
}
