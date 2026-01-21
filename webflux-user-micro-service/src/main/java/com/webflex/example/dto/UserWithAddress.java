package com.webflex.example.dto;

import java.util.List;

import com.webflex.example.model.User;

public class UserWithAddress {
    private User user;
    private List<AddressDTO> addresses;

    public UserWithAddress(User user, List<AddressDTO> addresses) {
        this.user = user;
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }
}

