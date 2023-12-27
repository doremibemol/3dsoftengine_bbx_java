package window;
import java.util.Random;
import image.Bitmap;
import math.Vertex;

public class RenderContext extends Bitmap{

	private final int m_scanBuffer[];
	
	public RenderContext(int width, int height) {
		super(width, height);
		m_scanBuffer = new int[height*2];
	}

	public void DrawScanBuffer(int yCoord, int xMin, int xMax)
	{
		m_scanBuffer[yCoord * 2] = xMin;
		m_scanBuffer[yCoord * 2 + 1] = xMax;
		System.out.print(m_scanBuffer[yCoord * 2] + " - " + m_scanBuffer[yCoord * 2 + 1] + " - ");
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void FillShape(int yMin, int yMax)
	{

		for(int j = yMin; j < yMax ;j++)
		{
			int xMin = m_scanBuffer[ j * 2    ];
			int xMax = m_scanBuffer[ j * 2 + 1];
			for(int i=xMin; i < xMax; i++)
			{
				DrawPixel(i, j, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff);
			}
		}
	}
	
	public void FillShape(int yMin, int yMax, int r, int g, int b)
	{

		for(int j = yMin; j < yMax ;j++)
		{
			int xMin = m_scanBuffer[ j * 2    ];
			int xMax = m_scanBuffer[ j * 2 + 1];
			for(int i=xMin; i < xMax; i++)
			{
				DrawPixel(i, j, (byte)0xff, (byte)r, (byte)g, (byte)b);
			}
		}
	}
	
	public void FillTriangle(Vertex v1, Vertex v2, Vertex v3)
	{
		Vertex minYVert = v1;
		Vertex midYVert = v2;
		Vertex maxYVert = v3;
		
		if(maxYVert.GetY() < midYVert.GetY())
		{
			Vertex tmp = maxYVert;
			maxYVert = midYVert;
			midYVert = tmp;
		}
		if(midYVert.GetY() < minYVert.GetY())
		{
			Vertex tmp = midYVert;
			midYVert = minYVert;
			minYVert = tmp;
		}
		if(maxYVert.GetY() < midYVert.GetY())
		{
			Vertex tmp = maxYVert;
			maxYVert = midYVert;
			midYVert = tmp;
		}
		
		float area = minYVert.TriangleArea(maxYVert, midYVert);
		int handedness = (area >= 0 ? 1 : 0);
		
		ScanConvertTriangle(minYVert, midYVert, maxYVert, handedness);
		FillShape((int)minYVert.GetY(), (int)maxYVert.GetY());
	}
	
	public void ScanConvertTriangle(Vertex minYVert,Vertex midYVert, Vertex maxYVert, int whichSide)
	{
		ScanConvertLine(minYVert, maxYVert, 0 + whichSide);
		ScanConvertLine(minYVert, midYVert, 1 - whichSide);
		ScanConvertLine(midYVert, maxYVert, 1 - whichSide);
	}
	
	private void ScanConvertLine(Vertex minYVert, Vertex maxYVert, int whichSide)
	{
		int yStart = (int)minYVert.GetY();
		int yEnd = (int)maxYVert.GetY();
		int xStart = (int)minYVert.GetX();
		int xEnd = (int)maxYVert.GetX();
		
		int yDist = yEnd - yStart;
		int xDist = xEnd - xStart;
		
		if(yDist <= 0)
		{
			return;
		}
		
		float xStep = (float)xDist/(float)yDist;
		float curX = (float)xStart;
		
		for(int j = yStart; j < yEnd; j++)
		{
			m_scanBuffer[j*2+whichSide]=(int)curX;
			curX+=xStep;
		}
	}

	public void FillTriangle(Vertex v1, Vertex v2, Vertex v3, int r, int g, int b) {

			Vertex minYVert = v1;
			Vertex midYVert = v2;
			Vertex maxYVert = v3;
			
			if(maxYVert.GetY() < midYVert.GetY())
			{
				Vertex tmp = maxYVert;
				maxYVert = midYVert;
				midYVert = tmp;
			}
			if(midYVert.GetY() < minYVert.GetY())
			{
				Vertex tmp = midYVert;
				midYVert = minYVert;
				minYVert = tmp;
			}
			if(maxYVert.GetY() < midYVert.GetY())
			{
				Vertex tmp = maxYVert;
				maxYVert = midYVert;
				midYVert = tmp;
			}
			
			float area = minYVert.TriangleArea(maxYVert, midYVert);
			int handedness = (area >= 0 ? 1 : 0);
			
			ScanConvertTriangle(minYVert, midYVert, maxYVert, handedness);
			FillShape((int)minYVert.GetY(), (int)maxYVert.GetY(),r,g,b);
		}
}
