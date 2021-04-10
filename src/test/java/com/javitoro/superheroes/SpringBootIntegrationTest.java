package com.javitoro.superheroes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.javitoro.superheroes.domain.Superhero;
import com.javitoro.superheroes.repository.SuperheroRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootIntegrationTest {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Test
    void superheroIntegrationTest() {
        Superhero newSuperhero = superheroRepository.save(new Superhero(1, "Test Superhero"));
        Superhero storedSuperhero = superheroRepository.findById(newSuperhero.getId()).orElse(null);
        assertNotNull(storedSuperhero);
        assertEquals(newSuperhero.getId(), storedSuperhero.getId());
    }

}
