package org.example;



public class FamilyPerson extends Person {

    public FamilyPerson(int id) {
        super(id);
    }

    public FamilyPerson(int id, String name, String second_name, Gender gender, int birthYear) {
        super(id, name, second_name, gender, birthYear);
    }

    @Override
    public Object createObject(int id) {
        FamilyPerson newPerson = new FamilyPerson(id);
        newPerson.name = name;
        newPerson.second_name = second_name;
        newPerson.gender = gender;
        newPerson.birthYear = birthYear;
        return newPerson;
    }

    @Override
    public String toString() {
        return name + " " + second_name + " (" + birthYear + ") ...id:" + id + " " + gender;
    }
}