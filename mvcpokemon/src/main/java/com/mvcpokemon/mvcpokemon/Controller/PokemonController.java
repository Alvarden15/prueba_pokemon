package com.mvcpokemon.mvcpokemon.Controller;

import com.mvcpokemon.mvcpokemon.Entites.ListPokemon;
import com.mvcpokemon.mvcpokemon.Entites.PokemonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class PokemonController {
    
    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("pokemon/listado")
    public String listado(Model model){
        ResponseEntity<ListPokemon> response = restTemplate
            .getForEntity("https://pokeapi.co/api/v2/pokemon-species", ListPokemon.class);
        ListPokemon list = response.getBody();
        model.addAttribute("pokemones", list);
        return "pokemon/listado";
    }

    @GetMapping("pokemon/detalles/{id}")
    public String pokemonData(@PathVariable int id, Model model){
        ResponseEntity<PokemonResult> response = restTemplate
            .getForEntity("https://pokeapi.co/api/v2/pokemon-species/"+id, PokemonResult.class);

        PokemonResult pokemon = response.getBody();
        model.addAttribute("pokemon", pokemon);
        return "pokemon/detalles";
    }


}
