package inditex.infrastructure.configuration;

import inditex.domain.price.GetPriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public GetPriceService getPriceService() {
        return new GetPriceService();
    }
}
