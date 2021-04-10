package com.javitoro.superheroes.controller;

import com.javitoro.superheroes.domain.Superhero;
import com.javitoro.superheroes.exception.ResourceNotFoundException;
import com.javitoro.superheroes.service.SuperheroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/superhero")
@Tag(name = "superhero", description = "the superhero API")
public class SuperheroController {

    private SuperheroService superheroService;

    public SuperheroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @Operation(summary = "Get all superheroes")
    @GetMapping("/all")
    public @ResponseBody
    Iterable<Superhero> getAllSuperheroes() {
        return superheroService.getAllSuperheroes();
    }

    @Cacheable("superhero")
    @Operation(summary = "Get a superhero by its id")
    @GetMapping("/{id}")
    public @ResponseBody
    Superhero getSuperheroById(@PathVariable Integer id) throws ResourceNotFoundException {
        return superheroService.getSuperheroById(id);
    }

    @Operation(summary = "Get all superheroes containing the string in its name")
    @GetMapping("/search/{name}")
    public @ResponseBody
    Iterable<Superhero> getSuperheroesByName(@PathVariable String name) {
        return superheroService.getSuperheroesByName(name);
    }

    @Secured("ROLE_ADMIN")
    @Operation(summary = "Add a new superhero")
    @PostMapping("/add")
    public @ResponseBody
    Superhero addNewSuperhero(@RequestBody Superhero superhero) {
        return superheroService.addNewSuperhero(superhero);
    }

    @Secured("ROLE_ADMIN")
    @Operation(summary = "Update the name of a superhero using its id")
    @PutMapping("/rename/{id}/{name}")
    public @ResponseBody
    Superhero renameSuperhero(@PathVariable Integer id, @PathVariable String name) {
        return superheroService.renameSuperheroById(id, name);
    }

    @Secured("ROLE_ADMIN")
    @Operation(summary = "Delete a superhero by its id")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    void deleteSuperHeroById(@PathVariable Integer id) {
        superheroService.deleteSuperheroById(id);
    }
}
