package com.leogersen.alfornoapi.domain.user;


import com.leogersen.alfornoapi.domain.client.Client;
import com.leogersen.alfornoapi.domain.client.ClientRepository;
import com.leogersen.alfornoapi.domain.restaurant.Restaurant;
import com.leogersen.alfornoapi.domain.restaurant.RestaurantRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserRepositoryEventHandler {


    private RestaurantRepository restaurantRepository;
    private ClientRepository clientRepository;

    public UserRepositoryEventHandler(ClientRepository clientRepository, RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.clientRepository = clientRepository;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handleClient(Client client) throws DuplicatedUserException {
        Client clientDB = clientRepository.findByEmail(client.getEmail());
        boolean duplicated = false;

        if(clientDB != null) {
            if(client.getEmail().equals(clientDB.getEmail())){
                duplicated = true;
            }

            if(client.getPhone().equals(clientDB.getPhone())){
                duplicated = true;
            }

            if(duplicated) {
                throw new DuplicatedUserException("Já existe um cadastro com essas informações");
            }

        }
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handleClient(Restaurant restaurant) throws DuplicatedUserException {
        Restaurant restaurantDB = restaurantRepository.findByEmail(restaurant.getEmail());
        boolean duplicated = false;

        if(restaurantDB != null) {
            if(restaurant.getEmail().equals(restaurantDB.getEmail())){
                duplicated = true;
            }

            if(restaurant.getPhone().equals(restaurantDB.getPhone())) {
                duplicated = true;
            }

        }

        if(duplicated) {
            throw new DuplicatedUserException("Já existe um cadastro com essas informações");
        }
    }








}
