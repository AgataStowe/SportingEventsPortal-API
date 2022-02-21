package com.portal.sportsevent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portal.sportsevent.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="SELECT u FROM User u "
			+ " WHERE (:ID IS NULL OR u.id <> :ID)"
			+ " AND u.email = :EMAIL"
			+ " AND u.active <> false")
	public abstract Optional<User> findByEmail(@Param("ID") Long id, @Param("EMAIL") String email);
	
	@Query(value="SELECT u FROM User u "
			+ " WHERE u.email = :EMAIL "
			+ " AND u.active <> false")
	public abstract Optional<User> getByEmail(@Param("EMAIL") String email);
	
	@Query(value="SELECT u FROM User u "
			+ " WHERE u.active <> false ")
	public abstract List<User> findActiveUsers();
}
