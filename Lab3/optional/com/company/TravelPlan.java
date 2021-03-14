package com.company;

import java.util.ArrayList;
import java.util.List;

public class TravelPlan {
    private City myCity = new City();
    private List<Location> preferences = new ArrayList<>();

    public void setMyCity(City myCity) {
        this.myCity = myCity;
    }

    public City getMyCity() {
        return myCity;
    }

    public void setPreferences(List<Location> preferences) {
        this.preferences = preferences;
    }

    public List<Location> getPreferences() {
        return preferences;
    }

    public void setPref(Location preference) {
        this.preferences.add(preference);
    }


}
