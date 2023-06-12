package org.example.commands;

import org.example.FamilyTreeUtils;
import org.example.interfaces.ICommand;

import java.util.Scanner;

//Загрузка дерева из файла "FamilyTree.txt"

public class CommandLoad implements ICommand {

    @Override
    public void exercute(Scanner scanner, FamilyTreeUtils utils) {

        utils.geFamilyTree().load("FamilyTree.txt");
    }

    @Override
    public String getDescription() {

        return "load";
    }

    @Override
    public void execute() {
    }
}