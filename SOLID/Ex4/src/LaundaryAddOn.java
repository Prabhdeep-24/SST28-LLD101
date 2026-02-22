public class LaundaryAddOn implements AddOn{
    public Money getPrice(){
        return new Money(500.0);
    }

    @Override
    public String toString(){
        return "LAUNDARY";
    }
}
