package com.saga.cleanindia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.cleanindia.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
