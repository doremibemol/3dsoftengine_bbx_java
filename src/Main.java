/**
 * 
 */

import effect.Stars3D;
import image.Bitmap;
import math.Vertex;
import window.Display;
import window.RenderContext;

/**
 * @author Ash
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display(800, 600, "Software Rendering");
		RenderContext target = display.GetFrameBuffer();
		Stars3D stars = new Stars3D(3, 64.0f, 4.0f);
		Vertex p1 = new Vertex(100, 500);
		Vertex p2 = new Vertex(170, 200);
		Vertex p3 = new Vertex(80, 400);
		long previousTime = System.nanoTime();
		while(true)
		{
			long currentTime = System.nanoTime();
			float delta = (float)((currentTime - previousTime)/1000000000.0);
			previousTime = currentTime;

			
			target.Clear((byte)0x00);
			stars.UpdateAndRender(target, delta);
			/*for(int j = 100; j < 200; j++)
			{
				target.DrawScanBuffer(j, 300 - j, 300 + j); 
			}*/
			
			//target.FillTriangle(p1, p2, p3,(int)(Math.sin(System.nanoTime()/1000000000000000.0f)),(int)(Math.cos(System.nanoTime()/1000000000000000.0f)),
			//(int)(Math.sin(System.nanoTime()/1000000000000000.0f)));

			display.SwapBuffers();
		}
	}

}
