package cl.bci.user.crud.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus;
    public ApplicationException(String message, HttpStatus status){
        super(message);
        this.httpStatus=status;
    }
}
