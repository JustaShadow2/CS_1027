public class FindShortestPath { // This is the main class for the shortest path program
    public static void main(String[] args) { 
        try {
            if (args.length < 1) { throw new Exception("No input file specified"); } // Check that an input file name has been specified
            String dungeonFileName = args[0]; // Get the input file name from the command line
            Dungeon dungeon = new Dungeon(dungeonFileName); // Create a new dungeon object
            DLPriorityQueue<Hexagon> queue = new DLPriorityQueue<Hexagon>(); // Create a new empty priority queue
            Hexagon initialChamber = dungeon.getStart(); // Get the start chamber
            initialChamber.markEnqueued(); // Mark the start chamber as enqueued
            queue.add(initialChamber, 0); // Add the start chamber to the queue with priority 0
            while (!queue.isEmpty() && !initialChamber.isExit()) { // While the queue is not empty and the current chamber is not the exit
                initialChamber = queue.removeMin(); // Remove the chamber with the lowest priority from the queue
                initialChamber.markDequeued(); // Mark the chamber as dequeued
                if (initialChamber.isExit()) { break; } // If the chamber is the exit then stop
                // following block checks if neighbours have dragons, if so then remove the chamber from the queue and start again. make sure that it is not null
                for (int i = 0; i < 6; i++) {
                    if (initialChamber.getNeighbour(i) != null && initialChamber.getNeighbour(i).isDragon()) {
                        initialChamber = queue.removeMin();
                        initialChamber.markDequeued();
                        break;
                    }
                }
                for (int i = 0; i < 6; i++) { // For each neighbour of the current chamber
                    if (initialChamber.getNeighbour(i) != null && !initialChamber.getNeighbour(i).isWall() && !initialChamber.getNeighbour(i).isMarkedDequeued() && !initialChamber.isDragon() && !initialChamber.getNeighbour(i).isDragon()) { // If the neighbour is not a wall, not marked as dequeued, and not a dragon
                        int D = initialChamber.getDistanceToStart() + 1; // Calculate the distance to the neighbour
                        if (initialChamber.getNeighbour(i).getDistanceToStart() > D) { // If the distance to the neighbour is less than the current distance to the neighbour
                            initialChamber.getNeighbour(i).setDistanceToStart(D); // Set the distance to the neighbour to the new distance
                            initialChamber.getNeighbour(i).setPredecessor(initialChamber); // Set the predecessor of the neighbour to the current chamber
                            if (initialChamber.getNeighbour(i).isMarkedEnqueued()) { // If the neighbour is marked as enqueued                    
                                queue.updatePriority(initialChamber.getNeighbour(i), initialChamber.getNeighbour(i).getDistanceToStart() + initialChamber.getNeighbour(i).getDistanceToExit(dungeon));  // Update the priority of the neighbour
                            } else {
                                initialChamber.getNeighbour(i).markEnqueued(); // Mark the neighbour as enqueued
                                queue.add(initialChamber.getNeighbour(i), initialChamber.getNeighbour(i).getDistanceToStart() + initialChamber.getNeighbour(i).getDistanceToExit(dungeon)); // Add the neighbour to the queue with priority equal to the distance to the neighbour plus the distance to the exit
                            }
                        }
                    }
                }
            }
            System.out.println(initialChamber.getDistanceToStart() + 1); // Print the distance to the exit
        } catch (EmptyPriorityQueueException e) { // If the queue is empty and the exit has not been found
            System.out.println("No path found"); // Print that no path was found
        } catch (Exception e) { // If an exception is thrown
            System.out.println(e.getMessage()); // Print the exception message
        }
    }
}

            



