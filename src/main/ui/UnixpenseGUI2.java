//package ui;
//
//import exceptions.CannotDeleteTaskException;
//import model.*;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//// class for GUI
//public class UnixpenseGUI2 extends JFrame implements ActionListener {
//    private static final String DATABASE = "./data/expenses.json";
//
//    private Expenses expenses = new Expenses();
//
//    private JPanel menu;
//    private JPanel createPanel;
//    private JPanel deletePanel;
//    private JPanel viewTable;
//    private JPanel markCompletedPanel;
//
//
//    private JLabel expensesLabel;
//    private JLabel descriptionLabel;
//    private JLabel dueDateLabel;
//    private JLabel taskAddedLabel;
//    private JLabel deleteTaskLabel;
//    private JLabel markCompletedLabel;
//    private JLabel sumTasksLabel;
//
//    private JButton create;
//    private JButton add;
//    private JButton delete;
//    private JButton deleteTask;
//    private JButton view;
//    private JButton mark;
//    private JButton markTask;
//    private JButton save;
//    private JButton load;
//    private JButton quit;
//    private JButton returnMenu;
//
//
//    private JTextField descriptionText;
//    private JTextField dueDateText;
//    private JTextField deleteTaskInput;
//    private JTextField markCompletedInput;
//
//
//    //EFFECTS: constructs to-do list GUI
//    public UnixpenseGUI2() {
//        super("To Do List");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(800, 700));
//
//        initializeMenu();
//        pack();
//        makeAddTaskPanel();
//        makeDeleteTaskPanel();
//        makeMarkCompletedPanel();
//        makeViewTasksPanel();
//
//
//        JLabel welcomeLabel = new JLabel("Welcome to your to-do list!");
//        addWelcomeMessage(welcomeLabel);
//
//        JLabel dateAndTime = new JLabel("It is currently: " + addDateAndTime());
//        addSubheadingMessage(dateAndTime);
//
//        JLabel mainScreenImage = new JLabel();
//        addImageToLabel(mainScreenImage);
//
//
//        initializeButtons();
//
//        addButtonToMenu(create, delete, view, mark, save, load, quit);
//
//        addActionToButton();
//
//        menu.setVisible(true);
//    }
//
//
//    // EFFECTS: Creates the menu panel
//    public void initializeMenu() {
//        menu = new JPanel();
//        add(menu);
//        expensesLabel = new JLabel();
//        expensesLabel.setText("There is no expense");
//    }
//
//    // EFFECTS: displays the welcome message to the menu panel
//    public void addWelcomeMessage(JLabel jl) {
//        jl.setFont(new Font("Arial", Font.BOLD, 40));
//        menu.add(jl);
//    }
//
//    // EFFECTS: displays the subheading (date and time) message to the menu panel
//    public void addSubheadingMessage(JLabel jl) {
//        jl.setFont(new Font("Arial", Font.BOLD, 20));
//        menu.add(jl);
//    }
//
//    // EFFECTS: adds the date and time message to the menu panel
//    public String addDateAndTime() {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        return formatter.format(date);
//    }
//
//    // EFFECTS: adds image to the menu panel
//    public void addImageToLabel(JLabel jl) {
//        ImageIcon icon = new ImageIcon("./data/cover.jpg");
//        Image cover = icon.getImage();
//        Image modifiedCover = cover.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
//        ImageIcon modifiedCoverIcon = new ImageIcon(modifiedCover);
//        jl.setIcon(modifiedCoverIcon);
//        menu.add(jl);
//    }
//
//
//    // EFFECTS: initializes buttons that are displayed in the menu and adds labels for them
//    public void initializeButtons() {
//        create = new JButton("Add Task");
//        delete = new JButton("Delete Task");
//        view = new JButton("View Tasks");
//        mark = new JButton("Mark Task as Completed");
//        save = new JButton("Save To-Do List");
//        load = new JButton("Load To-Do List");
//        quit = new JButton("Quit To-Do List");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: displays the buttons on the menu
//    public void buttonToMenu(JButton btn, JPanel p) {
//        p.add(btn);
//        pack();
//        setVisible(true);
//        setResizable(false);
//    }
//
//    // EFFECTS: calls buttonToMenu method and adds all the relevant buttons to menu that is passed as argument
//    public void addButtonToMenu(JButton add, JButton delete, JButton view, JButton mark,
//                                JButton save, JButton load, JButton quit) {
//        buttonToMenu(add, menu);
//        buttonToMenu(delete, menu);
//        buttonToMenu(view, menu);
//        buttonToMenu(mark, menu);
//        buttonToMenu(save, menu);
//        buttonToMenu(load, menu);
//        buttonToMenu(quit, menu);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds action to each button
//    public void addActionToButton() {
//        create.addActionListener(this);
//        create.setActionCommand("Add Task");
//        returnMenu.addActionListener(this);
//        returnMenu.setActionCommand("Return To Menu");
//        delete.addActionListener(this);
//        delete.setActionCommand("Delete Task");
//        view.addActionListener(this);
//        view.setActionCommand("View Tasks");
//        mark.addActionListener(this);
//        mark.setActionCommand("Mark Task as Completed");
//        save.addActionListener(this);
//        save.setActionCommand("Save Tasks");
//        load.addActionListener(this);
//        load.setActionCommand("Load Tasks");
//        quit.addActionListener(this);
//        quit.setActionCommand("Quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates the panel for adding a task
//    public void makeAddTaskPanel() {
//        createPanel = new JPanel(new GridLayout(4, 3));
//        returnMenu = new JButton("Return To Menu");
//        returnMenu.setActionCommand("Return To Menu");
//        returnMenu.addActionListener(this);
//        buttonToMenu(returnMenu, createPanel);
//
//        createAddTaskInputPage();
//
//        createPanel.add(add);
//
//        createPanel.add(descriptionLabel);
//        createPanel.add(descriptionText);
//        createPanel.add(dueDateLabel);
//        createPanel.add(dueDateText);
//        createPanel.add(taskAddedLabel);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates JTextFields for user input for tasks
//    public void createAddTaskInputPage() {
//
//        add = new JButton("Add this task to my To-Do List");
//        add.setActionCommand("Add this task to my To-Do List");
//        add.addActionListener(this);
//
//        descriptionLabel = new JLabel("Task Description:");
//        descriptionText = new JTextField(1);
//        dueDateLabel = new JLabel("Due Date of Task:");
//        dueDateText = new JTextField(1);
//        taskAddedLabel = new JLabel("Task Added?");
//
//
//        addTaskLabelSettings();
//    }
//
//    // EFFECTS: Adds settings for backgrounds, JLabels, and JTextFields
//    private void addTaskLabelSettings() {
//
//        add.setBackground(Color.WHITE);
//        add.setFont(new Font("Arial", Font.BOLD, 15));
//
//        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 15));
//        dueDateLabel.setFont(new Font("Arial", Font.BOLD, 15));
//
//        descriptionText.setSize(new Dimension(10, 20));
//        dueDateText.setSize(new Dimension(10, 20));
//    }
//
//    // EFFECTS: sets the addTaskPanel to be visible and hides the rest of the panels
//    public void addTaskPanel() {
//        add(createPanel);
//        createPanel.setVisible(true);
//        menu.setVisible(false);
//        deletePanel.setVisible(false);
//        markCompletedPanel.setVisible(false);
//        viewTable.setVisible(false);
//    }
//
//    // EFFECTS: displays tasks onto GUI panel
//    public void listTasks() {
//        String tasks = "";
//        for (int i = 0; i < expenses.length(); i++) {
//            tasks += i + ". " + "\n" + "Task Description: " + expenses.getTask(i).getDescription()
//                    + "\n" + "Due Date: " + expenses.getTask(i).getDueDate() + "\n"
//                    + "Status: " + expenses.getTask(i).isComplete() + "\n" + "\n";
//        }
//        expensesLabel.setText("<html><pre>Your Tasks: \n" + "\n" + tasks + "\n<pre><html>");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Adds a task for it to be displayed
//    public void addTaskGUI() {
//        try {
//            expenses.addTask(new Task(descriptionText.getText(), dueDateText.getText(), false));
//            listTasks();
//            taskAddedLabel.setText("Task Added? Yes! Add more tasks if you need too!");
//            sumTasksLabel.setText("You have " + expenses.sumIncompleteTasks() + " incomplete todo tasks and "
//                    + expenses.sumCompletedTasks() + " complete todo tasks.");
//        } catch (NullPointerException e) {
//            //
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input, please try again");
//        } catch (IndexOutOfBoundsException e) {
//            expensesLabel.setText("Task is not initialized");
//        }
//    }
//
//
//    // EFFECTS: adds relevant panels, buttons,labels, and text fields to delete task panel
//    public void makeDeleteTaskPanel() {
//        deletePanel = new JPanel(new GridLayout(3, 1));
//
//        JButton returnMenuButton = new JButton("Return To Menu");
//        returnMenuButton.setActionCommand("Return To Menu");
//        returnMenuButton.addActionListener(this);
//        buttonToMenu(returnMenuButton, deletePanel);
//
//        deleteTask = new JButton("Delete Task from To-Do List");
//        deleteTask.setActionCommand("Delete Task from To-Do List");
//        deleteTask.addActionListener(this);
//        buttonToMenu(deleteTask, deletePanel);
//        deleteTask.setBackground(Color.WHITE);
//        deleteTask.setFont(new Font("Arial", Font.BOLD, 15));
//
//        deleteTaskLabel = new JLabel("Please input the task number you would like to delete:");
//        deleteTaskInput = new JTextField(1);
//
//        deletePanel.add(deleteTaskLabel);
//        deletePanel.add(deleteTaskInput);
//    }
//
//    // EFFECTS: sets the deleteTaskPanel to be visible and hides the rest of the panels
//    public void deleteTaskPanel() {
//        add(deletePanel);
//        viewTable.setVisible(false);
//        menu.setVisible(false);
//        createPanel.setVisible(false);
//        deletePanel.setVisible(true);
//        markCompletedPanel.setVisible(false);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: Removes task from the to-do list
//    public void deleteTaskGUI() {
//        try {
//            expenses.removeTask(Integer.parseInt(deleteTaskInput.getText()));
//            listTasks();
//            sumTasksLabel.setText("You have " + expenses.sumIncompleteTasks() + " incomplete todo tasks and "
//                    + expenses.sumCompletedTasks() + " complete todo tasks.");
//        } catch (NumberFormatException e) {
//            //
//        } catch (NullPointerException e) {
//            System.out.println("Please add a task before attempting to remove it");
//        } catch (IndexOutOfBoundsException e) {
//            expensesLabel.setText("Task is not initialized");
//        } catch (CannotDeleteTaskException e) {
//            expensesLabel.setText("No More Tasks To Delete");
//        }
//    }
//
//    // EFFECTS: adds relevant panels, buttons,labels, and text fields to delete task panel
//    public void makeMarkCompletedPanel() {
//        markCompletedPanel = new JPanel(new GridLayout(3, 1));
//
//        returnMenu = new JButton("Return To Menu");
//        returnMenu.setActionCommand("Return To Menu");
//        returnMenu.addActionListener(this);
//        buttonToMenu(returnMenu, markCompletedPanel);
//
//        markTask = new JButton("Mark Task as Completed in To-Do List");
//        markTask.setActionCommand("Mark Task as Completed in To-Do List");
//        markTask.addActionListener(this);
//        buttonToMenu(markTask,  markCompletedPanel);
//        markTask.setBackground(Color.WHITE);
//        markTask.setFont(new Font("Arial", Font.BOLD, 15));
//
//        markCompletedLabel = new JLabel("Please input the task number you would like to mark as completed:");
//        markCompletedInput = new JTextField(1);
//
//        markCompletedPanel.add(markCompletedLabel);
//        markCompletedPanel.add(markCompletedInput);
//    }
//
//    // EFFECTS: sets the markCompletedPanel to be visible and hides the rest of the panels
//    public void markCompletedPanel() {
//        add(markCompletedPanel);
//        viewTable.setVisible(false);
//        menu.setVisible(false);
//        createPanel.setVisible(false);
//        deletePanel.setVisible(false);
//        markCompletedPanel.setVisible(true);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: Mark a task as completed from to-do list
//    public void markCompletedGUI() {
//        try {
//            expenses.markCompleted(Integer.parseInt(markCompletedInput.getText()));
//            listTasks();
//            sumTasksLabel.setText("You have " + expenses.sumIncompleteTasks() + " incomplete todo tasks and "
//                    + expenses.sumCompletedTasks() + " complete todo tasks.");
//        } catch (NumberFormatException e) {
//            // expected
//        } catch (NullPointerException e) {
//            System.out.println("Please add a task before attempting to mark it as completed");
//        } catch (IndexOutOfBoundsException e) {
//            expensesLabel.setText("Task is not initialized");
//        }
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECTS: Creates view tasks panel that displays the current tasks and the sum of completed and incomplete tasks
//    public void makeViewTasksPanel() {
//
//        viewTable = new JPanel(new GridLayout(3, 3));
//        JScrollPane scroll = new JScrollPane(expensesLabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//
//        JButton returnMenuButton = new JButton("Return To Menu");
//        returnMenuButton.setActionCommand("Return To Menu");
//        returnMenuButton.addActionListener(this);
//        buttonToMenu(returnMenuButton, viewTable);
//
//        expensesLabel.setFont(new Font("Arial", Font.BOLD, 15));
//        viewTable.add(scroll);
//
//        sumTasksLabel = new JLabel();
//        sumTasksLabel.setText("You have " + expenses.sumIncompleteTasks() + " incomplete tasks and "
//                + expenses.sumCompletedTasks() + " completed tasks.");
//
//        viewTable.add(sumTasksLabel);
//    }
//
//    // EFFECTS: sets the viewTasksPanel to be visible and hides the rest of the panels
//    public void viewTasksPanel() {
//        add(viewTable);
//        viewTable.setVisible(true);
//        menu.setVisible(false);
//        createPanel.setVisible(false);
//        deletePanel.setVisible(false);
//        markCompletedPanel.setVisible(false);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: loads the tasks in the previously saved to-do list that's stored in toDoList.json
//    private void loadTasks() {
//        try {
//            JsonReader reader = new JsonReader(DATABASE);
//            expenses = reader.read();
//            listTasks();
//            sumTasksLabel.setText("You have " + expenses.sumIncompleteTasks() + " incomplete todo tasks and "
//                    + expenses.sumCompletedTasks() + " complete todo tasks.");
//            System.out.println("Tasks loaded from file " + DATABASE);
//        } catch (IOException e) {
//            expensesLabel.setText("There are no tasks added");
//        } catch (IndexOutOfBoundsException e) {
//            expensesLabel.setText("Save tasks before proceeding");
//        }
//    }
//
//    // EFFECTS: saves current tasks in the to-do list to file toDoList.json
//    private void saveTasks() {
//        try {
//            JsonWriter writer = new JsonWriter(DATABASE);
//            writer.open();
//            writer.write(expenses);
//            writer.close();
//            System.out.println("Expenses saved to file " + DATABASE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save expenses to " + DATABASE);
//        } catch (NullPointerException e) {
//            System.out.println("Try loading file before saving");
//        }
//    }
//
//    // EFFECTS: sets the menuPanel to be visible and hides the rest of the panels
//    public void returnToMenu() {
//        menu.setVisible(true);
//        createPanel.setVisible(false);
//        deletePanel.setVisible(false);
//        viewTable.setVisible(false);
//        markCompletedPanel.setVisible(false);
//    }
//
//    // EFFECTS: performs action based on the button the user presses
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("Add this task to my To-Do List")) {
//            addTaskGUI();
//        } else if (e.getActionCommand().equals("Add Task")) {
//            addTaskPanel();
//        } else if (e.getActionCommand().equals("Delete Task from To-Do List")) {
//            deleteTaskGUI();
//        } else if (e.getActionCommand().equals("Delete Task")) {
//            deleteTaskPanel();
//        } else if (e.getActionCommand().equals("View Tasks")) {
//            viewTasksPanel();
//        } else if (e.getActionCommand().equals("Save Tasks")) {
//            saveTasks();
//        } else if (e.getActionCommand().equals("Mark Task as Completed in To-Do List")) {
//            markCompletedGUI();
//        } else if (e.getActionCommand().equals("Mark Task as Completed")) {
//            markCompletedPanel();
//        } else if (e.getActionCommand().equals("Load Tasks")) {
//            loadTasks();
//        } else if (e.getActionCommand().equals("Return To Menu")) {
//            returnToMenu();
//        } else if (e.getActionCommand().equals("Quit")) {
//            System.exit(0);
//        }
//    }
//
//
//}
