package effect;

import java.util.Random;

import math.Vertex;
import window.RenderContext;

public class Stars3D {

	private final float m_spread;
	private final float m_speed;
	
	private final float tanHalFOV = (float)Math.tan(Math.toDegrees(90/2.0f));
	
	private final float m_starX[];
	private final float m_starY[];
	private final float m_starZ[];
	
	public Stars3D(int numStars, float spread, float speed)
	{
		m_spread = spread;
		m_speed = speed;
		
		m_starX = new float[numStars];
		m_starY = new float[numStars];
		m_starZ = new float[numStars];
		
		for(int i = 0; i < numStars; i++)
			InitStars(i);
	}
	
	private void InitStars(int index)
	{
		m_starX[index]  = 2 * ((float)Math.random() - 0.5f) * m_spread;
		m_starY[index]  = 2 * ((float)Math.random() - 0.5f) * m_spread;;
		m_starZ[index]  = ((float)Math.random() + 0.00001f) * m_spread;;
	}
	
	public void UpdateAndRender(RenderContext target, float delta)
	{
		target.Clear((byte)0x00);
		
		float centerW = target.GetWidth()/2.0f;
		float centerH = target.GetHeight()/2.0f;
		
		int triangleBuilderCounter = 0;
		
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		
		for(int i=0; i < m_starX.length; i++)
		{
			m_starZ[i] -= delta * m_speed;
			
			if(m_starZ[i] <= 0)
			{
				InitStars(i);
			}
			
			int x = (int)((m_starX[i]/(m_starZ[i] * tanHalFOV)) * centerW + centerW);
			int y = (int)((m_starY[i]/(m_starZ[i] * tanHalFOV)) * centerH + centerH);
			Random random = new Random();

			int r = random.nextInt(255 + 10) + 10;
			int g = random.nextInt(255 + 10) + 10;
			int b = random.nextInt(255 + 10) + 10;
			if(x < 0 || x >= target.GetWidth()||
				y < 0 || y >= target.GetHeight())
			{
				InitStars(i);
				continue;
			}
			//else
			//{
				//int d= (int) ((m_starZ[i]+1000.f)*4);
				//target.DrawPixel(x, y, (byte)(255*d), (byte)255, (byte)255, (byte)255);
			//}
			
			triangleBuilderCounter++;
			if(triangleBuilderCounter == 1)
			{
				x1 = x;
				y1 = y;
			}
			else if(triangleBuilderCounter == 2)
			{
				x2 = x;
				y2 = y;
			}
			else if(triangleBuilderCounter == 3)
			{
				triangleBuilderCounter = 0;
				Vertex v1 = new Vertex(x1, y1);
				Vertex v2 = new Vertex(x2, y2);
				Vertex v3 = new Vertex(x, y);

				target.FillTriangle(v1, v2, v3,r,g,b);
			}
		}
	}
}
