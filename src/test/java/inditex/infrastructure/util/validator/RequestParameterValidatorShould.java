package inditex.infrastructure.util.validator;

import inditex.infrastructure.util.validator.expection.InvalidParameterException;
import inditex.infrastructure.util.validator.expection.MandatoryParameterException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

public class RequestParameterValidatorShould {

    private RequestParameterValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RequestParameterValidator(ZoneId.of("CET"));
    }

    @Test public void
    validate_mandatory_product_id() {
        Optional<String> valid = Optional.of("1500");
        Optional<String> invalid = Optional.of("Date");
        Optional<String> empty = Optional.empty();

        Assertions
            .assertThat(validator.mandatoryProductId(valid))
            .isEqualTo(1500);

        Assertions
            .assertThatThrownBy(() -> validator.mandatoryProductId(invalid))
            .isInstanceOf(InvalidParameterException.class)
            .hasMessage("Parameter [productId] must be a valid number");

        Assertions
            .assertThatThrownBy(() -> validator.mandatoryProductId(empty))
            .isInstanceOf(MandatoryParameterException.class)
            .hasMessage("Parameter [productId] is mandatory");
    }

    @Test public void
    validate_mandatory_brand_id() {
        Optional<String> valid = Optional.of("1500");
        Optional<String> invalid = Optional.of("Date");
        Optional<String> empty = Optional.empty();

        Assertions
            .assertThat(validator.mandatoryBrandId(valid))
            .isEqualTo(1500);

        Assertions
            .assertThatThrownBy(() -> validator.mandatoryBrandId(invalid))
            .isInstanceOf(InvalidParameterException.class)
            .hasMessage("Parameter [brandId] must be a valid number");

        Assertions
            .assertThatThrownBy(() -> validator.mandatoryBrandId(empty))
            .isInstanceOf(MandatoryParameterException.class)
            .hasMessage("Parameter [brandId] is mandatory");
    }

    @Test public void
    validate_mandatory_date() {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(1668853977000L), ZoneId.of("CET"));

        Optional<String> valid = Optional.of(date.toString());
        Optional<String> invalid = Optional.of("Date");
        Optional<String> empty = Optional.empty();

        Assertions
            .assertThat(validator.mandatoryDate(valid))
            .isEqualTo(1668853977000L);

        Assertions
            .assertThatThrownBy(() -> validator.mandatoryDate(invalid))
            .isInstanceOf(InvalidParameterException.class)
            .hasMessage("Parameter [date] must be a valid date");

        Assertions
            .assertThatThrownBy(() -> validator.mandatoryDate(empty))
            .isInstanceOf(MandatoryParameterException.class)
            .hasMessage("Parameter [date] is mandatory");
    }
}