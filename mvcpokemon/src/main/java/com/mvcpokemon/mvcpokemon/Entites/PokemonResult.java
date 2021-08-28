package com.mvcpokemon.mvcpokemon.Entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResult 
{
    private int base_happiness;
    private int capture_rate;

    
}
