package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.persistence.domain.Recipe;

public interface RecipeRepo extends JpaRepository <Recipe, Long> {

}
