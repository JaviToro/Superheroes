package com.javitoro.superheroes.repository;

import com.javitoro.superheroes.domain.Superhero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SuperheroRepository extends CrudRepository<Superhero, Integer> {

    @Query(value = "SELECT * FROM Superhero WHERE LOWER(name) LIKE LOWER(CONCAT('%',:name,'%'))", nativeQuery = true)
    Iterable<Superhero> findByNameContaining(@Param("name") String name);

}
