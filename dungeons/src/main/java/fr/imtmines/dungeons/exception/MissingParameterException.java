package fr.imtmines.dungeons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Missing paramater(s)")
public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String... missingFields) {
        super("Request body must contain the following fields: " + String.join(", ", missingFields));
    }
}