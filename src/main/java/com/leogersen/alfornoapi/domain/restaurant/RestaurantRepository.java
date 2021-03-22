package com.leogersen.alfornoapi.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    public Restaurant findByEmail(String email);
    public List<Restaurant> findByNameIgnoreCaseContaining(String name);
    public List<Restaurant> findByCategories_Id(Integer categoryId);
}
