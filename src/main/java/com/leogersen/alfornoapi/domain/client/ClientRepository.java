package com.leogersen.alfornoapi.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface ClientRepository extends JpaRepository<Client, Integer> {

    public Client findByEmail(String email);
}
