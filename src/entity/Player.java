package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	/** Deklaration der Variablen */
	GamePanel gp;
	KeyHandler keyH;
	public int bildX;
	public int bildY;
	public int framesUnbewegt;

	/** Constructor mit Uebergabeparametern GamePanel und KeyHandler */
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		bildX = gp.BildBreite / 2 - (gp.feldGroeße / 2);
		bildY = gp.BildHoehe / 2 - (gp.feldGroeße / 2);
		framesUnbewegt = 0;

		setDefaultValuables();
		getPlayerImage();
	}

	public void setDefaultValuables() {
		weltX = bildX; //13 * gp.feldGroeße;
		weltY = bildY; //13 * gp.feldGroeße;
		geschwindigkeit = gp.skala;
		richtung = "unten";
	}
	
	/** Die Charactersprites werden aus dem res Ordner in deren variablen geladen */
	public void getPlayerImage() {
		
			up = setup("char-Up");
			upLV = setup("char-UpLV");
			upRV = setup("char-UpRV");
			down = setup("char-Down");
			downLV = setup("char-DownLV");
			downRV = setup("char-DownRV");
			left = setup("char-Left");
			leftLV = setup("char-LeftLV");
			leftRV = setup("char-LeftRV");
			right = setup("char-Right");
			rightLV = setup("char-RightLV");
			rightRV = setup("char-RightRV");

	}
	/**Der Player wird jetzt in der methode setup skaliert, was die performance verbessern kann.*/
	public BufferedImage setup(String bildName) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage bild = null;
		
		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/player/"+bildName+".png"));
			bild = uTool.skalaBild(bild, gp.feldGroeße, gp.feldGroeße);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bild;
	}
	
	/** Wenn tasten in die entsprechende Richtung gedrückt wurden, wird die Spielerposition neu berechnet.
	 * Außerdem wird einer Anzahl an frames die Variable für die Animation erhöht bzw zurückgesetzt. 
	 * Wenn der Character eine gewisse Anzahl frames steht, so wird die Richtung auf "steht" gesetzt. */
	public void update() {
		if (keyH.obenGedrückt || keyH.untenGedrückt || keyH.linksGedrückt || keyH.rechtsGedrückt) {
			if (keyH.obenGedrückt == true) {
				richtung = "oben";
				weltY -= geschwindigkeit;
			} else if (keyH.untenGedrückt) {
				richtung = "unten";
				weltY += geschwindigkeit;
			} else if (keyH.linksGedrückt) {
				richtung = "links";
				weltX -= geschwindigkeit;
			} else if (keyH.rechtsGedrückt) {
				richtung = "rechts";
				weltX += geschwindigkeit;
			}
			bildX = weltX;
			bildY = weltY;
			framesUnbewegt = 0;
			frameCounter++;
			if (frameCounter > 8) {
				spriteNumber++;
				if (spriteNumber >= 4) {
					spriteNumber = 0;
				}
				frameCounter = 0;
			}
		}
		framesUnbewegt++;
		if (framesUnbewegt >= 16) {
			spriteNumber = 0;
		}
		
	}
	 
	/** Mithilfe von verschachtelten switch-case Verzweigungen wird das zu Ladende Sprite ausgewählt und
	 * Schlussendlich an entsprechender Position angezeigt. */
	public void draw(Graphics2D g2) {

		BufferedImage charSprite = null;

		switch (richtung) {
		case "oben":
			switch (spriteNumber) {
			case 0:
				charSprite = up;
				break;
			case 1:
				charSprite = upLV;
				break;
			case 2:
				charSprite = up;
				break;
			case 3:
				charSprite = upRV;
				break;
			}
			break;
		case "unten":
			switch (spriteNumber) {
			case 0:
				charSprite = down;
				break;
			case 1:
				charSprite = downLV;
				break;
			case 2:
				charSprite = down;
				break;
			case 3:
				charSprite = downRV;
				break;
			}
			break;
		case "links":
			switch (spriteNumber) {
			case 0:
				charSprite = left;
				break;
			case 1:
				charSprite = leftLV;
				break;
			case 2:
				charSprite = left;
				break;
			case 3:
				charSprite = leftRV;
				break;
			}
			break;
		case "rechts":
			switch (spriteNumber) {
			case 0:
				charSprite = right;
				break;
			case 1:
				charSprite = rightLV;
				break;
			case 2:
				charSprite = right;
				break;
			case 3:
				charSprite = rightRV;
				break;
			}
			break;
		}
		g2.drawImage(charSprite, bildX, bildY, gp.feldGroeße, gp.feldGroeße, null);
	}

}
