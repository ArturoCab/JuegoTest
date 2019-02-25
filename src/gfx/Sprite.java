package gfx;

public final class Sprite {
	private final int size;
	
	private int x, y;
	
	public int [] pixeles;
	private final SpriteSheet sheet;
	
	public Sprite(final int size, final int column, final int row, final SpriteSheet sheet){
		this.size=size;
		this.sheet=sheet;
		
		pixeles=new int[size*size];
		
		this.x=column*size;
		this.y=row*size;
		
		for(int y=0; y<size; y++) {
			for(int x=0; x<size; x++) {
				pixeles[(x+y)*size]=sheet.pixeles[(x+this.x)+(y+this.y)*sheet.getWidth()];
			}
		}
	}
}
