package inditex.infrastructure.configuration;

import inditex.domain.price.PriceRepository;
import inditex.infrastructure.repository.R2dbcPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public PriceRepository priceRepository() {
        return new R2dbcPriceRepository();
    }
}
