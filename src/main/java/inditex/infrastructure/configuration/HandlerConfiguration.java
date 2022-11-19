package inditex.infrastructure.configuration;

import inditex.infrastructure.handler.price.get.GetPriceHandler;
import inditex.infrastructure.handler.status.get.GetStatusHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfiguration {

    @Bean
    public GetStatusHandler getStatusHandler() {
        return new GetStatusHandler();
    }

    @Bean
    public GetPriceHandler getPriceHandler() {
        return new GetPriceHandler();
    }
}
