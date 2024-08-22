# Case study 1 : Designing a Parking Lot System

## Table of Contents
1. [Overview of the System](#overview-of-the-system)
2. [Requirement Gathering](#requirement-gathering)
3. [Class Diagram](#class-diagram)
4. [Schema Design](#schema-design)
5. [Code Implementations](#code-implementations)

## Overview of the System
> A parking lot is a designated area for parking vehicles and is a feature found in almost all popular venues such as shopping malls, sports stadiums, offices, etc. In a parking lot, there are a fixed number of parking spots available for different types of vehicles. Each of these spots is charged according to the time the vehicle has been parked in the parking lot. The parking time is tracked with a parking ticket issued to the vehicle at the entrance of the parking lot. Once the vehicle is ready to exit, it can either pay at the automated exit panel or to the parking agent at the exit using a card or cash payment method.

## Design approach 
1. Top down Approach 
    - In this we visualize system from Top & see the big picture, say Parking Lot System. 
    - Then we deep drive into each component & solve them.
2. Bottom up Approach
    - In this approach, we visualize the lower parts of systems & keep building on top of them 
    - We keep decorating the components & wire up all till we move to top component, say Parking Lot System

## Requirement Gathering
-- For this case study we follow Top down approach, 
- R1 : From the top, we can visualize that we have a parking lot, where there are multiple spots available 
- R2 : Parking Lot will be consisting of multiple gate, both entry & exit gates 
- R3 : Each spot we can define what type of vehicle it can be parked 
- R4 : For simplicity we will allow 4 common types of vehicle, CAR, BIKE, SCOTY, VAN/XUV
- R5 : The system includes a display board, which update periodically once spot if available or occupied
- R6 : For our system, there are 3 main actors, users & parking agents & system 
- R7 : We will also store vehicle details
- R8 : At each entry gate we can have a ticket allotment handled by either parking agents or system itself
- R9 : At each exit gate we can have payment arragements for users to pay
- R10 : The payment should be calculated at an hourly rate.

## Present a class diagram illustrating the structure of the system. Include all the key classes, their attributes, methods, and the relationships between them.

Core Classes

    ParkingLot
        Attributes: List<Gate> gates, List<ParkingSpot> spots, DisplayBoard displayBoard
        Methods: assignTicket(Vehicle vehicle), calculateFee(Ticket ticket)

    Gate (Abstract Class)
        Attributes: int gateId, String gateType
        Methods: Ticket generateTicket(Vehicle vehicle) (for entry gates), void processPayment(Ticket ticket) (for exit gates)

    EntryGate (Extends Gate)
        Methods: Ticket generateTicket(Vehicle vehicle)

    ExitGate (Extends Gate)
        Methods: void processPayment(Ticket ticket)

    ParkingSpot
        Attributes: int spotId, SpotType type, boolean isOccupied
        Methods: boolean isAvailable(), void assignVehicle(Vehicle vehicle), void releaseVehicle()

    DisplayBoard
        Attributes: Map<SpotType, Integer> availableSpots
        Methods: void updateBoard()

    Vehicle (Abstract Class)
        Attributes: String licenseNumber, VehicleType type
        Methods: getLicenseNumber(), getType()

    Car, Bike, Scooty, Van (Extends Vehicle)
        Attributes (inherited from Vehicle)

    Ticket
        Attributes: String ticketId, Vehicle vehicle, Date entryTime, Date exitTime, ParkingSpot spot
        Methods: getParkingDuration(), calculateFee()

    User
        Attributes: String userId, Vehicle vehicle
        Methods: Ticket enterParkingLot(Vehicle vehicle), void exitParkingLot(Ticket ticket)

    ParkingAgent (Extends User)
        Methods: Ticket issueTicket(Vehicle vehicle), void collectPayment(Ticket ticket)

    System
        Attributes: ParkingLot parkingLot
        Methods: static System getInstance(), void manageParkingLot()

## Design patterns 
- Singleton Pattern for System: Ensures that only one instance of the system manages the parking lot.
- Factory Pattern for Gate Creation: You can use a factory to create EntryGate and ExitGate objects.
- Strategy Pattern for Fee Calculation: The fee calculation logic can be implemented using different strategies (e.g., hourly rate, daily rate).
- Observer Pattern for DisplayBoard: The display board can be an observer to the parking lot, updating its state when spot availability changes.

## Schema Design
Provide a detailed schema design for the database. Explain the tables, fields, relationships, and any indexing strategies used for optimizing the system.

## Code Implementations

