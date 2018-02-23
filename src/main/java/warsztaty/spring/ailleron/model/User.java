package warsztaty.spring.ailleron.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class User {
    private long id;

    @Size(min = 2, max = 20, message = "Wrong length of string!")
    private String name;
    private String surname;
    @Min(value = 18, message = "Adult users only!")
    @Max(value = 150, message = "People do not live so long!")
    private int age;

    public User() {
    }

    public User(long id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
