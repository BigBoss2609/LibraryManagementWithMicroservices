package event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookUpdatedStatusEvent {
    private String bookId;
    private Boolean isReady;
    private String employeeId;
    private String borrowingId;
}
