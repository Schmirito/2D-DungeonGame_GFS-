package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;

public class UI {

	GamePanel gp;
	Entity en;
	
	Font schriftgroesse;
	
	UtilityTool uTool = new UtilityTool();
	BufferedImage eisenmuenze, raum;
	
	/**Constructor des User-Interface 
	 * @param gp*/
	public UI(GamePanel gp) {
		this.gp = gp;
		
		schriftgroesse = new Font("Arial", Font.PLAIN, 40);
		try {
			raum = ImageIO.read(getClass().getResourceAsStream("/ui/tuerFuerRaum.png"));
			raum = uTool.skalaBild(raum, gp.feldGroeße - gp.skala, gp.feldGroeße - gp.skala);
			
			eisenmuenze = ImageIO.read(getClass().getResourceAsStream("/ui/eisenmuenzeLennard.png"));
			eisenmuenze = uTool.skalaBild(eisenmuenze, gp.feldGroeße - gp.skala, gp.feldGroeße - gp.skala);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**Zeichen-Methode in welcher das User-Interface gezeichnet wird.
	 * @param g2 */
	public void draw(Graphics2D g2) {
		
		g2.drawImage(raum, null, gp.skala*2, gp.skala*2);
		g2.drawImage(eisenmuenze, null, gp.skala*2, gp.feldGroeße);
		
		g2.setFont(schriftgroesse);
		g2.setColor(Color.WHITE);
		
		g2.drawString(" X " + gp.feldM.raeumeGesamt, gp.feldGroeße, gp.feldGroeße - gp.skala*4);
		g2.drawString(" X " + Entity.muenzen, gp.feldGroeße, gp.feldGroeße*2 - gp.skala*6);
	}
	
}
