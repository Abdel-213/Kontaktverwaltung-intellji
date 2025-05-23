public class Kontakt {
    private String name;
    private String phone;

    public Kontakt(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        return "Name: " + name + ", Telefonnummer: " + phone;
    }
}

