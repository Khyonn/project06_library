package fr.nmocs.library.consumer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.nmocs.library.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmailIgnoreCase(String email);

	List<User> findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);

	// Native query => the field userType doesn't exist on user entity
	@Modifying
	@Query(value = "UPDATE Users SET userType = :newType WHERE id = :userId", nativeQuery = true)
	void updateUserType(@Param("userId") Integer userId, @Param("newType") String userType);

	boolean existsByIdAndStatus(Integer id, String status);
}
