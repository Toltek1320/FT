package org.example.commands;

import org.example.FamilyTreeUtils;
import org.example.interfaces.ICommand;

import java.util.Scanner;

//Сохранение дерева в файл "FamilyTree.txt"

public class CommandSave implements ICommand {

    @Override
    public void exercute(Scanner scanner, FamilyTreeUtils utils) {

        utils.geFamilyTree().save("FamilyTree.txt");
    }

    @Override
    public String getDescription() {
        return "save";
    }

    @Override
    public void execute() {

    }
}
