package com.javitoro.superheroes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    public Superhero() {

    }

    public Superhero(@NotBlank String name) {
        this.name = name;
    }

    public Superhero(@NotBlank Integer id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(@NotBlank Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }
}
