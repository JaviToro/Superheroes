package com.javitoro.superheroes.service;

import com.javitoro.superheroes.aop.LogExecutionTime;
import com.javitoro.superheroes.domain.Superhero;
import com.javitoro.superheroes.exception.ResourceNotFoundException;
import com.javitoro.superheroes.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SuperheroService {

    @Autowired
    private SuperheroRepository superheroRepository;

    @LogExecutionTime
    public Iterable<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

    @LogExecutionTime
    public Superhero getSuperheroById(Integer id) {
        Superhero superhero = superheroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No superhero found on ID: " + id));
        return superhero;
    }

    public Iterable<Superhero> getSuperheroesByName(String name) {
        return superheroRepository.findByNameContaining(name);
    }

    public Superhero addNewSuperhero(Superhero superhero) {
        return superheroRepository.save(superhero);
    }

    @Transactional()
    public Superhero renameSuperheroById(Integer id, String name) {
        Superhero superhero = superheroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No superhero found on ID: " + id));
        superhero.setName(name);
        return superheroRepository.save(superhero);
    }

    @Transactional()
    public void deleteSuperheroById(Integer id) {
        Superhero superhero = superheroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No superhero found on ID: " + id));
        superheroRepository.delete(superhero);
    }

}
