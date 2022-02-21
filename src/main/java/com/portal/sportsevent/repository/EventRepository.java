package com.portal.sportsevent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portal.sportsevent.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
	@Query(value="SELECT e FROM Event e "
			+ " WHERE e.active <> false ")
	public abstract List<Event> findActiveEvents();
}
