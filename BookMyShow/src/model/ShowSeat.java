package model;

public class ShowSeat {
    private String id;
    private Show show;
    private Seat seat;
    private SeatStatus status;
    private double price;

    public ShowSeat(String id, Show show, Seat seat, SeatStatus status, double price) {
        this.id = id;
        this.show = show;
        this.seat = seat;
        this.status = status;
        this.price = price;
    }

    public String getId() { return id; }
    public Show getShow() { return show; }
    public Seat getSeat() { return seat; }
    public double getPrice() { return price; }
    
    public synchronized SeatStatus getStatus() { 
        return status; 
    }
    
    public synchronized void setStatus(SeatStatus status) {
        this.status = status;
    }
}
