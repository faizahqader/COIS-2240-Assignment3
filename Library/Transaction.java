import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Transaction {
	private static Transaction instance;
	
	private Transaction() { }

    public static Transaction getTransaction() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    // task2 (2) saveTransaction method
    public void saveTransaction(String transactionDetails) {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
    		writer.write(transactionDetails);
    		writer.newLine();
    	} catch (IOException e) {
    		System.out.println("Error saving transaction: " + e.getMessage());
    	}
    	
    }
    
    // task2 (3) displayTransactionHistory‬‭ method‬
    public void displayTransactionHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            System.out.println("Transaction History: ");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No transaction found or an error occurred: " + e.getMessage());
        }
    }
}






