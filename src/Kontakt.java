public class Kontakt {
    private String name;
    private String age;
    private String job;




    public Kontakt(String name, String age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }


    public String getJob() {
        return job;
    }

    public String toString() {
        return  name + " ist " + age + " jahre alt und ist als " + job + " Tätig.";
    }
}

