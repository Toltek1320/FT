package org.example.interfaces;

import org.example.FamilyPerson;
import org.example.FamilyTreeUtils;

import java.util.Scanner;

public interface ICommand {

    String getDescription();
    void execute();
    void exercute(Scanner scanner, FamilyTreeUtils<FamilyPerson> utils);
}


