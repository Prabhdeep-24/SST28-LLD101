package com.bms.repository;

import com.bms.model.Booking;
import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public void addBooking(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}
