package OOPS;

// Base Abstraction
abstract class Person {
    String name;
    int age;
    String contact;

    Person(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    // Abstract method
    abstract String getRole();

    // Polymorphism: overridden later
    abstract void checkIn();
}

// ================= Inheritance =================

// Passenger extends Person
class Passenger extends Person {
    Passenger(String name, int age, String contact) {
        super(name, age, contact);
    }

    @Override
    String getRole() {
        return "Passenger";
    }

    @Override
    void checkIn() {
        System.out.println(name + " (Passenger) checked in with boarding pass.");
    }
}

// Staff extends Person
abstract class Staff extends Person {
    Staff(String name, int age, String contact) {
        super(name, age, contact);
    }

    @Override
    void checkIn() {
        System.out.println(name + " (Staff) checked in with staff ID.");
    }
}

// Pilot extends Staff
class Pilot extends Staff implements Flyable {
    Pilot(String name, int age, String contact) {
        super(name, age, contact);
    }

    @Override
    String getRole() {
        return "Pilot";
    }

    @Override
    public void flyPlane() {
        System.out.println(name + " is flying the plane.");
    }
}

// Cabin Crew extends Staff
class CabinCrew extends Staff {
    CabinCrew(String name, int age, String contact) {
        super(name, age, contact);
    }

    @Override
    String getRole() {
        return "Cabin Crew";
    }
}

// ============ Interface ============
interface Flyable {
    void flyPlane();
}

// ============ Association ============
class Flight {
    String flightNumber;
    Cockpit cockpit; // Composition

    Flight(String flightNumber) {
        this.flightNumber = flightNumber;
        this.cockpit = new Cockpit(); // created when flight is created
    }

    void showDetails() {
        System.out.println("Flight " + flightNumber + " is scheduled.");
    }

    // Composition class (ceases with Flight)
    class Cockpit {
        void cockpitDetails() {
            System.out.println("Cockpit of flight " + flightNumber + " ready.");
        }
    }
}

// ============ Aggregation ============
class Airline {
    static String airlineName = "SkyHigh Airlines"; // static field
    java.util.List<Flight> flights = new java.util.ArrayList<>();

    void addFlight(Flight flight) {
        flights.add(flight);
    }

    void showFlights() {
        System.out.println("Airline: " + airlineName + " has flights:");
        for (Flight f : flights) {
            System.out.println("  - " + f.flightNumber);
        }
    }
}

// ============ Final Class ============
final class Ticket {
    String ticketNumber;

    Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    void showTicket() {
        System.out.println("Ticket Number: " + ticketNumber);
    }
}

// ============ Main ============

public class AirlineReservationSystem {
    public static void main(String[] args) {
        // Dynamic Method Dispatch
        Person p1 = new Passenger("Alice", 25, "1234567890");
        Person p2 = new Pilot("Bob", 40, "9876543210");
        Person p3 = new CabinCrew("Clara", 30, "5555555555");

        // Roles
        System.out.println(p1.getRole());
        System.out.println(p2.getRole());
        System.out.println(p3.getRole());

        // Dynamic dispatch for checkIn()
        p1.checkIn(); // Passenger checkIn
        p2.checkIn(); // Pilot checkIn (staff version)
        p3.checkIn(); // Cabin Crew checkIn

        // Interface usage
        ((Pilot)p2).flyPlane();

        // Aggregation
        Airline airline = new Airline();
        Flight f1 = new Flight("AI101");
        Flight f2 = new Flight("AI202");

        airline.addFlight(f1);
        airline.addFlight(f2);
        airline.showFlights();

        // Composition (Cockpit dies if Flight dies)
        f1.cockpit.cockpitDetails();

        // Final class Ticket
        Ticket t1 = new Ticket("TK123");
        t1.showTicket();
    }
}
