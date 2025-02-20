Experiment 4.3: Ticket Booking System

  class TicketBookingSystem {
    private boolean[] seats;
    private static final int TOTAL_SEATS = 5;

    public TicketBookingSystem() {
        seats = new boolean[TOTAL_SEATS];
    }

    public synchronized void bookSeat(int seatNumber, String customerName, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (seats[seatNumber - 1]) {
            System.out.println(customerName + ": Seat " + seatNumber + " is already booked!");
        } else {
            seats[seatNumber - 1] = true;
            String userType = isVIP ? "VIP" : "Regular";
            System.out.println(customerName + " (" + userType + ") booked seat " + seatNumber);
        }
    }

    public void displayBookingStatus() {
        for (int i = 0; i < TOTAL_SEATS; i++) {
            System.out.println("Seat " + (i + 1) + ": " + (seats[i] ? "Booked" : "Available"));
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private int seatNumber;
    private String customerName;
    private boolean isVIP;

    public BookingThread(TicketBookingSystem bookingSystem, int seatNumber, String customerName, boolean isVIP) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber, customerName, isVIP);
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        bookingSystem.displayBookingStatus();

        BookingThread anish = new BookingThread(bookingSystem, 1, "Anish", true);
        BookingThread bobby = new BookingThread(bookingSystem, 2, "Bobby", false);
        BookingThread charlie = new BookingThread(bookingSystem, 3, "Charlie", true);

        anish.start();
        bobby.start();
        charlie.start();

        anish.join();
        bobby.join();
        charlie.join();

        BookingThread bobbyLow = new BookingThread(bookingSystem, 4, "Bobby", false);
        BookingThread anishHigh = new BookingThread(bookingSystem, 4, "Anish", true);
        bobbyLow.setPriority(Thread.MIN_PRIORITY);
        anishHigh.setPriority(Thread.MAX_PRIORITY);

        anishHigh.start();
        bobbyLow.start();

        anishHigh.join();
        bobbyLow.join();

        BookingThread bobbyAgain = new BookingThread(bookingSystem, 1, "Bobby", false);
        bobbyAgain.start();
        bobbyAgain.join();

        BookingThread regularUser = new BookingThread(bookingSystem, 3, "Regular User", false);
        regularUser.start();
        regularUser.join();

        BookingThread invalidSeat = new BookingThread(bookingSystem, 0, "Invalid User", false);
        BookingThread outOfRange = new BookingThread(bookingSystem, 6, "OutOfRange User", false);
        invalidSeat.start();
        outOfRange.start();
        invalidSeat.join();
        outOfRange.join();

        BookingThread[] users = new BookingThread[10];
        for (int i = 0; i < 10; i++) {
            users[i] = new BookingThread(bookingSystem, (i % 5) + 1, "User" + (i + 1), i % 2 == 0);
            users[i].start();
        }

        for (BookingThread user : users) {
            user.join();
        }

        bookingSystem.displayBookingStatus();
    }
}



This program simulates a ticket booking system where multiple users (threads) try to book seats at the same time. The key challenges addressed are:

1) Avoiding Double Booking → Using synchronized methods to ensure no two users book the same seat.
2) Prioritizing VIP Customers → Using thread priorities so VIP users' bookings are processed before regular users.

📌 Core Concepts Used
️1 Synchronized Booking Method
The method bookSeat() is marked as synchronized, ensuring that only one thread can access it at a time.
This prevents race conditions, where two threads might try to book the same seat simultaneously.
  
️2 Thread Priorities for VIP Customers
Threads representing VIP users are assigned Thread.MAX_PRIORITY so they execute first.
Regular users have Thread.NORM_PRIORITY or Thread.MIN_PRIORITY, making them process later.

3 Handling Multiple Users
Each user trying to book a seat is represented by a thread.
Users can select a seat, and if it’s already booked, they receive an error message.


Step-by-Step Execution
1 Initialize the TicketBookingSystem → Allows booking of N seats.
2 Create Multiple Booking Threads → Each user (VIP or Regular) is assigned a thread.
3 Start All Threads → Threads compete for booking, with VIPs processed first.
4 Ensure No Double Booking → synchronized method prevents duplicate seat allocation.
5 Threads Finish Execution & Display Booking Status.


🔹 Why Use Synchronization?
Without synchronized, two threads might book the same seat simultaneously, causing double booking issues. Using synchronized, only one thread at a time can modify the seat booking data.

🔹 Why Use Thread Priorities?
Setting higher priority for VIP users ensures their bookings are processed first, simulating real-world priority-based bookings.

Test Cases

Test Case 1: No Seats Available Initially
Input:
System starts with 5 seats.
No users attempt to book.
Expected Output:
No bookings yet.

Test Case 2: Successful Booking
Input:
Anish (VIP) books Seat 1.
Bobby (Regular) books Seat 2.
Charlie (VIP) books Seat 3.
Expected Output:
Anish (VIP) booked seat 1
Bobby (Regular) booked seat 2
Charlie (VIP) booked seat 3

Test Case 3: Thread Priorities (VIP First)
Input:
Bobby (Regular) books Seat 4 (low priority).
Anish (VIP) books Seat 4 (high priority).
Expected Output:
Anish (VIP) booked seat 4
Bobby (Regular): Seat 4 is already booked!

Test Case 4: Preventing Double Booking
Input:
Anish (VIP) books Seat 1.
Bobby (Regular) tries to book Seat 1 again.
Expected Output:
Anish (VIP) booked seat 1
Bobby (Regular): Seat 1 is already booked!

Test Case 5: Booking After All Seats Are Taken
Input:
All 5 seats are booked.
A new user (Regular) tries to book Seat 3.
Expected Output:
Error: Seat 3 is already booked!

Test Case 6: Invalid Seat Selection
Input:
User tries to book Seat 0 (out of range).
User tries to book Seat 6 (beyond available seats).
Expected Output:
Invalid seat number!

Test Case 7: Simultaneous Bookings (Concurrency Test)
Input:
10 users try booking at the same time for 5 seats.
Expected Output:
5 users successfully book seats.
5 users receive error messages for already booked seats.
