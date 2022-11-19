package inditex.domain.price;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Price {

    private long productId;
    private int brandId;
    private long startDate;
    private long endDate;
    private int priceRateId;
    private BigDecimal price;
    private Currency currency;

    public Price(
        long productId,
        int brandId,
        long startDate,
        long endDate,
        int priceRateId,
        BigDecimal price,
        Currency currency
    ) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceRateId = priceRateId;
        this.price = price;
        this.currency = currency;
    }

    public long productId() {
        return productId;
    }

    public int brandId() {
        return brandId;
    }

    public long startDate() {
        return startDate;
    }

    public long endDate() {
        return endDate;
    }

    public int priceRateId() {
        return priceRateId;
    }

    public BigDecimal price() {
        return price;
    }

    public Currency currency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return productId == price1.productId && brandId == price1.brandId && startDate == price1.startDate && endDate == price1.endDate && priceRateId == price1.priceRateId && Objects.equals(price, price1.price) && Objects.equals(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, startDate, endDate, priceRateId, price, currency);
    }
}
