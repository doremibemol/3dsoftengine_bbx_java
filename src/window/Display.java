package window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import image.Bitmap;

public class Display extends Canvas {
	
	
	private final JFrame m_frame;
	ImageIcon imgIcon;
	private final RenderContext m_frameBuffer;
	private final BufferedImage m_displayImage;
	private final byte[] m_displayComponents;
	private final BufferStrategy m_bufferStrategy;
	private final Graphics m_graphics;
	private final int m_width;
	private final int m_height;
	public String m_title;
	
	public Display (int width, int height, String title)
	{
		m_width = width;
		m_height = height;
		m_title = title;
		System.out.println("W: " + m_width + " H: " + m_height);
		Dimension size = new Dimension(m_width, m_height);
		imgIcon = new ImageIcon("icone.png"); 
		
		m_frameBuffer = new RenderContext(m_width, m_height);
		m_displayImage = new BufferedImage(m_width, m_height, BufferedImage.TYPE_3BYTE_BGR);
		m_displayComponents = ((DataBufferByte)m_displayImage.getRaster().getDataBuffer()).getData();
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		
		JMenuItem menuItem = new JMenuItem("Quitter");
		
		menuFile.add(menuItem);
		menuBar.add(menuFile);
		
		m_frame = new JFrame();
		m_frame.add(this);
		m_frame.pack();
		m_frame.setResizable(false);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setLocationRelativeTo(null);
		m_frame.setIconImage(imgIcon.getImage());
		m_frame.setTitle(title);
		m_frame.setVisible(true);
		m_frame.setJMenuBar(menuBar);
		createBufferStrategy(1);
		m_bufferStrategy = getBufferStrategy();
		m_graphics = m_bufferStrategy.getDrawGraphics();
		
	}
	
	public void SetTitle(String t)
	{
		m_frame.setTitle(t);
	}
	
	public void SwapBuffers()
	{
		m_frameBuffer.CopyToByteArray(m_displayComponents);
		m_graphics.drawImage(m_displayImage, 0, 0, m_frameBuffer.GetWidth(), m_frameBuffer.GetHeight(), null);
		m_bufferStrategy.show();
	}
	
	public void Clear(byte color)
	{
		m_frameBuffer.Clear(color);
	}
	
	public void ClearRGB(int r, int g, int b)
	{
		m_frameBuffer.ClearRGB((byte)r,(byte)g,(byte)b);
	}
	
	public void DrawPixel(int x, int y, int r, int g, int b)
	{
		m_frameBuffer.DrawPixel(x, y, (byte)0x00, (byte)r, (byte)g, (byte)b);
	}
	
	public void DrawBitmap(byte[] src)
	{
		
		m_frameBuffer.CopyToByteArray(src);
	}
	
	public void DrawSquare(int x, int y, int w, int h, int r, int g, int b)
	{
		for(int i = 0; i < w; i++)
		{
			for(int j = 0;j <h; j++) {
					if(i+x > m_width-1||j+y > m_height-1||i+x<0||j+y<0)
						continue;
					else
					m_frameBuffer.DrawPixel(i+x, j+y, (byte)0xff, (byte)r, (byte)g, (byte)b);
			}

		}
	}
	
	public int GetWidth() { return m_width; }
	public int GetHeight() { return m_height; }
	public RenderContext GetFrameBuffer() { return m_frameBuffer; }
	
	
}
