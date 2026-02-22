public class DeluxeRoom implements RoomType{
    public Money basePrice(){
        return new Money(16000.0);
    }

    @Override
    public String toString(){
        return "Deluxe";
    }
}
