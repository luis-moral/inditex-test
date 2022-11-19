package inditex.domain.price;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.List;

public class GetPriceServiceShould {

    private final static Price FIRST_PRICE =
        price(35455, 1, "2020-06-14T00:00:00+01", "2020-12-31T23:59:59+01", 1, "35.5", 0);
    private final static Price SECOND_PRICE =
        price(35455, 1, "2020-06-14T00:00:00+01", "2020-12-31T23:59:59+01", 1, "35.5", 1);
    private final static Price THIRD_PRICE =
        price(35455, 1, "2020-06-14T00:00:00+01", "2020-12-31T23:59:59+01", 1, "35.5", 1);
    private final static Price FOURTH_PRICE =
        price(35455, 1, "2020-06-14T00:00:00+01", "2020-12-31T23:59:59+01", 1, "35.5", 1);

    private PriceRepository priceRepository;

    private GetPriceService getPriceService;

    @BeforeEach
    public void setUp() {
        priceRepository = Mockito.mock(PriceRepository.class);

        getPriceService = new GetPriceService(priceRepository);
    }

    @Test public void
    return_the_price_for_a_filtered_product() {
        assertPriceFor(filter(14, 10), List.of(FIRST_PRICE), FIRST_PRICE);
        assertPriceFor(filter(14, 16), List.of(FIRST_PRICE, SECOND_PRICE), SECOND_PRICE);
        assertPriceFor(filter(14, 21), List.of(FIRST_PRICE), FIRST_PRICE);
        assertPriceFor(filter(15, 10), List.of(FIRST_PRICE, THIRD_PRICE), THIRD_PRICE);
        assertPriceFor(filter(16, 21), List.of(FIRST_PRICE, FOURTH_PRICE), FOURTH_PRICE);
    }

    @Test public void
    return_empty_if_no_products_match() {
        GetPriceFilter filter =
            new GetPriceFilter(25, 7, ZonedDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneId.of("CET")).toInstant().toEpochMilli());

        Mockito
            .when(priceRepository.prices(filter))
            .thenReturn(Flux.empty());

        StepVerifier
            .create(getPriceService.price(filter))
            .verifyComplete();

        Mockito
            .verify(priceRepository, Mockito.times(1))
            .prices(filter);
    }

    private void assertPriceFor(GetPriceFilter filter, List<Price> repositoryPrices, Price expected) {
        Mockito
            .when(priceRepository.prices(filter))
            .thenReturn(Flux.fromIterable(repositoryPrices));

        StepVerifier
            .create(getPriceService.price(filter))
            .expectNext(expected)
            .verifyComplete();

        Mockito
            .verify(priceRepository, Mockito.times(1))
            .prices(filter);
    }

    private GetPriceFilter filter(int dayOfMonth, int hour) {
        return
            new GetPriceFilter(
                35455,
                1,
                ZonedDateTime.of(2020, 6, dayOfMonth, hour, 0, 0, 0, ZoneId.of("CET")).toInstant().toEpochMilli()
            );
    }

    private static Price price(
        long productId,
        int brandId,
        String startDate,
        String endDate,
        int priceRateId,
        String price,
        int priority
    ) {
        return
            new Price(
                productId,
                brandId,
                ZonedDateTime.parse(startDate).toInstant().toEpochMilli(),
                ZonedDateTime.parse(endDate).toInstant().toEpochMilli(),
                priceRateId,
                new BigDecimal(price),
                Currency.getInstance("EUR"),
                priority
            );
    }
}