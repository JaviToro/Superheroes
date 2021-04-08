package com.javitoro.superheroes;

import com.javitoro.superheroes.controller.SuperheroController;
import com.javitoro.superheroes.domain.Superhero;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuperheroesApplicationTests {

    @Autowired
    private SuperheroController superheroController;

    @Test
    @Order(1)
    public void addSuperhero() {
        Superhero newHero = new Superhero(1, "Test Hero");
        Superhero superhero = superheroController.addNewSuperhero(newHero);
        assertThat(superhero).isNotNull();
    }

    @Test
    @Order(2)
    public void deleteSuperhero() {
        superheroController.deleteSuperHeroById(1);
        List<Superhero> superheroes = (List<Superhero>) superheroController.getAllSuperheroes();
        assertThat(superheroes).size().isEqualTo(0);
    }

    @Test
    @Order(3)
    public void modifySuperheroes() {
        Superhero hero1 = new Superhero(2, "Test Hero");
        Superhero superhero1 = superheroController.addNewSuperhero(hero1);
        Superhero hero2 = new Superhero(3, "Test Hero");
        Superhero superhero2 = superheroController.addNewSuperhero(hero2);
        superheroController.renameSuperhero(3, "Test Hero modified");
        assertThat(superheroController.getSuperheroById(2).getName()).isNotEqualTo(superheroController.getSuperheroById(3).getName());
    }

    @Test
    @Order(4)
    public void getAllSuperheroes() {
        List<Superhero> superheroes = (List<Superhero>) superheroController.getAllSuperheroes();
        assertThat(superheroes).size().isEqualTo(2);
    }

    @Test
    @Order(5)
    public void getAllSuperheroesContainingModified() {
        List<Superhero> superheroes = (List<Superhero>) superheroController.getSuperheroesByName("modified");
        assertThat(superheroes).size().isEqualTo(1);
    }
}
