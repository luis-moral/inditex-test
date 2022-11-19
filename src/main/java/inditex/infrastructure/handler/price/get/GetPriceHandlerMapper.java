package inditex.infrastructure.handler.price.get;

import inditex.domain.price.GetPriceFilter;
import inditex.domain.price.Price;
import inditex.infrastructure.util.validator.RequestParameterValidator;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GetPriceHandlerMapper {

    private final ZoneId zoneId;
    private final RequestParameterValidator validator;

    public GetPriceHandlerMapper(ZoneId zoneId, RequestParameterValidator validator) {
        this.zoneId = zoneId;
        this.validator = validator;
    }

    public GetPriceFilter toGetPriceFilter(ServerRequest serverRequest) {
        return
            new GetPriceFilter(
                validator.mandatoryLong(serverRequest.queryParam("productId"), "productId"),
                validator.mandatoryInteger(serverRequest.queryParam("brandId"), "brandId"),
                validator.mandatoryDate(serverRequest.queryParam("date"), "date")
            );
    }

    public GetPriceResponse toGetPriceResponse(Price price) {
        return
            new GetPriceResponse(
                price.id(),
                price.productId(),
                price.brandId(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(price.startDate()), zoneId),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(price.endDate()), zoneId),
                price.price(),
                price.currency().getCurrencyCode()
            );
    }
}
