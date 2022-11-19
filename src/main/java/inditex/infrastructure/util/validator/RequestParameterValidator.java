package inditex.infrastructure.util.validator;

import inditex.infrastructure.util.validator.expection.InvalidParameterException;
import inditex.infrastructure.util.validator.expection.MandatoryParameterException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class RequestParameterValidator {

    private final ZoneId zoneId;

    public RequestParameterValidator(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public long mandatoryProductId(Optional<String> productId) {
        String value = productId.orElseThrow(() -> new MandatoryParameterException("productId"));

        return validateLong(value, "productId", "a valid number");
    }

    public int mandatoryBrandId(Optional<String> brandId) {
        String value = brandId.orElseThrow(() -> new MandatoryParameterException("brandId"));

        return validateInteger(value, "brandId", "a valid number");
    }

    public long mandatoryDate(Optional<String> date) {
        String value = date.orElseThrow(() -> new MandatoryParameterException("date"));

        return validateDate(value, "date", "a valid date");
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
