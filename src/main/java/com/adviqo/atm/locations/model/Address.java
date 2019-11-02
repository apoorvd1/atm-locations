package com.adviqo.atm.locations.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Address implements Serializable {

    private static final long serialVersionUID = -3134400844872619125L;
    private String street;

    @JsonAlias("housenumber")
    private String housenumber;

    @JsonAlias("postalcode")
    private String postalcode;

    private String city;
    private GeoLocation geoLocation;

}
