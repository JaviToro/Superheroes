package com.javitoro.superheroes.controller;

import com.javitoro.superheroes.domain.Superhero;
import com.javitoro.superheroes.exception.ResourceNotFoundException;
import com.javitoro.superheroes.repository.SuperheroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/superhero")
@Tag(name = "superhero", description = "the superhero API")
public class SuperheroController {

    private SuperheroRepository superheroRepository;

    public SuperheroController(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    @Operation(summary = "Get all superheroes")
    @GetMapping("/all")
    public @ResponseBody
    Iterable<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

    @Cacheable("superhero")
    @Operation(summary = "Get a superhero by its id")
    @GetMapping("/{id}")
    public @ResponseBody
    Superhero getSuperheroById(@PathVariable Integer id) throws ResourceNotFoundException {
        Superhero superhero = superheroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No superhero found on ID: " + id));
        return superhero;
    }

    @Operation(summary = "Get all superheroes containing the string in its name")
    @GetMapping("/search/{name}")
    public @ResponseBody
    Iterable<Superhero> getSuperheroesByName(@PathVariable String name) {
        return superheroRepository.findByNameContaining(name);
    }

    @Operation(summary = "Add a new superhero")
    @PostMapping("/add")
    public @ResponseBody
    Superhero addNewSuperhero(@RequestBody Superhero superhero) {
        return superheroRepository.save(superhero);
    }

    @Operation(summary = "Update the name of a superhero using its id")
    @PutMapping("/rename/{id}/{name}")
    public @ResponseBody
    Superhero renameSuperhero(@PathVariable Integer id, @PathVariable String name) {
        Superhero superhero = superheroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No superhero found on ID: " + id));
        superhero.setName(name);
        return superheroRepository.save(superhero);
    }

    @Operation(summary = "Delete a superhero by its id")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    void deleteSuperHeroById(@PathVariable Integer id) {
        Superhero superhero = superheroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No superhero found on ID: " + id));
        superheroRepository.delete(superhero);
    }
}
