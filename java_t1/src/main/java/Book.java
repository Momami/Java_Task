import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private String name;
    private int countPage;
    private double price;
    private int yearOfBirth;

    public Book(){}
    public Book(String name, int countPage, double price, int yearOfBirth){
        this.name = name;
        this.countPage = countPage;
        this.price = price;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public double averagePricePage(){
        return this.price / (double)this.countPage;
    }

    public int countDayAfterYearOfBirth(){
        //String d1 = "23.11.2011";
        String d2 = "31.12." + this.yearOfBirth;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = new Date();
        Date date2 = null;
        try {
            //date1 = format.parse(d1);
            date2 = format.parse(d2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long difference = date1.getTime() - date2.getTime();
        int days = (int)(difference / (24 * 60 * 60 * 1000));
        return days;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", countPage=" + countPage +
                ", price=" + price +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
