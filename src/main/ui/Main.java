package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UnixpenseGUI::new);
        printLog(EventLog.getInstance());
    }

    public static void printLog(EventLog el) {
        System.out.println();
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

}