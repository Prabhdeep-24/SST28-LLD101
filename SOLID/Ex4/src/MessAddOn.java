public class MessAddOn implements AddOn{
    public Money getPrice(){
        return new Money(1000.0);
    }

    @Override
    public String toString(){
        return "MESS";
    }
}
