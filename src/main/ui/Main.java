package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UnixpenseGUI::new);
    }
}