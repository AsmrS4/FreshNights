package com.project.pet.domain.models;

import lombok.Data;

import java.util.UUID;
@Data
public class UserShort {
    private UUID id;
    private String image;
    private String firstName;
    private String lastName;
}
