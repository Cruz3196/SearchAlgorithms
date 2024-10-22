import java.util.Scanner;
//PLEASE RUN THIS APPLICATION ON TERMINAL 

public class StateSearchApp {
    static String[] states = {
        "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota","Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
// Creating the selection for the user 
        while(running){
            System.out.println("\nMenu: ");
            System.out.println("1) Display the text ");
            System.out.println("2) Search ");
            System.out.println("3) Exit Program ");
            System.out.println("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch(choice){
                case 1: 
                    displayStates();
                    break;
                case 2: 
                    System.out.print("Enter a pattern to search: ");
                    String pattern = scanner.nextLine();
                    searchPatten(pattern);
                    break;
                case 3: 
                    running = false;
                    System.out.println("Exiting the program.");
                    break;
                default: 
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    // displaying the states in a list 
    public static void displayStates(){
        System.out.println("\nList of States");
        for(String state : states){
            System.out.println(state);
        }
    }

    // implementing the boyer - moore bad character rule for pattern searching 
    public static void searchPatten(String pattern){
        String text = String.join(" ", states);
        int [] badChar = badCharacterHeuristic(pattern);
        int m = pattern.length();
        int n = text.length();

        int s = 0; //s is the shift of the pattern with respect of text
        while (s <= (n - m)){
            int j = m - 1;

            // keep reducing j while characters of pattern and text are matching.
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)){
                j--;
            }
            // if the pattern is not found
            if (j < 0){
                System.out.println("Pattern found at index: " + s);
                // shift the pattern based on bad character 
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }
    }
    // building the bad character heurisitc array 
    public static int[] badCharacterHeuristic(String pattern) {
        int[] badChar = new int[256]; 

        for (int i = 0; i < 256; i++){
            badChar[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++){
            badChar[pattern.charAt(i)] = i;
        }
        return badChar;
    }
}