package com.adviqo.atm.locations.helper;

import com.adviqo.atm.locations.model.Address;
import com.adviqo.atm.locations.model.GeoLocation;
import com.adviqo.atm.locations.model.LocationType;
import com.adviqo.atm.locations.model.Locations;

import java.util.ArrayList;
import java.util.List;

public final class MockData {

    private MockData() {
    }

    public static List<Locations> getLocations() {
        List<Locations> locationsList = new ArrayList<>();
        Locations locations = getLocation();
        locationsList.add(locations);
        return locationsList;
    }

    public static Locations getLocation() {
        Locations locations = new Locations();
        Address address = new Address();
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLat("52.942981");
        geoLocation.setLng("4.741973");
        address.setCity("Den Helder");
        address.setGeoLocation(geoLocation);
        address.setHousenumber("");
        address.setPostalcode("");
        address.setStreet("Marsdiepstraat");

        locations.setAddress(address);
        locations.setDistance("0");
        locations.setType(LocationType.ING.toString());
        return locations;
    }

    public static String getLocators() {
        return ")]}',[{\"address\"" +
                ":{\"street\":\"Johannes van Vlotenlaan\"," +
                "\"housenumber\":\"1\",\"postalcode\":\"7412 SB\"," +
                "\"city\":\"Deventer\",\"geoLocation\":{\"lat\":\"52.26656\"" +
                ",\"lng\":\"6.14299\"}},\"distance\":50,\"type\":\"ING\"}]";
    }
}
