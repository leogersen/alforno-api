package com.leogersen.alfornoapi.domain.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.PrePersist;

@Component
public class ClientListener {

    private static Logger logger = LoggerFactory.getLogger(ClientListener.class);

    private static ClientRepository clientRepository;

    @PrePersist
    public void onPrePersistHandler(Client client) {

        logger.info("Chegou aqui!");
     }

    @Autowired
    public void init(ClientRepository clientRepository){
        ClientListener.clientRepository = clientRepository;
    }

}


