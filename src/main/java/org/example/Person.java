package org.example;

import org.example.interfaces.IStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Person implements IStream {

    enum Gender {
        MALE,
        FEMALE;
    }

    protected int id;
    public String name;
    public String second_name;
    public int birthYear;
    public Gender gender;


    public abstract Object createObject(int id);

    public Person(int id) {
        this.id = id;
        name = "";
        second_name = "";
    }

    public Person(int id, String name, String second_name, Gender gender, int birthYear) {
        this.id = id;
        this.name = name;
        this.second_name = second_name;
        this.gender = gender;
        this.birthYear = birthYear;
    }

    public int get_id() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != Person.class)
            return false;
        return this.id == ((Person) obj).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {

        return name + second_name + birthYear + "id: " + id + gender;
    }

    // Метод для передачи данных в поток

    @Override
    public void save(DataOutputStream stream_out) throws IOException {
        stream_out.writeInt(id);
        stream_out.writeUTF(name);
        stream_out.writeUTF(second_name);
        stream_out.writeInt(birthYear);
        int igender = 0;
        if (gender == Gender.FEMALE)
            igender = 1;
        stream_out.writeInt(igender);
    }

    // Метод для чтения объекта Person из потока

    @Override
    public void load(DataInputStream stream_in) throws IOException {
        int id = stream_in.readInt();
        String name = stream_in.readUTF();
        String second_name = stream_in.readUTF();
        int birthYear = stream_in.readInt();
        int igender = stream_in.readInt();
        Gender gender = Gender.MALE;
        if (igender == 1)
            gender = Gender.FEMALE;

        this.id = id;
        this.name = name;
        this.second_name = second_name;
        this.birthYear = birthYear;
        this.gender = gender;
    }
}
