package com.epam.transport.view;

import java.util.Scanner;

/**
 * The class is designed to work with user input.
 */
public class UserDataInput {
    public int enterNumber(String message) {
        if (message == null) {
            message = "";
        }

        System.out.print(message + " >> ");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.print(message + " >> ");
            scan.next();
        }
        int intNum = scan.nextInt();

        return intNum;
    }
}
