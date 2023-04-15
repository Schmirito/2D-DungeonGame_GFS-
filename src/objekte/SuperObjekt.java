package objekte;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class SuperObjekt {
	
	GamePanel gp;
	public BufferedImage bild;
	public String name;
	public boolean kollision = true;
	public int weltX, weltY;
	public Rectangle hitBox;
	
	public SuperObjekt(GamePanel gp, int weltX, int weltY) {
		this.gp = gp;
		hitBox = new Rectangle(0,0,gp.feldGroe�e,gp.feldGroe�e);
		this.weltX = weltX * gp.feldGroe�e;
		this.weltY = weltY * gp.feldGroe�e;
		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/objekte/Obj001Test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		if(bild != null) {
			
			int bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
			int bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
			
			if (weltX + gp.feldGroe�e > gp.kamera.weltX - gp.kamera.bildschirmX && 
				weltX - gp.feldGroe�e < gp.kamera.weltX + gp.kamera.bildschirmX && 
				weltY + gp.feldGroe�e > gp.kamera.weltY - gp.kamera.bildschirmY && 
				weltY - gp.feldGroe�e < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(bild, bildschirmX, bildschirmY, gp.feldGroe�e, gp.feldGroe�e, null);
			}
		}
	}
}