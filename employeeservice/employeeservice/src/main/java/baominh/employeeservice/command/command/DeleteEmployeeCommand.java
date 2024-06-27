package baominh.employeeservice.command.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteEmployeeCommand {
    private String employeeId;

    public DeleteEmployeeCommand(String employeeId) {
        this.employeeId = employeeId;
    }
}
