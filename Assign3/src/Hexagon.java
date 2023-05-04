import java.awt.Color;

/**
 * This class represents a pointed-top Hexagon chamber used to make up a
 * <code>Dungeon</code> object.
 * <p>
 * Each chamber has a type. It can be a Wall, Start, End, Empty, DEQUEUED,
 * Dragon, Lava, Cactus, Start_Processed, Start_DEQUEUED, End_Processed, or
 * ENQUEUED chamber. Each chamber type will be a different colour:.
 * <p>
 * Hexagon chambers know about their neighbors (if set using setNeighbor
 * method).
 * <p>
 * The neighbors of a chamber are accessed by an index 0-5 inclusive.
 * <ul>
 * <li>The hexagons are pointed-top in orientation, the 0 index is the
 * upper-right side</li>
 * <li>Indexes for the sides progress incrementally clockwise from the 0 index,
 * to 5 on the upper-left side</li>
 * </ul>
 * Eg.
 * <p>
 * <code>    5 /  \ 0</code>
 * <p>
 * <code>    4 |  | 1</code>
 * <p>
 * <code>    3 \  / 2</code>
 * <p>
 * 
 * @author CS1027
 *
 */
public class Hexagon extends HexComponent {
	private static final long serialVersionUID = 4865976127980106774L;

	// Available hexagon types
	public static enum HexType {
		WALL, START, END, EMPTY, START_PROCESSED, END_PROCESSED, START_DEQUEUED, ENQUEUED, DEQUEUED, DRAGON, LAVA, CACTI, PATH, IN_PATH
	};

	// Attributes
	private HexType type; // Stores the current type of this Hexagon. This value
							// changes as
							// the Hexagons in the board are marked
	private HexType originalType; // Type initially assigned to this Hexagon
	private boolean isStart; // Is this the initial chamber?
	private boolean isEnd; // Is this the exit?
	private Hexagon[] neighbors; // Stores the hexagons which surround this one
									// on each of 6 sides
	private int timeDelay; // Delay before drawing this chamber on the screen
	private int distanceToStart; // Distance from the chamber represented by
									// this Hexagon to the initial chamber
	private Hexagon predecessor = null; // Predecessor in the shortest path to
										// exit

	/**
	 * Create a Hexagon chamber of the specified type
	 * 
	 * @param t
	 *            the HexType to create
	 */
	public Hexagon(HexType t, int delay) {
		timeDelay = delay;
		type = t;
		originalType = t;
		predecessor = null;
		isStart = (t == HexType.START);
		isEnd = (t == HexType.END);

		// set the initial color based on the initial type
		setColor(this.type);
		// allocate space for the neighbor array
		neighbors = new Hexagon[6];
		if (isStart)
			distanceToStart = 0;
		else
			distanceToStart = Integer.MAX_VALUE; // Distance to initial chamber
													// is not known
	}

	public HexType getType() {
		for (int i = 0; i < 6; ++i)
			if (neighbors[i] != null && neighbors[i].originalType == HexType.DRAGON)
				return HexType.DRAGON;
		return originalType;
	}

	/**
	 * Set the neighbor for this hexagon using the neighbor index.
	 * 
	 * The index for the neighbor indicates which side of the hexagon this new
	 * neighbor is on. 0-5 inclusive.
	 * 
	 * @param neighbor
	 *            The new Hexagon neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-5
	 *            inclusive)
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-5 inclusive.
	 */
	public void setNeighbour(Hexagon neighbor, int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i <= 5)
			this.neighbors[i] = neighbor;
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * Returns the neighbor for this hexagon using the neighbor index
	 * 
	 * The index for the neighbor indicates which side of the hexagon the
	 * neighbor to get is on. 0-5 inclusive.
	 * 
	 * @param i
	 *            The index indicating the side of the hexagon this neighbor is
	 *            on
	 * @return The hexagon that is on the i-th side of the current hexagon, or
	 *         null if no neighbor
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-5 inclusive.
	 */
	public Hexagon getNeighbour(int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i <= 5)
			return this.neighbors[i];
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * This method checks if the current hexagon is a wall chamber.
	 * 
	 * @return true if this is a wall chamber, false otherwise.
	 */
	public boolean isWall() {
		return originalType == HexType.WALL;
	}

	/**
	 * This method checks if the current hexagon is an empty chamber.
	 * 
	 * @return true if this chamber is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return originalType == HexType.EMPTY;
	}

	/**
	 * This method checks if the current Hexagon has been marked as dequeued
	 * 
	 * @return true if this chamber has been marked as dequeued, false
	 *         otherwise.
	 */

	public boolean isMarkedDequeued() {
		return (type == HexType.DEQUEUED);
	}

	public void setInPath() {
		type = HexType.IN_PATH;
	}

	public boolean inPath() {
		return type == HexType.IN_PATH;
	}

	/**
	 * This method checks if the current Hexagon has been marked as enqueued
	 * 
	 * @return true if this chamber has been marked as enqueued, false
	 *         otherwise.
	 */

	public boolean isMarkedEnqueued() {
		return (type == HexType.ENQUEUED);
	}

	/**
	 * This method checks if the current Hexagon object hosts a dragon. This
	 * means that no adjacent Hexagon object can be in the path to the end
	 * 
	 * @return true if chamber has a dragon, false otherwise.
	 */
	public boolean isDragon() {
		return originalType == HexType.DRAGON;
	}

	/**
	 * This method checks if the current hexagon is a lava chamber.
	 * 
	 * @return true if this is a lava chamber, false otherwise.
	 */
	public boolean isLava() {
		return originalType == HexType.LAVA;
	}

	/**
	 * This method checks if the current hexagon is a cacti chamber.
	 * 
	 * @return true if this is a cacti chamber, false otherwise.
	 */
	public boolean isCacti() {
		return originalType == HexType.CACTI;
	}

	/**
	 * This method checks if the current hexagon is the starting chamber.
	 * 
	 * @return true if this is the starting chamber, false otherwise.
	 */
	public boolean isStart() {
		return this.isStart;
	}

	/**
	 * This method checks if the current hexagon is the exit.
	 * 
	 * @return true if this is the exit, false otherwise.
	 */
	public boolean isExit() {
		return this.isEnd;
	}

	/**
	 * This re-draws the current hexagonal chamber
	 */
	private void reDraw() {
		try {
			Thread.sleep(timeDelay);
		} catch (Exception e) {
			System.err.println("Error while issuing time delay\n" + e.getMessage());
		}
		super.repaint();
	}

	/**
	 * This method marks the chamber as ENQUEUED and updates the chamber's
	 * colour
	 */
	public void markEnqueued() {
		type = HexType.ENQUEUED;
		setColor(type);
		reDraw();
	}

	/**
	 * This method marks the chamber as DEQUEUED and updates the chamber's
	 * colour
	 */
	public void markDequeued() {
		type = HexType.DEQUEUED;
		if (this.isExit()) {
			Hexagon prev = this;
			while (prev != null) {
				prev.setColor(HexType.PATH);
				reDraw();
				prev = prev.getPredecessor();
			}
		} else {
			setColor(this.type);
			reDraw();
		}
	}

	/**
	 * This method marks the exit chamber and updates the chamber's colour
	 */
	public void markExit() {
		this.type = HexType.END_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the chamber as the starting chamber and updates the
	 * chamber's colour
	 */
	public void markStart() {
		this.type = HexType.START_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * Helper method to set the current chamber color based on the type of
	 * chamber.
	 * 
	 * @param t
	 *            The type of the chamber; used to set the color
	 */
	private void setColor(HexType t) {
		switch (t) {
		case WALL:
			this.setBackground(HexColors.WALL);
			break;
		case START:
			this.setBackground(HexColors.START);
			break;
		case END:
			this.setBackground(HexColors.END);
			break;
		case EMPTY:
			this.setBackground(HexColors.UNVISITED);
			break;
		case END_PROCESSED:
			this.setBackground(HexColors.END_PROCESSED);
			break;
		case START_PROCESSED:
			this.setBackground(HexColors.START_PROCESSED);
			break;
		case START_DEQUEUED:
			this.setBackground(HexColors.START_DEQUEUED);
			break;
		case ENQUEUED:
			if (originalType == HexType.LAVA)
				setBackground(HexColors.LAVA_ENQUEUED);
			else if (originalType == HexType.CACTI)
				setBackground(HexColors.CACTUS_ENQUEUED);
			else if (originalType == HexType.START)
				this.setBackground(HexColors.START_PROCESSED);
			else if (originalType == HexType.END)
				this.setBackground(HexColors.END_PROCESSED);
			else
				setBackground(HexColors.ENQUEUED);
			break;
		case DEQUEUED:
			if (originalType == HexType.LAVA)
				setBackground(HexColors.LAVA_DEQUEUED);
			else if (originalType == HexType.CACTI)
				setBackground(HexColors.CACTUS_DEQUEUED);
			else if (originalType == HexType.START)
				setBackground(HexColors.START_DEQUEUED);
			else if (originalType == HexType.END)
				this.setBackground(HexColors.END_DEQUEUED);
			else
				setBackground(HexColors.DEQUEUED);
			break;
		case PATH:
			if (originalType == HexType.LAVA)
				setBackground(HexColors.LAVA_PATH);
			else if (originalType == HexType.CACTI)
				setBackground(HexColors.CACTUS_PATH);
			else if (originalType == HexType.START)
				setBackground(HexColors.START_PATH);
			else if (originalType == HexType.END)
				setBackground(HexColors.END_PATH);
			else
				setBackground(HexColors.PATH);
			break;
		case DRAGON:
			this.setBackground(HexColors.DRAGON);
			break;
		case LAVA:
			this.setBackground(HexColors.LAVA);
			break;
		case CACTI:
			this.setBackground(HexColors.CACTUS);
			break;
		default:
			this.setBackground(HexColors.WALL);
			break;
		}
		this.setForeground(Color.BLACK);
	}

	public String toString() {
		if (type == HexType.WALL)
			return "Walled chamber, ";
		else if (type == HexType.START || type == HexType.START_PROCESSED || type == HexType.START_DEQUEUED
				|| originalType == HexType.START)
			return "Initial chamber, ";
		else if (type == HexType.END || type == HexType.END_PROCESSED || originalType == HexType.END)
			return "Exit, ";
		else if (type == HexType.EMPTY || originalType == HexType.EMPTY)
			return "Empty chamber, ";
		else if (type == HexType.LAVA || originalType == HexType.LAVA)
			return "Lava chamber, ";
		else if (type == HexType.CACTI || originalType == HexType.CACTI)
			return "Cacti chamber, ";
		else if (type == HexType.DRAGON)
			return "Dragon chamber, ";
		else
			return "";
	}

	/**
	 * Returns the predicted distance from this chamber to the exit of the
	 * dungeon. The predicted distance is simply the Euclidean distance. If
	 * there is no exit to the dungeon then a distance of 0 is returned
	 * 
	 * 
	 * @param The
	 *            dungeon to which the chamber belongs
	 * @return The predicted distance to the exit
	 */

	public double getDistanceToExit(Dungeon d) {
		double cell = (double) d.getCellSize();
		double heightToWidthRatio;

		if (cell > 100)
			heightToWidthRatio = 1.0;
		else
			heightToWidthRatio = 1.15;

		double dist = 0;
		if (isExit())
			return 0;

		try {
			double x1, y1, x2, y2;
			x1 = (double) this.getX();
			x2 = (double) d.getExit().getX();
			y1 = (double) this.getY();
			y2 = (double) d.getExit().getY();

			// 1:1.2 ratio of height to width of displayed hexagons
			dist = Math.sqrt(Math.pow((x1 - x2) * 1.2, 2) + Math.pow(y1 - y2, 2)) / cell;
		} catch (NullPointerException e) {
			return 0;
		}

		if (dist == 0)
			return 1; // Distance to exit is at least 1
		else
			return dist;
	}

	/**
	 * Returns the current estimation of the distance from the inital chamber to
	 * this chamber
	 * 
	 * @return The estimated distance to the initial chamber
	 */
	public int getDistanceToStart() {
		return distanceToStart;
	}

	/**
	 * Changes the current estimation of the distance from the inital chamber to
	 * this chamber
	 * 
	 * @param The
	 *            estimation of the distance to the initial chamber
	 */
	public void setDistanceToStart(int dist) {
		distanceToStart = dist;
	}

	/**
	 * Returns true if this Hexagon object is being referenced by otherChamber
	 * 
	 * @param otherChamber
	 *            Hexagon reference
	 * @return true if the parameter points to this Hexagon
	 */
	public boolean equals(Hexagon otherChamber) {
		return this == otherChamber;
	}

	/**
	 * Get the preceding Hexagon object to this one in a shortest path from
	 * initial chamber to exit
	 * 
	 * @return preceding Hexagon object in the shortest path from initial
	 *         chamber to destination
	 */
	public Hexagon getPredecessor() {
		return predecessor;
	}

	/**
	 * Set the preceding Hexagon object to this one in a shortest path from the
	 * initial chamber to the exit
	 * 
	 * @param pred:
	 *            preceding Hexagon object to this one in a shortest path from
	 *            the initial chamer to the exit
	 */
	public void setPredecessor(Hexagon pred) {
		predecessor = pred;
	}
}