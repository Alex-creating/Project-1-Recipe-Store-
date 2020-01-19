package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.persistence.domain.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository <Recipe, Integer> {

}
