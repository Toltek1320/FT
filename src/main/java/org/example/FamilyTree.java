package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class FamilyTree<T extends Person> {
    private HashMap<Integer, T> persons;
    private ArrayList<Relation> relations;
    private int id_counter;
    private final T person_object;
    private String name;
    private String family;

    // передаем в конструктор уже созданный экзепляр, от которого будем создавать другие

    public FamilyTree(T person_object) {
        this.person_object = person_object;
        persons = new HashMap<>();
        relations = new ArrayList<>();
        id_counter = 0;
    }

    // Сохранение дерева в файл

    public void save(String file_path) {
        try (DataOutputStream ds = new DataOutputStream(new FileOutputStream("E:/Учеба/Введение в ООП_семинары/FT/FamilyTree.txt"))) {
            ds.writeInt(persons.size());
            for (T it : persons.values()) {
                it.save(ds);
            }
            ds.writeInt(relations.size());
            for (Relation it : relations) {
                it.save(ds);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

     // Загрузка дерева из файла

    public void load(String file_path) {
        try (DataInputStream ds = new DataInputStream(new FileInputStream("E:/Учеба/Введение в ООП_семинары/FT/FamilyTree.txt"))) {
            persons.clear();
            relations.clear();
            int count = ds.readInt();
            for (int i = 0; i < count; i++) {
                T person = (T) person_object.createObject(0);
                person.load(ds);
                persons.put(person.get_id(), person);
            }

            count = ds.readInt();
            for (int i = 0; i < count; i++) {
                Relation relation = new Relation(0, 0, Relation.Type.CHILD);
                relation.load(ds);
                relations.add(relation);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Добавление персоны

    public T addPerson(T tperson) {

        while (persons.containsKey(id_counter))
            id_counter++;
        T person = (T) tperson.createObject(id_counter);
        persons.put(id_counter, person);
        return person;
    }

     // Удаление персоны с ID

    public boolean deletePerson(int id) {
        if (!persons.containsKey(id))
            return false;
        persons.remove(id);
        return true;
    }

    public T getPerson(int id) {
        if (!persons.containsKey(id))
            return null;
        return persons.get(id);
    }

     // Поиск ID персоны по имени и фамилии

    public List<T> findPersonID(String name, String family) {
        this.name = name;
        this.family = family;
        List<T> res = new ArrayList<>();
        for (T person : persons.values()) {
            if (person.name.equals(name) && person.second_name.equals(family)) {
                res.add(person);
            }
        }
        return res;
    }

    //Возвращаем список персон

    public Collection<T> getPersons() {
        return persons.values();
    }

     // Добавление связи между персонами

    public void addRelation(Relation relation) {
        for (Relation it : relations) {
            if (it.equals(relation))
                return;
            if (it.getID1() == relation.getID1() && it.getID2() == relation.getID2())
                return;
            if (it.getID1() == relation.getID2() && it.getID2() == relation.getID1())
                return;
        }
        relations.add(relation);
    }

    //Удаление связи между персонами id1 b id2

    public boolean deleteRelation(int id1, int id2) {
        for (int i = 0; i < relations.size(); i++) {
            if ((relations.get(i).getID1() == id1 && relations.get(i).getID2() == id2) ||
                    (relations.get(i).getID1() == id2 && relations.get(i).getID2() == id1)) {
                relations.remove(i);
                return true;
            }

        }
        return false;
    }

    // Возвращаем список связей персон

    public ArrayList<Relation> getRelations() {
        return (ArrayList<Relation>) relations.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
