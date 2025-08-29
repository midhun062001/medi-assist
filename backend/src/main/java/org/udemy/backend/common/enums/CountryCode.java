package org.udemy.backend.common.enums;

public enum CountryCode {
    IN("India"),
    US("United States"),
    GB("United Kingdom"),
    CA("Canada"),
    AU("Australia"),
    SG("Singapore"),
    AE("United Arab Emirates"),
    DE("Germany"),
    FR("France"),
    JP("Japan"),
    NZ("New Zealand");

    private final String countryName;

    CountryCode(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }
}