import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library();
    private Transaction transaction = Transaction.getTransaction();

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                	System.out.print("Enter member name: ");
                    String memberName = scanner.next();
                    
                    scanner.nextLine();

                    Member newMember = new Member(memberId, memberName);
                    if (library.addMember(newMember)) {
                    	System.out.println("Member added successfully.");
                    } else {
                    	System.out.println("Member with ID " + memberId + " already exists.");
                    }
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                	System.out.print("Enter book title: ");
                    String bookTitle = scanner.next();
                    
                    scanner.nextLine();
                    int memberId1 = 0;

                    Book newBook = new Book(bookId, bookTitle); 
                    if (library.addBook(newBook)) {
                    	System.out.println("Book added to library successfully.");
                    } else {
                    	System.out.println("Book with ID " + memberId1 + " already exists.");
                    }
                    break;
                case 3:
                	System.out.println("\n--- Available Members ---");
                    for (Member member : library.getMembers()) {
                        System.out.println(member.getId() + ". " + member.getName());
                    }
                    
                    System.out.print("Enter member ID: ");
                    int memberId2 = scanner.nextInt();
                    
                    System.out.println("\n--- Available Books ---");
                    for (Book book : library.getBooks()) {
                        if (book.isAvailable())
                            System.out.println(book.getId() + ". " + book.getTitle());
                    }
                    
                    System.out.print("Enter book ID: ");
                    int bookId1 = scanner.nextInt();
                    
                    scanner.nextLine();

                    Member member = library.findMemberById(memberId2);
                    Book book = library.findBookById(bookId1);

                    if (member != null && book != null) {
                    	transaction.borrowBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 4:
                	System.out.print("Enter member ID: ");
                    memberId2 = scanner.nextInt();
                    
                    System.out.print("Enter book ID: ");
                    bookId1 = scanner.nextInt();
                    
                    scanner.nextLine();

                    member = library.findMemberById(memberId2);
                    book = library.findBookById(bookId1);

                    if (member != null && book != null) {
                    	transaction.returnBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 5:
                	System.out.print("Enter member ID: ");
                    memberId2 = scanner.nextInt();
                    scanner.nextLine();

                    Member specificMember = library.findMemberById(memberId2);
                    
                    if (specificMember != null) {
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        for (Book bk : specificMember.getBorrowedBooks()) {
                            System.out.println(" - " + bk.getTitle());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }
                    break;
                case 6:
                	transaction.displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("Exiting. Good Bye..");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
