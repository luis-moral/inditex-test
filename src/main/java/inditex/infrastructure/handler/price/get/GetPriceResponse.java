package inditex.infrastructure.handler.price.get;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetPriceResponse(
    long id,
    long productId,
    int brand_id,
    LocalDateTime startDate,
    LocalDateTime endDate,
    BigDecimal price,
    String currency
) {}
