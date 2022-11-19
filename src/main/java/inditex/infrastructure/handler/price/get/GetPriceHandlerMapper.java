package inditex.infrastructure.handler.price.get;

import inditex.domain.price.GetPriceFilter;
import inditex.domain.price.Price;
import inditex.infrastructure.util.validator.RequestParameterValidator;
import org.springframework.web.reactive.function.server.ServerRequest;

public class GetPriceHandlerMapper {

    private RequestParameterValidator validator;

    public GetPriceHandlerMapper(RequestParameterValidator validator) {
        this.validator = validator;
    }

    public GetPriceFilter toGetPriceFilter(ServerRequest serverRequest) {
        throw new UnsupportedOperationException();
    }

    public GetPriceResponse toGetPriceResponse(Price price) {
        throw new UnsupportedOperationException();
    }
}
