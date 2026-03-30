package model;

public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;
    private SeatType seatType;

    public Seat(String id, int rowNo, int seatNo, SeatType seatType) {
        this.id = id;
        this.rowNo = rowNo;
        this.seatNo = seatNo;
        this.seatType = seatType;
    }

    public String getId() { return id; }
    public int getRowNo() { return rowNo; }
    public int getSeatNo() { return seatNo; }
    public SeatType getSeatType() { return seatType; }
}
