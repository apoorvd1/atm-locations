package com.adviqo.atm.locations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Locations implements Serializable {

    private static final long serialVersionUID = -1742520357976302080L;

    private Address address;
    private String distance;
    private String type;
}
