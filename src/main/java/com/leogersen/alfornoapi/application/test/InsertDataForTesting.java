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
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

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
        MenuItem[] itemList = menuItems(restaurants);
        Order[] orders = orders(clients, restaurants);
        orderItems(itemList, orders);


    }

    private Order[] orders(Client[] clients, Restaurant[] restaurants) {
        List<Order> orderList = new ArrayList<Order>();

        Order o = new Order();
        o.setDate(LocalDateTime.now());
        o.setClient(clients[1]);
        o.setRestaurant(restaurants[0]);
        o.setStatus(Order.Status.Production);
        o.setSubTotal(BigDecimal.valueOf(15));
        o.setDeliveryTax(BigDecimal.valueOf(5));
        o.setTotal(BigDecimal.valueOf(20));
        orderRepository.save(o);
        orderList.add(o);

        o = new Order();        
        o.setDate(LocalDateTime.now());
        o.setClient(clients[1]);
        o.setRestaurant(restaurants[0]);
        o.setStatus(Order.Status.Completed);
        o.setSubTotal(BigDecimal.valueOf(15));
        o.setDeliveryTax(BigDecimal.valueOf(5));
        o.setTotal(BigDecimal.valueOf(20));
        orderRepository.save(o);
        orderList.add(o);

        Order[] array3 = new Order[orderList.size()];
        return orderList.toArray(array3);
    }

    private Set<OrderItem> orderItems(MenuItem[] items, Order[] orders) {
        Set<OrderItem> itemsSet = new HashSet<OrderItem>();

        OrderItem oI = new OrderItem();
        oI.setMenuItem(items[0]); 
        oI.setObs("Sem frango");
        oI.setPrice(BigDecimal.valueOf(15.50));
        oI.setQuantity(2);
        oI.setId(new OrderItemPK(orders[0], 1));
        orderItemRepository.save(oI);
        itemsSet.add(oI);

        oI = new OrderItem();
        oI.setMenuItem(items[1]); 
        oI.setObs("Sem milho");
        oI.setPrice(BigDecimal.valueOf(16.50));
        oI.setQuantity(2);
        oI.setId(new OrderItemPK(orders[0], 2));
        orderItemRepository.save(oI);
        itemsSet.add(oI);

        orders[0].setItems(itemsSet);
        return itemsSet;
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

    private MenuItem[] menuItems(Restaurant[] restaurants) {
        List<MenuItem> menuItems = new ArrayList<>();

        MenuItem im = new MenuItem();
        im.setCategory("Lanches");
        im.setDescription("Delicioso sanduíche de frango com legumes.");
        im.setName("Chiken Burger");
        im.setPrice(BigDecimal.valueOf(17.8));
        im.setRestaurant(restaurants[0]);
        im.setHighlight(true);
        im.setImage("0001-food.png");
        menuItemRepository.save(im);
        menuItems.add(im);


        im = new MenuItem();
        im.setCategory("Pizzas");
        im.setDescription("Delicioso sanduíche de frango com legumes.");
        im.setName("Super Burger");
        im.setPrice(BigDecimal.valueOf(17.8));
        im.setRestaurant(restaurants[0]);
        im.setHighlight(true);
        im.setImage("0002-food.png");
        menuItemRepository.save(im);
        menuItems.add(im);

        im = new MenuItem();
        im.setCategory("Pizzas");
        im.setDescription("Delicioso sanduíche de frango com legumes.");
        im.setName("Super Burger");
        im.setPrice(BigDecimal.valueOf(17.8));
        im.setRestaurant(restaurants[1]);
        im.setHighlight(true);
        im.setImage("0002-food.png");
        menuItemRepository.save(im);
        menuItems.add(im);

        MenuItem[] array2 = new MenuItem[menuItems.size()];
        return menuItems.toArray(array2);




    }



}
