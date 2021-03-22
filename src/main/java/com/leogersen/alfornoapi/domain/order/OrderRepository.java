package com.leogersen.alfornoapi.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /*
    @Query("SELECT o FROM Order o WHERE o.client.id =?1 ORDER BY o.data DESC")
    public List<Order> listOfOrders(Integer clientId);

    public List<Order> findByRestaurant_IdOrderByDateDesc(Integer restaurantId);

    public Order findByIdAndRestaurant_Id(Integer orderId, Integer restaurantId);

    @Query("SELECT o FROM Order o WHERE o.restaurant.id = ?1 AND o.date BETWEEN ?2 AND ?3")
    public List<Order> findByDateInterval(Integer restaurantId, LocalDateTime initialDate, LocalDateTime finalDate);

    @Query("SELECT i.menuItem.name, SUM(i.quantity), SUM(i.price * i.quantity) FROM Order o INNER JOIN o.items i " +
            "WHERE o.restaurant.id = ?1 AND i.menuItem.id = ?2 AND o.date BETWEEN ?3 AND ?4 GROUP BY i.menuItem.name")
    public List<Object[]> findItemsForAmount(Integer restaurantId, Integer meuItemId, LocalDateTime initialDate, LocalDateTime finalDate);


    @Query("SELECT i.menuItem.name, SUM(i.quantity), SUM(i.price * i.quantity) FROM Order o INNER JOIN o.items i " +
            "WHERE o.restaurant.id = ?1 AND o.date BETWEEN ?2 AND ?3 GROUP BY i.menuItem.name")
    public List<Object[]> findItemsForAmount(Integer restaurantId, LocalDateTime initialDate, LocalDateTime finalDate); */





}
