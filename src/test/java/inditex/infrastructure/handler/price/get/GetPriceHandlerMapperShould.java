package inditex.infrastructure.handler.price.get;

import inditex.domain.price.GetPriceFilter;
import inditex.domain.price.Price;
import inditex.infrastructure.util.validator.RequestParameterValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Currency;

public class GetPriceHandlerMapperShould {

    private ZoneId zoneId;
    private RequestParameterValidator validator;

    private GetPriceHandlerMapper mapper;

    @BeforeEach
    public void setUp() {
        zoneId = ZoneId.of("CET");
        validator = Mockito.mock(RequestParameterValidator.class);

        mapper = new GetPriceHandlerMapper(zoneId, validator);
    }

    @Test public void
    map_request_to_get_price_filter() {
        MockServerRequest serverRequest = MockServerRequest.builder().build();

        GetPriceFilter expected =
            new GetPriceFilter(
                1500,
                5,
                1668863977000L
            );

        Mockito
            .when(validator.mandatoryProductId(Mockito.any()))
            .thenReturn(1500L);
        Mockito
            .when(validator.mandatoryBrandId(Mockito.any()))
            .thenReturn(5);
        Mockito
            .when(validator.mandatoryDate(Mockito.any()))
            .thenReturn(1668863977000L);

        Assertions
            .assertThat(mapper.toGetPriceFilter(serverRequest))
            .isEqualTo(expected);

        Mockito
            .verify(validator, Mockito.times(1))
            .mandatoryProductId(Mockito.any());
        Mockito
            .verify(validator, Mockito.times(1))
            .mandatoryBrandId(Mockito.any());
        Mockito
            .verify(validator, Mockito.times(1))
            .mandatoryDate(Mockito.any());
    }

    @Test public void
    map_price_to_get_price_response() {
        Price price =
            new Price(
                1500,
                5,
                1668853977000L,
                1668863977000L,
                3,
                new BigDecimal("25.5"),
                Currency.getInstance("EUR")
            );

        GetPriceResponse expected =
            new GetPriceResponse(
                1500,
                5,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(1668853977000L), zoneId),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(1668863977000L), zoneId),
                3,
                new BigDecimal("25.5"),
                "EUR"
            );

        Assertions
            .assertThat(mapper.toGetPriceResponse(price))
            .isEqualTo(expected);
    }

}