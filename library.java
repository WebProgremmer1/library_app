import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, String> map = new HashMap<>();
    static HashMap<String, String> borrowed = new HashMap<>();

    public static void main(String[] args){ 
        boolean exitCheck = true;
        int choice;

        
        map.put("Frankenstein", "Mary Shelley\n");
        map.put("1984", "George Orwell\n");
        map.put("Pride and Prejudice", "Jane Austen\n");
        map.put("The Great Gatsby", "F. Scott Fitzgerald\n");
        map.put("The Catcher in the Rye", "J. D. Salinger\n");


        while(exitCheck){
            System.out.println("************");
            System.out.println("Library books");
            System.out.println("************");

            System.out.println("Add book (title, author): ");
            System.out.println("List books: ");
            System.out.println("Borrow book");
            System.out.println("Return book");
            System.out.println("Search by title");
            System.out.println("Exit");
        

            System.out.println("Enter 1 - 6: ");
            choice = scanner.nextInt();
        
            switch(choice){
                case 1 -> {
                System.out.print("Title: ");
                String title = scanner.nextLine();
                while (title.isBlank()) title = scanner.nextLine(); // in case it got an empty line

                System.out.print("Author: ");
                String author = scanner.nextLine();
                
                addBook(title, author);
                }
                case 2 -> showBook();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> searchBook();
                case 6 -> exitCheck = false;
                default -> System.out.println("No such command");
            }
        }
    }

    static Map.Entry<String, String> askTitleAuthor(String message) {
        System.out.println(message);
        System.out.print("Title: ");
        String title = scanner.nextLine();
        while (title.isBlank()) title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();
        while (author.isBlank()) author = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(title, author);
    }
    static void addBook(String title, String author){
        map.put(title, author);
        System.out.println(title + " was added");
    }
    static void showBook(){
        System.out.println(map);
    }
    static void borrowBook(){
        Map.Entry<String, String> book = askTitleAuthor("Borrow book");
        String title = book.getKey();
        
        if(!(map.containsKey(title))){
            System.out.println("Sorry, library does not have such nool ");
            return;
        }
        map.remove(title);
        borrowed.put(title, map.get(title));
        System.out.println(title);

    }
    static void returnBook(){
        Map.Entry<String, String> book = askTitleAuthor("Return book");
        String title = book.getKey();
        if(!(borrowed.containsKey(title))){
            System.out.println("This title is not in borrowed list");
            return;
        }
        map.put(title, borrowed.get(title));
        borrowed.remove(title);
        System.out.println(title);
    }
    static void searchBook(){
        System.out.println("What book are you looking for ?");
        String search = scanner.nextLine();
        while (search.isBlank()) search = scanner.nextLine();

        boolean check = map.containsKey(search);
        if(check){
            System.out.println("Library has such book");
        }
        else{
            System.out.println("No book like that");
        }
    }

}
