package hu.ponte.repository;

import hu.ponte.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PhoneNumber database abstraction layer.
 */
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}