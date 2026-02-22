public class DoubleRoom implements RoomType{
    public Money basePrice(){
        return new Money(15000.0);
    }

    @Override
    public String toString(){
        return "Double";
    }
}
