package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends RuntimeException {

    NotAuthorizedException() {
        super();
    }

    NotAuthorizedException(String message) {
        super(message);
    }

}
