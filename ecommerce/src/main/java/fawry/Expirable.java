package fawry;

import java.time.LocalDate;

/**
 * An interface for products that have an expiration date.
 */
public interface Expirable {
    LocalDate getExpirationDate();

    
    default boolean isExpired() {
        return getExpirationDate().isBefore(LocalDate.now());
    }
}
