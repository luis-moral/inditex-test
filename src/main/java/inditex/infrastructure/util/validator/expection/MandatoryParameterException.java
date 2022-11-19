package inditex.infrastructure.util.validator.expection;

public class MandatoryParameterException extends ValidationException {

    public MandatoryParameterException(String parameter) {
        super("Parameter [" + parameter + "] is mandatory");
    }
}
