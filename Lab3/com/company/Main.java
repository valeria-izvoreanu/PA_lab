package com.company;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Museum museum = new Museum();
        museum.setOpeningTime(LocalTime.of(9, 30)); //9:30
        museum.setClosingTime(LocalTime.parse("17:00"));
        museum.setTicketPrice(20);

        Church church = new Church();
        church.setName("St. Andrew");
        church.setOpeningTime(LocalTime.of(7, 0));
        church.setClosingTime(LocalTime.MIDNIGHT);

        Hotel hotel = new Hotel("Home", 3);
        Restaurant restaurant = new Restaurant("Starry", LocalTime.of(8, 0), LocalTime.MIDNIGHT, 1);

        museum.setCost(church, 2);
        church.setCost(museum, 4);
        System.out.println(museum.getCostLocation(church));

        City city = new City();
        city.addLocation(museum);
        city.addLocation(church);
        city.addLocation(hotel);
        city.addLocation(restaurant);
        System.out.println(city);
    }
}
