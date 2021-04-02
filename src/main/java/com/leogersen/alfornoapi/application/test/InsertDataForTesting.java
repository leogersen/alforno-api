package com.leogersen.alfornoapi.application.test;


import com.leogersen.alfornoapi.domain.client.Client;
import com.leogersen.alfornoapi.domain.client.ClientRepository;
import com.leogersen.alfornoapi.domain.order.*;
import com.leogersen.alfornoapi.domain.restaurant.*;
import com.leogersen.alfornoapi.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class InsertDataForTesting {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Client[] clients = clients();
        Restaurant[] restaurants = restaurants();
        menuItems(restaurants);


        Order o = new Order();
        o.setDate(LocalDateTime.now());
        o.setClient(clients[0]);
        o.setRestaurant(restaurants[0]);
        o.setStatus(Order.Status.Production);
        o.setSubTotal(BigDecimal.valueOf(15));
        o.setDeliveryTax(BigDecimal.valueOf(5));
        o.setTotal(BigDecimal.valueOf(20));
        orderRepository.save(o);

        o = new Order();        
        o.setDate(LocalDateTime.now());
        o.setClient(clients[1]);
        o.setRestaurant(restaurants[0]);
        o.setStatus(Order.Status.Production);
        o.setSubTotal(BigDecimal.valueOf(15));
        o.setDeliveryTax(BigDecimal.valueOf(5));
        o.setTotal(BigDecimal.valueOf(20));
        orderRepository.save(o);


    }

    private Restaurant[] restaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        RestaurantCategory lanchesCategory = restaurantCategoryRepository.findById(1).orElseThrow(NoSuchElementException::new);
        RestaurantCategory pizzaCategory = restaurantCategoryRepository.findById(2).orElseThrow(NoSuchElementException::new);

        Restaurant r = new Restaurant();
        r.setName("Mc Donalds's");
        r.setEmail("contact@mcdonalds.com");
        r.setPassword(StringUtils.encrypt("123"));
        r.setCnpj("12345678901234");
        r.setDeliveryTax(BigDecimal.valueOf(3.2));
        r.setPhone("31988733237");
        r.getCategories().add(lanchesCategory);
        r.getCategories().add(pizzaCategory);
        r.setLogo("0002.restaurant.png");
        r.setDeliveryTime(10);
        r.setCep("31980540");
        r.setStreet("Rua Afonso Pena");
        r.setDistrict("Centro");
        r.setNumber(15);
        r.setComplement("");
        r.setRating(BigDecimal.valueOf(4.2));
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Burger King");
        r.setEmail("contact@burgerking.com");
        r.setPassword(StringUtils.encrypt("123"));
        r.setCnpj("12345678901235");
        r.setDeliveryTax(BigDecimal.valueOf(1.5));
        r.setPhone("31988733238");
        r.getCategories().add(lanchesCategory);
        r.getCategories().add(pizzaCategory);
        r.setLogo("0002.restaurant.png");
        r.setDeliveryTime(20);
        r.setCep("31980540");
        r.setStreet("Rua Afonso Pena");
        r.setDistrict("Centro");
        r.setNumber(15);
        r.setComplement("");
        restaurantRepository.save(r);
        restaurants.add(r);


        /*
        r = new Restaurant();
        r.setName("Bob's");
        r.setEmail("contact@bobs.com");
        r.setPassword(StringUtils.encrypt("123"));
        r.setCnpj("12345678901236");
        r.setDeliveryTax(BigDecimal.valueOf(0));
        r.setPhone("31988733239");
        r.getCategories().add(lanchesCategory);
        r.setLogo("0003.restaurant.png");
        r.setDeliveryTime(30);
        r.setCep("31980540");
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Pizza Hut");
        r.setEmail("contact@pizzahut.com");
        r.setPassword(StringUtils.encrypt("123"));
        r.setCnpj("12345678901237");
        r.setDeliveryTax(BigDecimal.valueOf(2.5));
        r.setPhone("31988733230");
        r.getCategories().add(pizzaCategory);
        r.setLogo("0004.restaurant.png");
        r.setDeliveryTime(40);
        r.setCep("31980540");
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Temaki Now");
        r.setEmail("contact@temakinow.com");
        r.setPassword(StringUtils.encrypt("123"));
        r.setCnpj("12345678901231");
        r.setDeliveryTax(BigDecimal.valueOf(0));
        r.setPhone("31988733231");
        r.getCategories().add(japonesaCategory);
        r.setLogo("0005.restaurant.png");
        r.setDeliveryTime(50);
        r.setCep("31980540");
        restaurantRepository.save(r);
        restaurants.add(r); */

        Restaurant[] array = new Restaurant[restaurants.size()];
        return restaurants.toArray(array);


    }

    private Client[] clients() {
        List<Client> clients = new ArrayList<>();


        Client c = new Client();
        c.setName("Jhonny Silva");
        c.setEmail("contact@jhonny.com");
        c.setPassword(StringUtils.encrypt("123"));
        c.setCpf("11156477689");
        c.setPhone("31988733237");
        c.setCep("31980540");
        c.setStreet("Rua Afonso Pena");
        c.setDistrict("Centro");
        c.setNumber(15);
        c.setComplement("");
        c.setFavorites(1, 2, 3);
        clientRepository.save(c);
        clients.add(c);


        c = new Client();
        c.setName("Jhonny Lima");
        c.setEmail("j@j.com");
        c.setPassword(StringUtils.encrypt("123"));
        c.setCpf("11256477689");
        c.setPhone("31988733227");
        c.setCep("31980640");
        c.setStreet("Rua Afonso Pena");
        c.setDistrict("Centro");
        c.setNumber(15);
        c.setComplement("");
        c.setFavorites(1, 3);
        clientRepository.save(c);
        clients.add(c);

        Client[] array = new Client[clients.size()];
        return clients.toArray(array);



    }

    private void menuItems(Restaurant[] restaurants) {

        MenuItem im = new MenuItem();
        im.setCategory("Lanches");
        im.setDescription("Delicioso sanduíche de frango com legumes.");
        im.setName("Chiken Burger");
        im.setPrice(BigDecimal.valueOf(17.8));
        im.setRestaurant(restaurants[0]);
        im.setHighlight(true);
        im.setImage("0001-food.png");
        menuItemRepository.save(im);


        im = new MenuItem();
        im.setCategory("Pizzas");
        im.setDescription("Delicioso sanduíche de frango com legumes.");
        im.setName("Super Burger");
        im.setPrice(BigDecimal.valueOf(17.8));
        im.setRestaurant(restaurants[0]);
        im.setHighlight(true);
        im.setImage("0002-food.png");
        menuItemRepository.save(im);

        im = new MenuItem();
        im.setCategory("Pizzas");
        im.setDescription("Delicioso sanduíche de frango com legumes.");
        im.setName("Super Burger");
        im.setPrice(BigDecimal.valueOf(17.8));
        im.setRestaurant(restaurants[1]);
        im.setHighlight(true);
        im.setImage("0002-food.png");
        menuItemRepository.save(im);




    }



}
