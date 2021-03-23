package com.leogersen.alfornoapi.domain.order;

import com.leogersen.alfornoapi.domain.client.Client;
import com.leogersen.alfornoapi.domain.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

@Component
public class OrderLitener {

    private static ClientRepository clientRepository;

    @PrePersist
    public void onPrePersistHandler(Order order) {
        if (order.getClient() == null){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientRepository.findByName(username);

            if(client == null) {
                throw new EntityNotFoundException("O usuário " + username + " não foi encontrado");
            }

            order.setClient(client);
        }
    }

    @Autowired
    public void init(ClientRepository clientRepository){
        OrderLitener.clientRepository = clientRepository;
    }

}
