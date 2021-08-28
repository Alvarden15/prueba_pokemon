package com.mvcpokemon.mvcpokemon.Controller;

import com.mvcpokemon.mvcpokemon.Entites.Evolution;
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

@Controller
public class PokemonController {
    
    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("pokemon/listado")
    public String listado(Model model, @RequestParam(required = false, name = "page", defaultValue = "1") String page){
        int convertedPage= 1;
        try{
            convertedPage = Integer.parseInt(page);
        }catch(Exception e){
            convertedPage = 1;
        }
        
        int offset = convertedPage * 20;
        ResponseEntity<ListPokemon> response = restTemplate
            .getForEntity("https://pokeapi.co/api/v2/pokemon-species?offset="+ offset, ListPokemon.class);
        ListPokemon list = response.getBody();
        model.addAttribute("pokemones", list);
        
        if(convertedPage > 1)
        {
            model.addAttribute("previous", convertedPage - 1);
        }else{
            model.addAttribute("previous", null);
        }

        model.addAttribute("next", convertedPage + 1);
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

    @GetMapping("pokemon/evolution/{id}")
    public String pokemonEvolutionData(@PathVariable int id, Model model){
        ResponseEntity<Evolution> response = restTemplate
            .getForEntity("https://pokeapi.co/api/v2/evolution-chain/"+id, Evolution.class);

        Evolution pokemon = response.getBody();
        model.addAttribute("evolution", pokemon);
        return "pokemon/evolution";
    }
}
