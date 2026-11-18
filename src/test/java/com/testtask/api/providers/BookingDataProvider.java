package com.testtask.api.providers;

import com.testtask.api.models.Booking;
import com.testtask.api.models.BookingDates;
import org.testng.annotations.DataProvider;

public class BookingDataProvider {

    @DataProvider(name = "validBookingData")
    public static Object[][] getValidBookingData() {
        BookingDates dates = new BookingDates("2026-12-01", "2026-12-10");
        Booking expectedBooking = new Booking(
                "Dmytro", 
                "Automation", 
                250, 
                true, 
                dates, 
                "Breakfast"
        );

        return new Object[][] {
            { expectedBooking }
        };
    }
}