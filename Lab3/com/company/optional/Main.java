package com.company;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Hotel hotel = new Hotel("v1", 3);

        Museum museumA = new Museum();
        //v1 (Hotel) v2 (Museum A) v3 (Museum B) v4 (Church A) v5 (Church B) v6 (Restaurant).
        museumA.setName("v2");
        museumA.setOpeningTime(LocalTime.of(9, 30)); //9:30
        museumA.setClosingTime(LocalTime.parse("17:00"));
        museumA.setTicketPrice(20);

        Museum museumB = new Museum();
        museumB.setName("v3");
        museumB.setOpeningTime(LocalTime.of(9, 30)); //9:30
        museumB.setClosingTime(LocalTime.parse("17:00"));
        museumB.setTicketPrice(20);

        Church churchA = new Church();
        churchA.setName("v4");
        churchA.setOpeningTime(LocalTime.of(7, 0));
        churchA.setClosingTime(LocalTime.MIDNIGHT);

        Church churchB = new Church();
        churchB.setName("v5");
        churchB.setOpeningTime(LocalTime.of(7, 0));
        churchB.setClosingTime(LocalTime.MIDNIGHT);


        Restaurant restaurant = new Restaurant("v6", LocalTime.of(8, 0), LocalTime.MIDNIGHT, 1);

        //v1 (Hotel) v2 (Museum A) v3 (Museum B) v4 (Church A) v5 (Church B) v6 (Restaurant).
        hotel.setCost(museumA, 10);
        hotel.setCost(museumB, 50);
        museumA.setCost(museumB, 20);
        museumA.setCost(churchA, 20);
        museumA.setCost(churchB, 10);
        museumB.setCost(churchA, 20);
        museumB.setCost(museumA, 20);
        churchA.setCost(churchB, 30);
        churchB.setCost(churchA, 30);
        churchA.setCost(restaurant, 10);
        churchB.setCost(restaurant, 20);

        churchA.defaultTime();

        System.out.println(Visitable.getVisitingDuration(churchA));

        City city = new City();
        city.addLocation(museumA);
        city.addLocation(churchA);
        city.addLocation(museumB);
        city.addLocation(churchB);
        city.addLocation(hotel);
        city.addLocation(restaurant);
        System.out.println(city);

        city.visitableNotPayable();

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setMyCity(city);
        travelPlan.setPref(hotel);
        travelPlan.setPref(museumB);
        travelPlan.setPref(churchA);
        travelPlan.setPref(museumA);
        travelPlan.setPref(churchB);
        travelPlan.setPref(restaurant);

    }
}
