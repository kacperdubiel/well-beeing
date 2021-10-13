package com.wellbeeing.wellbeeing.domain.message;

import java.util.ArrayList;

public class AuthenticationResponse{
    private String jwt;
    private ArrayList<String> roles;

    public AuthenticationResponse(String jwt, ArrayList<String> roles){
        this.jwt = jwt;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }
}
