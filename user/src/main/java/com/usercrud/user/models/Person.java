package com.usercrud.user.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;



@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = " Обязательное поле")
    private String name;

    @NotBlank(message = " Обязательное поле")
    private String surname;

    @NotBlank(message = " Обязательное поле")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = " Обязательное поле")
    private String birth_date;

    @NotNull(message = " Обязательное поле")
    @Min(value = 1, message = "Возраст не может быть меньше 1")
    @Max(value = 150, message = "Возраст не может быть больше 150")
    private Integer age;

    @NotNull(message = " Обязательное поле")
    private Long phone_number;

    @NotNull(message = " Обязательное поле")
    private Boolean married;

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public Person() {
    }

    public Person(String name, String surname, String email, String birth_date, Integer age, Long phone_number, Boolean married) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birth_date = birth_date;
        this.age = age;
        this.phone_number = phone_number;
        this.married = married;
    }
}
