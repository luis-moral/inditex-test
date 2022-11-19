package inditex.infrastructure.util.validator;

import inditex.infrastructure.util.validator.exception.InvalidParameterException;
import inditex.infrastructure.util.validator.exception.MandatoryParameterException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class RequestParameterValidator {

    private final ZoneId zoneId;

    public RequestParameterValidator(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public long mandatoryLong(Optional<String> optionalValue, String parameterName) {
        String value = optionalValue.orElseThrow(() -> new MandatoryParameterException(parameterName));

        return validateLong(value, parameterName, "a valid number");
    }

    public int mandatoryInteger(Optional<String> optionalValue, String parameterName) {
        String value = optionalValue.orElseThrow(() -> new MandatoryParameterException(parameterName));

        return validateInteger(value, parameterName, "a valid number");
    }

    public long mandatoryDate(Optional<String> optionalValue, String parameterName) {
        String value = optionalValue.orElseThrow(() -> new MandatoryParameterException(parameterName));

        return validateDate(value, parameterName, "a valid date");
    }

    private int validateInteger(String value, String errorParameter, String errorExpected) {
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            throw new InvalidParameterException(errorParameter, errorExpected);
        }
    }

    private long validateLong(String value, String errorParameter, String errorExpected) {
        try {
            return Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            throw new InvalidParameterException(errorParameter, errorExpected);
        }
    }

    private long validateDate(String value, String errorParameter, String errorExpected) {
        try {
            return LocalDateTime.parse(value).atZone(zoneId).toInstant().toEpochMilli();
        }
        catch (DateTimeParseException e) {
            throw new InvalidParameterException(errorParameter, errorExpected);
        }
    }
}
