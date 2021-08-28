package com.mvcpokemon.mvcpokemon.Controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.mvcpokemon.mvcpokemon.Entites.ListPokemon;
import com.mvcpokemon.mvcpokemon.Entites.PokemonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

@Controller
public class PokemonController {
    
    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("pokemon/listado")
    public String listado(Model model, @RequestParam(required = false, name = "offset", defaultValue = "20") String offset){
        ResponseEntity<ListPokemon> response = restTemplate
            .getForEntity("https://pokeapi.co/api/v2/pokemon-species?offset="+offset, ListPokemon.class);
        ListPokemon list = response.getBody();
        model.addAttribute("pokemones", list);
        /*
        if(list.getNext()!=null)
        {
            
            String localNext = list.getNext();
            String next = localNext.substring(localNext.lastIndexOf("?offset="),2);
            //model.addAttribute("next", next);
        }

        if(list.getPrevious()!=null)
        {
            String localPrevious = list.getPrevious();
            String next = localPrevious.substring(localPrevious.lastIndexOf("?offset="),2);
            
            model.addAttribute("previous", list);
        }
        */
        

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
