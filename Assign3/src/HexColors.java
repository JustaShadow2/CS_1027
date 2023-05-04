import java.awt.Color;

public class HexColors {
	public static final Color WALL = Color.BLACK;
	public static final Color START = new Color(250, 250, 0);
	public static final Color END = Color.BLUE;
	public static final Color UNVISITED = new Color(100, 255, 100);
	public static final Color ENQUEUED = Color.CYAN;
	public static final Color END_PROCESSED = new Color(0, 190, 190);
	public static final Color END_DEQUEUED = new Color(55, 255, 255);
	public static final Color START_PROCESSED = new Color(200, 255, 255);
	public static final Color START_DEQUEUED = new Color(50, 255, 255);
	public static final Color DEQUEUED = Color.LIGHT_GRAY;
	public static final Color DRAGON = Color.YELLOW;
	public static final Color LAVA = Color.RED;
	public static final Color CACTUS = Color.PINK;
	public static final Color LAVA_ENQUEUED = Color.RED.darker();
	public static final Color CACTUS_ENQUEUED = Color.PINK.darker();
	public static final Color LAVA_DEQUEUED = Color.RED.brighter();
	public static final Color CACTUS_DEQUEUED = Color.PINK.brighter();
	public static final Color LAVA_PATH = new Color(255,100,100);
	public static final Color CACTUS_PATH = new Color(255,100,99);
	public static final Color START_PATH = new Color(255,100,98);
	public static final Color END_PATH = new Color(255,100,97);
	public static final Color PATH = new Color(255,100,96);
	
	public Color gradientColor(Color c) {
		if (c == WALL)
			return Color.DARK_GRAY.brighter();
		else if (c == ENQUEUED)
			return c.darker().darker();
		else if (c == END_PROCESSED)
			return new Color(0, 0, 180);
		else if (c == START_PROCESSED)
			return Color.CYAN.darker();
		else if (c == START_DEQUEUED)
			return Color.GRAY;
		else if (c == END_DEQUEUED)
			return Color.GRAY;
		return c.darker();
	}

	public Color initialGradient(Color c) {
		if (c == END_PROCESSED)
			return new Color(150, 250, 250);
		else if (c == START_PROCESSED)
			return new Color(250, 250, 80);
		else if (c == START_DEQUEUED)
			return new Color(250, 250, 80);
		else if (c == END_DEQUEUED)
			return new Color(250, 250, 80);
		else if (c == END)
			return new Color(110, 110, 250);
		else
			return c.brighter();
	}
}
