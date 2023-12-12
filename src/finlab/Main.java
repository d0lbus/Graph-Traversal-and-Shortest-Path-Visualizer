package finlab;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private Scanner kbd = new Scanner(System.in);
    private Graph graph = new Graph();
    public static void main (String[] args){
        Main program;
        try {
            program = new Main();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void loadFile() throws FileNotFoundException {
        LoadFile loadfile = new LoadFile();
        graph = loadfile.loadFile();
    } // end of loadFile method

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

    public void breadthFirst() {
    } // end of breadthFirst method

    public void shortestPath() {
    } // end of shortestPath method

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

    public String readString(String promptMessage) {
        System.out.print(promptMessage);
        return kbd.nextLine();
    } // end of readString method

}
