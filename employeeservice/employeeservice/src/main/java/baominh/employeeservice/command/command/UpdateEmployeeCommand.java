package baominh.employeeservice.command.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeCommand {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDisciplined;

    public UpdateEmployeeCommand(String employeeId, String firstName, String lastName, String kin, boolean isDisciplined) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kin = kin;
        this.isDisciplined = isDisciplined;
    }
}
