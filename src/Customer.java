public class Customer extends LinkedQueue{
    private int ni;

    //Class constructor
    public Customer(int n){
        ni = n;
    }
    //Getter
    public int getNI(){
        return ni;
    }
    //Setter
    public void setNI(int n){
        n = ni;
    }
    //method to calcaulate serving time using number of items
    public int servingTime(){
        int t = 45 + (5*ni);
        return t;
    }

    //overriding method which return number of otems and serving time in a format of String
    @Override
    public String toString(){
        return "[" + ni + "(" + servingTime() + " s)]";
    }
}