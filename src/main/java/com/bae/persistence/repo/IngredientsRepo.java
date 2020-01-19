package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.persistence.domain.Ingredients;

@Repository
public interface IngredientsRepo extends JpaRepository <Ingredients, Integer>{

}
