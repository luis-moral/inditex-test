package inditex.infrastructure.util.validator.expection;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
