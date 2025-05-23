public class Kontakt {
    private String name;
    private String phone;
    private String job;

    public Kontakt(String name, String phone, String job) {
        this.name = name;
        this.phone = phone;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }


    public String getJob() {
        return job;
    }

    public String toString() {
        return "Name: " + name + ", Telefonnummer: " + phone + " und ist als " + job + " Tätig.";
    }
}

