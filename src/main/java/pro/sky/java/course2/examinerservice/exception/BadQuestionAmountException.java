package pro.sky.java.course2.examinerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Критерий: выбрасывается исключение со статусом BAD_REQUEST (400)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadQuestionAmountException extends RuntimeException {
    public BadQuestionAmountException(String message) {
        super(message);
    }
}