package baominh.employeeservice.command.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeRequestModel {
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDisciplined;

    public UpdateEmployeeRequestModel(String firstName, String lastName, String kin, boolean isDisciplined) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.kin = kin;
        this.isDisciplined = isDisciplined;
    }
}
