package pro.sky.recipessite.services.impl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
public class IdIsIncorrectException extends Exception {
    public IdIsIncorrectException(String message) {
        super(message);
    }
}
