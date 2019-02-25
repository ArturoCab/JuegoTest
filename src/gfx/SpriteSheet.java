package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private int width;
	private int height;
	public final int[] pixeles;
	
	public SpriteSheet(final String route, final int width, final int height) {
		this.width=width;
		this.height=height;
		
		pixeles=new int[width*height];
		
		try {
			BufferedImage image=ImageIO.read(SpriteSheet.class.getResource(route));
			image.getRGB(0, 0, width, height, pixeles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
