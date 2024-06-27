package baominh.employeeservice.command.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDeletedEvent {
    private String employeeId;
}
