package com.bms.service;

import com.bms.model.Booking;
import com.bms.model.BookingStatus;
import com.bms.model.SeatStatus;
import com.bms.model.Show;
import com.bms.model.ShowSeat;
import com.bms.model.User;
import com.bms.repository.BookingRepository;
import com.bms.strategy.PricingStrategy;

import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final PricingStrategy pricingStrategy;

    public BookingService(BookingRepository bookingRepository, PricingStrategy pricingStrategy) {
        this.bookingRepository = bookingRepository;
        this.pricingStrategy = pricingStrategy;
    }

    public Booking bookTickets(User user, Show show, List<ShowSeat> selectedSeats) {
        for (ShowSeat showSeat : selectedSeats) {
            synchronized (showSeat) {
                if (showSeat.getStatus() != SeatStatus.AVAILABLE) {
                    System.out.println("Seat " + showSeat.getSeat().getSeatNo() + " is already booked or locked.");
                    return null;
                }
                showSeat.setStatus(SeatStatus.LOCKED);
            }
        }

        double totalPrice = 0;
        for (ShowSeat showSeat : selectedSeats) {
            totalPrice += pricingStrategy.calculatePrice(showSeat, showSeat.getPrice());
        }

        String bookingId = "BKG-" + System.currentTimeMillis();
        Booking booking = new Booking(bookingId, user, show, selectedSeats, totalPrice, BookingStatus.PENDING);
        bookingRepository.addBooking(booking);

        boolean paymentSuccess = processPayment(booking);
        
        if (paymentSuccess) {
            booking.setStatus(BookingStatus.CONFIRMED);
            for (ShowSeat showSeat : selectedSeats) {
                 synchronized (showSeat) {
                     showSeat.setStatus(SeatStatus.BOOKED);
                 }
            }
        } else {
            booking.setStatus(BookingStatus.FAILED);
            for (ShowSeat showSeat : selectedSeats) {
                 synchronized (showSeat) {
                     showSeat.setStatus(SeatStatus.AVAILABLE);
                 }
            }
        }

        return booking;
    }

    private boolean processPayment(Booking booking) {
        return true; 
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if (booking == null || booking.getStatus() != BookingStatus.CONFIRMED) {
            return false;
        }

        System.out.println("Processing refund of " + booking.getTotalPrice() + " for Booking " + booking.getId());

        booking.setStatus(BookingStatus.CANCELLED);
        for (ShowSeat showSeat : booking.getBookedSeats()) {
            synchronized (showSeat) {
                showSeat.setStatus(SeatStatus.AVAILABLE);
            }
        }

        System.out.println("Refund successful. Seats freed.");
        return true;
    }
}
