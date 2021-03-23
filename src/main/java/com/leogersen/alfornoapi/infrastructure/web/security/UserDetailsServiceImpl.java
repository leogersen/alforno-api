package com.leogersen.alfornoapi.infrastructure.web.security;


import com.leogersen.alfornoapi.domain.client.Client;
import com.leogersen.alfornoapi.domain.client.ClientRepository;
import com.leogersen.alfornoapi.domain.restaurant.Restaurant;
import com.leogersen.alfornoapi.domain.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private ClientRepository clientRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public UserDetailsServiceImpl(ClientRepository clientRepository, RestaurantRepository restaurantRepository) {
        this.clientRepository = clientRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(clientRepository.findByEmail(username) != null){
            Client user = clientRepository.findByEmail(username);
            return new UserDetailsImpl(user);
        }

        if(restaurantRepository.findByEmail(username) != null){
            Restaurant user = restaurantRepository.findByEmail(username);
            return new UserDetailsImpl(user);
        }

        if (clientRepository.findByEmail(username) == null && restaurantRepository.findByEmail(username) == null){
            throw new UsernameNotFoundException(username);
        }
        return null;
    }
}
