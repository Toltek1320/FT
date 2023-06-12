package org.example.commands;

import org.example.FamilyTreeUtils;
import org.example.interfaces.ICommand;

import java.util.Scanner;

public class CommandPrint implements ICommand {

    @Override
    public void exercute(Scanner scanner, FamilyTreeUtils utils) {
        utils.print();
    }

    @Override
    public String getDescription() {
        return "print";
    }

    @Override
    public void execute() {

    }
}
