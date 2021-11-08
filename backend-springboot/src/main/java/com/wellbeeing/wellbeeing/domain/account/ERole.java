package com.wellbeeing.wellbeeing.domain.account;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ERole {
    ROLE_BASIC_USER ("ROLE_BASIC_USER"),
    ROLE_DOCTOR ("ROLE_DOCTOR"),
    ROLE_DIETICIAN ("ROLE_DIETICIAN"),
    ROLE_TRAINER ("ROLE_TRAINER"),
    ROLE_ADMIN ("ROLE_ADMIN");

    public class Name{
        public static final String ROLE_BASIC_USER = "ROLE_BASIC_USER";
        public static final String ROLE_DOCTOR = "ROLE_DOCTOR";
        public static final String ROLE_DIETICIAN = "ROLE_DIETICIAN";
        public static final String ROLE_TRAINER = "ROLE_TRAINER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }

    private final String name;

    ERole(String role_name) {
        name = role_name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
