/**
 * @author Chloe Lee San Miguel
 * @author Freskkie Earl Encarnacion
 * @author Nichole Jhoy Escano
 * @author Jan Dolby Aquino
 */

package finlab;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Main class contains the main program for interacting with the graph.
 */
public class Main {
    private Scanner kbd = new Scanner(System.in);
    private Graph graph = new Graph();

    /**
     * The main method that initializes the program and runs the main menu loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main (String[] args){
        Main program;
        try {
            program = new Main();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main loop of the program that displays the main menu and processes user choices.
     */
    public void run() {
        int choice = 0;
        while (choice != 5) {
            mainMenu();
            try {
                choice = Integer.parseInt(readString("Please select from the options above: "));
                if (choice < 1 || choice > 5) {
                    throw new IllegalArgumentException("Invalid choice. Please enter a number between 1 and 5.");
                }
                switch (choice) {
                    case 1 -> loadFile();
                    case 2 -> depthFirst();
                    case 3 -> breadthFirst();
                    case 4 -> shortestPath();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\nProgram terminated.\nThank you for using this program!\n");
        System.exit(0);
    } // end of run method

    /**
     * Loads the graph data from a file.
     *
     * @throws FileNotFoundException If the specified file is not found.
     */
    public void loadFile() throws FileNotFoundException {
        LoadFile loadfile = new LoadFile();
        graph = loadfile.loadFile();
    } // end of loadFile method

    /**
     * Performs a depth-first traversal of the graph.
     */
    public void depthFirst() {
        try {
            if (graph.getVertices().isEmpty()) {
                loadFile();
            }

            GraphTraversal graphTraversal = new GraphTraversal(graph);
            String startVertexIdString = readString("Enter a starting vertex ID for Depth First Traversal: ");

            Vertex startVertex = graph.getVertexById(startVertexIdString);

            if (startVertex == null) {
                throw new IllegalArgumentException("Invalid vertex ID. Please enter a valid vertex ID.");
            }

            String result = graphTraversal.depthFirstTraversal(startVertex);
            System.out.println("Depth First Traversal Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred during Depth First Traversal: " + e.getMessage());
        }
    }// end of depthFirst method

    /**
     * Performs a breadth-first traversal of the graph.
     */
    public void breadthFirst() {
        try {
            if (graph.getVertices().isEmpty()) {
                loadFile();
            }
    
            GraphTraversal graphTraversal = new GraphTraversal(graph);
            String startVertexIdString = readString("Enter a starting vertex ID for Breadth First Traversal: ");
            
            Vertex startVertex = graph.getVertexById(startVertexIdString); 
    
            if (startVertex == null) {
                throw new IllegalArgumentException("Invalid vertex ID. Please enter a valid vertex ID.");
            }

        String result = graphTraversal.breadthFirstTraversal(startVertex);
        System.out.println("Breadth First Traversal Result: " + result);
       } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
       } catch (Exception e) {
        System.out.println("An error occured during Breadth First Traversal: " + e.getMessage());
       }

    } // end of breadthFirst method

    /**
     * Placeholder method for performing the shortest path operation.
     */
    public void shortestPath() {
    } // end of shortestPath method

    /**
     * Displays the main menu options to the user.
     */
    public void mainMenu() {
        System.out.println("=========================================================");
        System.out.println("|                  FINAL GROUP PROJECT                  |");
        System.out.println("| 1. Load File Containing the Graph's Data              |");
        System.out.println("| 2. Perform Depth First Traversal of the Graph         |");
        System.out.println("| 3. Perform Breadth First Traversal of the Graph       |");
        System.out.println("| 4. Show the Shortest Path from One Vertex to Another  |");
        System.out.println("| 5. Exit                                               |");
        System.out.println("=========================================================");
    } // end of mainMenu method

    /**
     * Reads a string input from the user with a specified prompt message.
     *
     * @param promptMessage The prompt message to display to the user.
     * @return The string entered by the user.
     */
    public String readString(String promptMessage) {
        System.out.print(promptMessage);
        return kbd.nextLine();
    } // end of readString method
} // end of Main class
