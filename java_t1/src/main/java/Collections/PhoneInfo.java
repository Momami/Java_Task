package Collections;

public class PhoneInfo {
    private String name;
    private String numberPhone;
    private String city;
    private String lastName;

    public PhoneInfo(String name, String numberPhone, String city, String lastName) {
        this.name = name;
        this.numberPhone = numberPhone;
        this.city = city;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PhoneInfo() {}

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "name='" + name + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", city='" + city + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
