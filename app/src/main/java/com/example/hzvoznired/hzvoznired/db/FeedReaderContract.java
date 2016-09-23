package com.example.hzvoznired.hzvoznired.db;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "hzData";
        public static final String CITY_NAME = "cityName";
        public static final String CITY_CODE = "cityCode";
        public static final String GDJE_JE_VLAK_FAV = "gdjeJeVlakFav";
        public static final String PO_KOLODVORIMA_FAV = "poKolodvorimaFav";
        public static final String VOZNI_RED_FAV = "vozniRedFav";
    }
}
