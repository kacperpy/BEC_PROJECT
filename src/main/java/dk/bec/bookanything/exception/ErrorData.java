package dk.bec.bookanything.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorData {

    private String message;

    private Integer value;

    private HttpStatus status;

    private List<String> errors;
}
