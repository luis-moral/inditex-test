package inditex.infrastructure.configuration;

import inditex.infrastructure.util.validator.RequestParameterValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

@Configuration
public class ValidatorConfiguration {

    @Bean
    public RequestParameterValidator requestParameterValidator(ZoneId applicationZoneId) {
        return new RequestParameterValidator(applicationZoneId);
    }
}
