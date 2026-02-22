public class TripleRoom implements RoomType{
    public Money basePrice(){
        return new Money(12000.0);
    }

    @Override
    public String toString(){
        return "Triple";
    }
}
