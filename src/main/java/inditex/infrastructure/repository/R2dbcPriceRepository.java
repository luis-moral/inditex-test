package inditex.infrastructure.repository;

import inditex.domain.price.GetPriceFilter;
import inditex.domain.price.Price;
import inditex.domain.price.PriceRepository;
import reactor.core.publisher.Flux;

public class R2dbcPriceRepository implements PriceRepository {

    @Override
    public Flux<Price> prices(GetPriceFilter filter) {
        return null;
    }
}
