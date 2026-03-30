package model;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<ShowSeat> bookedSeats;
    private double totalPrice;
    private BookingStatus status;
    private LocalDateTime bookingTime;

    public Booking(String id, User user, Show show, List<ShowSeat> bookedSeats, double totalPrice, BookingStatus status) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
        this.status = status;
        this.bookingTime = LocalDateTime.now();
    }

    public String getId() { return id; }
    public User getUser() { return user; }
    public Show getShow() { return show; }
    public List<ShowSeat> getBookedSeats() { return bookedSeats; }
    public double getTotalPrice() { return totalPrice; }
    public BookingStatus getStatus() { return status; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
