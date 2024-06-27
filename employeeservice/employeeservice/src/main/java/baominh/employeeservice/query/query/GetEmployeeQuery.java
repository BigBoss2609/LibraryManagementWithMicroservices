package baominh.employeeservice.query.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetEmployeeQuery {
    private String employeeId;
    public GetEmployeeQuery(String employeeId) {
        this.employeeId = employeeId;
    }
}
