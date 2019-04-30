package com.mycompany.cxf.soap.model;

public class Person {
    private String name;
    private int age;
    private String id;

    public Person(String id, String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person() {
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;

        return true;
    }
}
