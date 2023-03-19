//BSC-COM-23-20
public class Customers {
    String name = "";
    int gym_num = 000000;


    public Customers() {

    } 

    public Customers(String name, int gym_num) {
        this.name = name;
        this.gym_num = gym_num;
    } 

    public String getNameOfCustomer() {
        return name;
    }

    public int getGym_number() {
       return gym_num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGym_number(int number) {
        this.gym_num = number;
    }

}
