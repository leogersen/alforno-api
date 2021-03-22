package com.leogersen.alfornoapi.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    public List<MenuItem> findByRestaurant_IdOrderByName(Integer restaurantId);
    public List<MenuItem> findByRestaurant_IdAndHighlightOrderByName(Integer restaurantId, boolean highlight);
    public List<MenuItem> findByRestaurant_IdAndHighlightAndCategoryOrderByName(Integer restaurantId, boolean highlight, String category);

    @Query("SELECT DISTINCT im.category FROM MenuItem im where im.restaurant.id = ?1 ORDER by im.category")
    public List<String> findCategories(Integer restaurantId);


}
