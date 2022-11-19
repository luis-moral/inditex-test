package inditex.infrastructure.configuration;

import inditex.infrastructure.handler.price.get.GetPriceHandlerMapper;
import inditex.infrastructure.util.validator.RequestParameterValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public GetPriceHandlerMapper getPriceHandlerMapper(
        RequestParameterValidator requestParameterValidator
    ) {
        return new GetPriceHandlerMapper(requestParameterValidator);
    }
}
