package com.adviqo.atm.locations.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class GeoLocation implements Serializable {

    private static final long serialVersionUID = 1116935055973459503L;
    private String lat;
    private String lng;
}
