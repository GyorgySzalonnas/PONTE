package hu.ponte.repository;

import hu.ponte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User database abstraction layer.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}