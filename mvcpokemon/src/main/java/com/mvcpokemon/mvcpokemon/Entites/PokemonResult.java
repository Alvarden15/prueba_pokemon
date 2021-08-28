package com.mvcpokemon.mvcpokemon.Entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResult 
{
    private int base_happiness;
    private int capture_rate;
    private boolean forms_switchable;
    private int gender_rate;
    private String name;
    private int order;
    
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public int getBase_happiness() {
        return base_happiness;
    }
    public void setBase_happiness(int base_happiness) {
        this.base_happiness = base_happiness;
    }
    public int getCapture_rate() {
        return capture_rate;
    }
    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }
    public boolean isForms_switchable() {
        return forms_switchable;
    }
    public void setForms_switchable(boolean forms_switchable) {
        this.forms_switchable = forms_switchable;
    }
    public int getGender_rate() {
        return gender_rate;
    }
    public void setGender_rate(int gender_rate) {
        this.gender_rate = gender_rate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
