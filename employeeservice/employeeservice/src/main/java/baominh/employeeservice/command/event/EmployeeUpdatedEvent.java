package baominh.employeeservice.command.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdatedEvent {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDisciplined;
}
