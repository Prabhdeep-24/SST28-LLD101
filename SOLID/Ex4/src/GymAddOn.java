public class GymAddOn implements AddOn{
    public Money getPrice(){
        return new Money(300.0);
    }

    @Override
    public String toString(){
        return "GYM";
    }
}
