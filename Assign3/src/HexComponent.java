
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * A six sided chamber. This is not guaranteed to be a perfect hexagon, it is
 * just guaranteed to have six sides in the form of a hexagon. To be a perfect
 * hexagon the size of this component must have a height to width ratio of 1 to
 * 0.866
 *
 * @author keang, cs1027
 *
 *
 */

public class HexComponent extends JComponent {
	private static final long serialVersionUID = 4865976127980106774L;

	private Polygon hexagon = new Polygon();

	private final int nPoints = 6;
	private int[] hexX = new int[nPoints];
	private int[] hexY = new int[nPoints];

	private Color defaultColor;

	@Override
	public boolean contains(Point p) {
		return hexagon.contains(p);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.contains(x, y);
	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		calculateCoords();
	}

	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);
		calculateCoords();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		calculateCoords();
	}

	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		calculateCoords();
	}

	@Override
	protected void processMouseEvent(MouseEvent e) {
		if (contains(e.getPoint()))
			super.processMouseEvent(e);
	}

	private void calculateCoords() {
		int w = getWidth() - 1;
		int h = getHeight() - 1;

		int ratio = (int) (h * .25);

		agressiveCoords(w, h, ratio);

		hexagon = new Polygon(hexX, hexY, nPoints);
	}

	private void agressiveCoords(int w, int h, int ratio) {
		hexX[0] = w / 2;
		hexY[0] = 0;

		hexX[1] = w;
		hexY[1] = ratio;

		hexX[2] = w;
		hexY[2] = h - ratio;

		hexX[3] = w / 2;
		hexY[3] = h;

		hexX[4] = 0;
		hexY[4] = h - ratio;

		hexX[5] = 0;
		hexY[5] = ratio;
	}

	@Override
	protected void paintComponent(Graphics g) {
		HexColors palette = new HexColors();
		Color c = getBackground();
		Graphics2D g2d = (Graphics2D) g;
		defaultColor = (Color) g2d.getPaint();
		boolean displayed = false;
		GradientPaint gp;
		int width = getWidth();
		int height = getHeight();

		if (c == HexColors.WALL) {
			try {
				BufferedImage texture;
				texture = ImageIO.read(new File("wall.jpg"));
				TexturePaint tp = new TexturePaint(texture, new Rectangle(0, 0, 50, 50));
				g2d.setPaint(tp);
				g2d.fillPolygon(hexagon);
				displayed = true;
			} catch (IOException e) {
				System.out.println("Error opening file wall.jpg");
			}
		} else if (c == HexColors.DRAGON) {
			g2d.setColor(new Color(200, 150, 50));
			g2d.fillPolygon(hexagon);
			Image img = new ImageIcon("dragon.jpg").getImage();
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;
		} else if (c == HexColors.END_PROCESSED || c == HexColors.START_PROCESSED || c == HexColors.START_PATH ||
				   c == HexColors.START_DEQUEUED || c == HexColors.END_DEQUEUED || c == HexColors.END_PATH) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			
			if (c == HexColors.END_PROCESSED || c == HexColors.END_DEQUEUED) {
				img = new ImageIcon("exit.jpg").getImage();
				g2d.drawImage(img, width / 4, height / 3, width / 2, height / 5, null);
			} else if (c == HexColors.END_PATH) {
				img = new ImageIcon("exitPath.jpg").getImage();
				g2d.drawImage(img, width / 4, height / 3, width / 2, height / 5, null);
			} else if (c == HexColors.START_PROCESSED || c == HexColors.START_DEQUEUED ) {
				img = new ImageIcon("entrance.jpg").getImage();
				g2d.drawImage(img, 3 * width / 8, height / 3, width / 4, height / 3, null);
			} else {
				img = new ImageIcon("entrancePath.jpg").getImage();
				g2d.drawImage(img, 3 * width / 8, height / 3, width / 4, height / 3, null);			
			}
			displayed = true;
		} else if (c == HexColors.END) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			img = new ImageIcon("exit.jpg").getImage();
			g2d.drawImage(img, width / 4, height / 3, width / 2, height / 5, null);

			displayed = true;
		} else if (c == HexColors.LAVA || c == HexColors.LAVA_ENQUEUED || 
				   c == HexColors.LAVA_DEQUEUED || c == HexColors.LAVA_PATH) {
			try {
				BufferedImage texture;
				if (c == palette.LAVA)
					texture = ImageIO.read(new File("lava.jpg"));
				else if (c == palette.LAVA_ENQUEUED)
					texture = ImageIO.read(new File("lavaPush.jpg"));
				else if (c == palette.LAVA_DEQUEUED)
					texture = ImageIO.read(new File("lavaPop.jpg"));
				else
					texture = ImageIO.read(new File("lavaPath.jpg"));

				TexturePaint tp = new TexturePaint(texture, new Rectangle(0, 0, 90, 110));
				g2d.setPaint(tp);
				g2d.fillPolygon(hexagon);
				displayed = true;
			} catch (IOException e) {
				System.out.println("Error opening file wall.jpg");
			}
		} else if (c == HexColors.CACTUS || c == HexColors.CACTUS_ENQUEUED || 
				   c == HexColors.CACTUS_DEQUEUED || c == HexColors.CACTUS_PATH) {
			try {
				BufferedImage texture;
				if (c == HexColors.CACTUS)
					texture = ImageIO.read(new File("cactus.jpg"));
				else if (c == HexColors.CACTUS_ENQUEUED)
					texture = ImageIO.read(new File("cactusPush.jpg"));
				else if (c == HexColors.CACTUS_DEQUEUED)
					texture = ImageIO.read(new File("cactusPop.jpg"));
				else
					texture = ImageIO.read(new File("cactusPath.jpg"));

				TexturePaint tp = new TexturePaint(texture, new Rectangle(0, 0, 90, 110));
				g2d.setPaint(tp);
				g2d.fillPolygon(hexagon);
				displayed = true;
			} catch (IOException e) {
				System.out.println("Error opening file wall.jpg");
			}
		}
		if (!displayed) {
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
		}
		g2d.setPaint(defaultColor);

	}

	@Override
	protected void paintBorder(Graphics g) {
		// do not paint a border
	}

}
