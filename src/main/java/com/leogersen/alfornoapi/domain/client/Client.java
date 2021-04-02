package com.leogersen.alfornoapi.domain.client;

import com.leogersen.alfornoapi.domain.restaurant.MenuItem;
import com.leogersen.alfornoapi.domain.restaurant.Restaurant;
import com.leogersen.alfornoapi.domain.user.User;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Client extends User {

    @NotEmpty(message = "O campo CPF não pode estar vazio")
    @Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inválido")
    @Column(length = 11, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuItem> menuItems = new HashSet<>(0);

    @JoinColumn(name = "client_favorites")
    private ArrayList<Integer> favorites = new ArrayList<>(0);

    public void setFavorites(int... ids) {
        ArrayList<Integer> favoritesArray = new ArrayList<>();
        for (int id : ids) {
            favoritesArray.add(id);}
        this.favorites = favoritesArray;}

}