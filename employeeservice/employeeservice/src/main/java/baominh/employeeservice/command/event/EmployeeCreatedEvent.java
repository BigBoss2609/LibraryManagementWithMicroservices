package baominh.employeeservice.command.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.reflection.qual.GetClass;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCreatedEvent {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDisciplined;

}
