package org.example;

import org.example.commands.CommandLoad;
import org.example.commands.CommandPrint;
import org.example.commands.CommandSave;
import org.example.commands.CommandSearchForParents;
import org.example.interfaces.ICommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<ICommand> commands;

    public static void main(String[] args) {
        FamilyTree<FamilyPerson> tree = new FamilyTree<>(new FamilyPerson(0));
        testFillTree(tree);
        FamilyTreeUtils<FamilyPerson> utils = new FamilyTreeUtils<>(tree);

        commands = new ArrayList<ICommand>();
        commands.add(new CommandPrint());
        commands.add(new CommandSave());
        commands.add(new CommandLoad());
        commands.add(new CommandSearchForParents());

        Scanner scanner = new Scanner(System.in);

        do {
            printMenu();
            String input = scanner.nextLine();
            if (input.equals("0"))
                break;

            boolean input_is_OK = false;
            for (ICommand command : commands) {
                if (input.equals(command.getDescription())) {
                    command.exercute(scanner, utils);
                    input_is_OK = true;
                    break;
                }
            }
            if (!input_is_OK)
                System.out.println("Неверная команда!");
        } while (true);
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("------- Меню -------");
        System.out.println("Введите команду из списка:");
        System.out.println("0 - выход");
        for (ICommand command : commands) {
            System.out.println(command.getDescription());
        }
        System.out.println("-------------");
    }

    public static void testFillTree(FamilyTree<FamilyPerson> familyTree) {

        FamilyPerson person1 = familyTree
                .addPerson(new FamilyPerson(0, "Иван", "Петров", Person.Gender.MALE, 2020));
        FamilyPerson person21 = familyTree.addPerson(
                new FamilyPerson(0, "Дарья", "Иванова", Person.Gender.FEMALE, 1999));
        FamilyPerson person22 = familyTree
                .addPerson(new FamilyPerson(0, "Петр", "Петров", Person.Gender.MALE, 1998));
        FamilyPerson person31 = familyTree
                .addPerson(new FamilyPerson(0, "Мария", "Сидорова", Person.Gender.FEMALE, 1973));
        FamilyPerson person32 = familyTree
                .addPerson(new FamilyPerson(0, "Олег", "Иванов", Person.Gender.MALE, 1968));
        FamilyPerson person33 = familyTree.addPerson(
                new FamilyPerson(0, "Вера", "Донцова", Person.Gender.FEMALE, 1972));
        FamilyPerson person34 = familyTree
                .addPerson(new FamilyPerson(0, "Сергей", "Петров", Person.Gender.MALE, 1969));

        familyTree.addRelation(new Relation(person21.id, person1.id, Relation.Type.CHILD));
        familyTree.addRelation(new Relation(person22.id, person1.id, Relation.Type.CHILD));
        familyTree.addRelation(new Relation(person31.id, person21.id, Relation.Type.CHILD));
        familyTree.addRelation(new Relation(person32.id, person21.id, Relation.Type.CHILD));
        familyTree.addRelation(new Relation(person33.id, person22.id, Relation.Type.CHILD));
        familyTree.addRelation(new Relation(person34.id, person22.id, Relation.Type.CHILD));
    }
}