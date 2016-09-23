package com.example.hzvoznired.hzvoznired.utils;

/**
 * Created by tiliasolutions on 5.9.2016..
 */
public class UrlCreator {

    private static final String mStartUrl = "https://prodaja.hzpp.hr/hr/Ticket/Journey?";
    private static final String mAndMark = "&";


    public static String trainScheduleUrl(String startId, String destId, String departureDate,
                                          String directTrains,String clas, String returnTrip,
                                          String passenger1Count, String  benefit1Id){

        return mStartUrl +
                "StartId=" +
                startId +
                mAndMark +
                "DestId=" +
                destId +
                mAndMark +
                "DepartureDate=" +
                departureDate +
                mAndMark +
                "DirectTrains=" +
                directTrains +
                mAndMark +
                "Class=" +
                clas +
                mAndMark +
                "ReturnTrip=" +
                returnTrip +
                mAndMark +
                "Passenger1Count=" +
                passenger1Count +
                mAndMark +
                "Benefit1Id=" +
                benefit1Id;
    }



    public static String trainScheduleUrlMultiple(String startId, String destId, String
            departureDate,
                                          String directTrains,String clas, String returnTrip,
                                          String passenger1Count, String passenger2Count,
                                          String  benefit1Id, String benefit2Id){

        return mStartUrl +
                "StartId=" +
                startId +
                mAndMark +
                "DestId=" +
                destId +
                mAndMark +
                "DepartureDate=" +
                departureDate +
                mAndMark +
                "DirectTrains=" +
                directTrains +
                mAndMark +
                "Class=" +
                clas +
                mAndMark +
                "ReturnTrip=" +
                returnTrip +
                mAndMark +
                "Passenger1Count=" +
                passenger1Count +
                mAndMark +
                "Passenger2Count=" +
                passenger2Count +
                mAndMark +
                "Benefit1Id=" +
                benefit1Id +
                mAndMark +
                "Benefit2Id=" +
                benefit2Id;
    }


    public static void trainScheduleDetailsUrl(){


    }





    public static String whereIsTrainUrl(String location, String direction){


        return mStartUrl +
                "Location=" +
                location +
                mAndMark +
                "TravelDirection=" +
                direction;

        //http://www.hzpp.hr/CorvusTheme/TimetableOnline/Result?
        // Location=112&
        // TravelDirection=D
    }


    public static String trainStationUrl(String location, boolean arrival, boolean departure){
        StringBuilder data = new StringBuilder();
        data.append("http://www.hzpp.hr/CorvusTheme/TimetableByStations/Result?")
                .append("Location=")
                .append(location)
                .append(mAndMark)
                .append("Arrival=")
                .append(arrival)
                .append(mAndMark)
                .append("Departure=")
                .append(departure);

        return data.toString();
        //http://www.hzpp.hr/CorvusTheme/TimetableByStations/Result?
        // Location=191&
        // Arrival=true&
        // Departure=false
    }


    public static String allLocationsUrl(){
        return "http://www.hzpp.hr/CorvusTheme/Timetable/Loc";
    }




}
