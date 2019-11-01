package com.adviqo.atm.locations.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Locations implements Serializable {

    private static final long serialVersionUID = -1742520357976302080L;

    private Address address;
    private String distance;
    private String type;
}
