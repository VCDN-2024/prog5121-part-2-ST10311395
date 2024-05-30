// Author: Katelyn Narain
// Student ID: ST10311395

package progpart2;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class ProgPart2 {

    static Object getTaskstatus(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // User credentials
    private String username;
    private String password;

    // List to store tasks
    private static List<Task> tasks = new ArrayList<>();

    // Constructor
    public ProgPart2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Currently logged-in user
    private static ProgPart2 currentUser;

    // Method to check if username meets requirements
    public static boolean checkUserName(String username) {
        //checks if username has 5 characters and contains underscore
        return username != null && username.length() == 5 && username.contains("_");
    }

    // Method to check if password meets complexity requirements
    public static boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
        //checks if password contains 8 characters and has regex charcters.
        return (password.length() >= 8 && password.matches(regex));
    }

    // Method to register a new user
    private static void registerUser() {
        //asks the user to enter username to sign up
        String username = JOptionPane.showInputDialog("Enter username (must be 5 characters long and contain an underscore):");
       //checks if username meets requirements
        if (checkUserName(username)) {
            //message to display if successfully captured
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            //if username does not meet requirements
        } else {
            //message to display when username is not successfully captured
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no longer than 5 characters in length");
           // Restarts registration
            main(null); 
        }
        //asks the user to enter a pasword to sign up
        String password = JOptionPane.showInputDialog("Enter password (must be 5 characters long and contain an underscore):");
       //checks if password meets requirements 
        if (checkPasswordComplexity(password)) {
            //message to display if password successfully captured
            JOptionPane.showMessageDialog(null, "Password successfully captured");
        //if password does not meet requirements
        } else {
            //message to display
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and special character");
            // Restart registration
            main(null); 
        }
        //when username and password successfully captured, save it in a variable
        currentUser = new ProgPart2(username, password);
        
        //ask user for firstname
        String firstname = JOptionPane.showInputDialog("Enter developer's first name");
        
        //ask user for lastname
        String lastname = JOptionPane.showInputDialog("Enter developer's last name");
    }

    // Method to log in an existing user
    private static void loginUser() {
        //asks the user to enter username to login
        String usernamelogin = JOptionPane.showInputDialog("Enter username to login:");
        
        //asks the user to enter password to signin
        String passwordlogin = JOptionPane.showInputDialog("Enter password to login:");
        
        // Check if the provided username and password match the stored user's credentials
        if (currentUser != null && usernamelogin.equals(currentUser.username) && passwordlogin.equals(currentUser.password)) {
            //message to display when login details matches sign in details
            JOptionPane.showMessageDialog(null, "Login successful!");
            //application displays the following welcoming message
          
            //part 2 
            //welcome message
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
           //program takes user to application to add task when successfully logged in
            application(); // Proceed to the application menu
        } else {
            //message to display when username and password in login does not match sign in details
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            loginUser(); // Retry login
        }
    }

    // Main application menu
    private static void application() {
        //numeric menu displays using JOPtionPane which allows the choose an option using the numbers 1-3
        String userInput = JOptionPane.showInputDialog(null,
                "Option 1) Add Task\n" +
                        "Option 2) Show Report\n" +
                        "Option 3) Quit");
        
        //when user picks number from menu
        //runs correctly and quits on correct input
        if (userInput != null && userInput.matches("\\d+")) {
            int choice = Integer.parseInt(userInput);
            switch (choice) {
                //if user picks 1
                // takes user to addtask()
                case 1:
                    //asks user how many tasks they wish to enter
                    int numberOfTasks = getNumberOfTasks();
                    //counts number of tasks
                    for (int i = 0; i < numberOfTasks; i++) {
                        addtask();
                    }
                    application(); // Return to menu after adding tasks
                    break;
                case 2:
                   // printDetails();
                    //prints a coming soon message since it is still developing
                    JOptionPane.showMessageDialog(null, "Coming soon!");
                    application(); // Return to menu after showing report
                    break;
                case 3:
                    //the program will only quit if this option is picked.
                    break; // Exit the application
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice!");
                    application(); // Return to menu if invalid choice
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input!");
            application(); // Return to menu if invalid input
        }
    }

    // Method to add a new task
    //the application provides adequate output which allows the user to enter 
    //the neccessary task data
    private static void addtask() {
        //ask the user to enter task name
        String taskName = JOptionPane.showInputDialog("Enter task name");
        if (taskName == null) {
            //if the user does not enter anything , following message will display
            JOptionPane.showMessageDialog(null, "Task creation cancelled");
            return;
        }
        //getting the task details
        String taskDescription;
        while (true) {
            //ask user to enter task description
            taskDescription = JOptionPane.showInputDialog("Please enter a task description of less than 50 characters");
            
            //check if user input is not blank 
            if (taskDescription == null) {
                //display message if no input
                JOptionPane.showMessageDialog(null, "Task creation cancelled");
                return;
            }
            //if description meets the requirements
            if (checkTaskDescription(taskDescription)) {
                //display if successfully captured
                JOptionPane.showMessageDialog(null, "Task successfully captured");
                break;
            } else {
                //display if unsuccessful 
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
        }
        //ask user to enter duration 
        String taskDuration = JOptionPane.showInputDialog("Enter task duration");
        //check if users input is blank
        if (taskDuration == null) {
            //display if blank
            JOptionPane.showMessageDialog(null, "Task creation cancelled");
            return;
        }
        
        //check if valid duration entered 
        while (!isValidDuration(taskDuration)) {
            
            JOptionPane.showMessageDialog(null, "Please enter a valid task duration");
            //ask user to enter duration again if invalid
            taskDuration = JOptionPane.showInputDialog("Enter task duration");
           
        }
        
        //ask user for task status
        String taskStatus = getTaskstatus();
        //check if users input is not blank
        if (taskStatus != null) {
            //display task status
            JOptionPane.showMessageDialog(null, "Task Status: " + taskStatus);
        }
        //ask user for their first name
        String firstname = JOptionPane.showInputDialog("Enter developer's first name");
        //ask user for last name
        String lastname = JOptionPane.showInputDialog("Enter developer's last name");
        
        //save variables to use to create task id
        String taskID = createTaskID(taskName, firstname);

        //save all information is one group
        tasks.add(new Task(taskName, taskDescription, taskDuration, taskStatus, firstname, lastname, taskID, taskNumber));
        
        //print all details 
        printDetails();
    }

    // Method to validate task duration
    private static boolean isValidDuration(String duration) {
        try {
            int d = Integer.parseInt(duration);
            return d > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to get the number of tasks the user wants to create
    private static int getNumberOfTasks() {
        while (true) {
            //ask user to enter number of task they wish to enter
            String input = JOptionPane.showInputDialog("Enter the number of tasks you wish to enter:");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Task creation cancelled");
                System.exit(0); // Exit the program if the user cancels
            }
            try {
                //save input in variable
                int numberOfTasks = Integer.parseInt(input);
                //check if it's not a negative number
                if (numberOfTasks > 0) {
                    //displays number of tasks
                    return numberOfTasks;
                } else {
                    //if user enters a negative number, following message will display
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
            }
        }
    }
    
    // Method to check if the task description meets length requirements
    private static boolean checkTaskDescription(String taskDescription) {
      //checks if it is not empty and contails less than 50 characters.
        return taskDescription != null && taskDescription.length() <= 50;
    }

    // Task number counter
    private static int taskNumber = 0;

    // Method to create a task ID based on task name and developer's first name
    private static String createTaskID(String taskName, String firstname) {
        //counts how many tasks there are
        taskNumber++;
        //takes first two letters of task name and changes it to uppercase
        String taskFirst = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
        //takes the last 3 letters of users name and changes it to uppercase
        String taskSecond = firstname.substring(Math.max(0, firstname.length() - 3)).toUpperCase();
        //putting variables in place with colons to meet ID requirements
        return taskFirst + ":" + taskNumber + ":" + taskSecond;
    }

    // Method to get task status from the user
    public static String getTaskstatus() {
        //displays menu to select task statuses from
        String input = JOptionPane.showInputDialog(null,
                "Option 1) To Do\n" +
                        "Option 2) Done\n" +
                        "Option 3) Doing");
        //user enters a task status 
        if (input != null && input.matches("\\d+")) {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    return "To Do";
                case 2:
                    return "Done";
                case 3:
                    return "Doing";
                default:
                    //message if user does not select anything
                    JOptionPane.showMessageDialog(null, "Invalid choice!");
                    return getTaskstatus(); // Recursive call to prompt again
            }
        } else {
            //message if user does not select anything
            JOptionPane.showMessageDialog(null, "Invalid input!");
            return getTaskstatus(); // Recursive call to prompt again
        }
    }

    

    // Method to print the details of all tasks
    private static void printDetails() {
        //calls toString method to display all tasks 
        StringBuilder details = new StringBuilder();
        for (Task task : tasks) {
            details.append(task).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, details.toString());
    }

    
    // Main method to start the program
    public static void main(String[] args) {
        registerUser(); // Register the user
        loginUser();    // Log in the user
    }
}

// Task class to store task details
class Task {
    private String name;
    private String description;
    private String duration;
    private String status;
    private String firstname;
    private String lastname;
    private String taskID;
    private int taskNumber;

    // Constructor for Task
    public Task(String name, String description, String duration, String status, String firstname, String lastname, String taskID, int taskNumber) {
        this.name = name;
        this.taskNumber = taskNumber;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.taskID = taskID;
        
    }

    @Override
    public String toString() {
        //display tasks 
        return "Task Name: " + name + "\n" +
                "Task Number: "  + taskNumber + "\n" +
                "Description: " + description + "\n" +
                "Developer Details: " + firstname + " " + lastname + "\n" +
                "Task Duration: " + duration + "\n" +
                "Task ID: " + taskID+ "\n" +
                "Task Status: " + status ;
                
    }
}
