package render_test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HardwareAcceleration extends JFrame {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(HardwareAcceleration::new);
	}
	
	/*
	The BufferedImage graphics will be rendered too. The size is large
	to demonstrate the sluggish drawing I experience curiously only when
	not using the line "createBufferStrategy(2)".
	 */
	private BufferedImage drawSurface = new BufferedImage(4000, 4000, BufferedImage.TYPE_INT_RGB);
	private Graphics2D drawGraphics = drawSurface.createGraphics();
	
	private Point mouse = new Point(0, 0);
	private PaintSurface canvas = new PaintSurface();
	
	private HardwareAcceleration() {
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas.addMouseListener(new MouseListener());
		canvas.addMouseMotionListener(new MouseListener());
		
		add(canvas);
		setVisible(true);
 
        /*
        COMMENT THIS LINE OUT TO EXPERIENCE POOR
        DRAWING PERFORMANCE.
         */
		createBufferStrategy(2);
		System.setProperty("sun.java2d.opengl","True");
	}
	
	private class MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			mouse = e.getPoint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
 
            /*
            Here's where the problem occurs. Without the line
            "createBufferStrategy(2)" drawing on an image of such a large
            size is very slow. However with that line drawing is smooth and fast.
 
            The problem is (1) I haven't the slightest clue why the line "createBufferStrategy(2)"
            would solve this performance problem, maybe it enables some sort of hardware acceleration?
            But i'm used to using "createBufferStrategy()" when active rendering using "getBufferStrategy().getDrawGraphics()"
            so without drawing to the strategies draw graphics I'm not sure why this would effect anything.
 
            (2) The line createBufferStrategy(2) seems to cause some other performance issues with
            scaling and transparency animations where without it these animations are smooth.
 
            My final goal is to figure out why drawing to this large image is slow without
            the line "createBufferStrategy(2)" and how I might be able to speed it up without
            it as it causes other problems.
             */
			
			drawGraphics.drawLine(mouse.x, mouse.y, e.getX(), e.getY());
			mouse = e.getPoint();
			
			canvas.repaint();
		}
	}
	
	class PaintSurface extends JPanel {
		
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(drawSurface, 0, 0, this);
		}
	}
}