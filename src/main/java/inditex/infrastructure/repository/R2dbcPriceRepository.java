package inditex.infrastructure.repository;

import inditex.domain.price.GetPriceFilter;
import inditex.domain.price.Price;
import inditex.domain.price.PriceRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Flux;

public class R2dbcPriceRepository implements PriceRepository {

    private final R2dbcEntityTemplate entityTemplate;

    public R2dbcPriceRepository(R2dbcEntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    @Override
    public Flux<Price> prices(GetPriceFilter filter) {
        return null;
    }
}
