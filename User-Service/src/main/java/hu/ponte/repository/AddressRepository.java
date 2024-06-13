package hu.ponte.repository;

import hu.ponte.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Address database abstraction layer.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}