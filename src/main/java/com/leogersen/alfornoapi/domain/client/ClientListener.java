package com.leogersen.alfornoapi.domain.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.PrePersist;

@Component
public class ClientListener {

    private static ClientRepository clientRepository;

    @PrePersist
    public void onPrePersistHandler(Client client) {
     }

    @Autowired
    public void init(ClientRepository clientRepository){
        ClientListener.clientRepository = clientRepository;
    }

}


