package org.example.commands;

import org.example.FamilyTreeUtils;
import org.example.Person;
import org.example.interfaces.ICommand;

import java.util.List;
import java.util.Scanner;

// Поиск родителей для персоны ID

public class CommandSearchForParents implements ICommand {

    @Override
    public void exercute(Scanner scanner, FamilyTreeUtils utils) {
        System.out.println("Введите ID: ");
        String input = scanner.nextLine();
        List<Person> parents = utils.SearchForParents(Integer.parseInt(input));
        for (Person person : parents) {
            System.out.println(person);
        }
    }
    @Override
    public String getDescription() {
        return "SearchForParents";
    }
    @Override
    public void execute() {

    }
}