package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	BufferedImage idleUp1, idleUp2, idleLeft1, idleLeft2, idleDown1, idleDown2, idleRight1, idleRight2;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		hitBox = new Rectangle(8, 16, 32, 32);
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		diagSpeed = 3;
		direction = "down";
		idle = true;
	}
	
	public void getPlayerImage() {
		
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkup1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkup2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkup3.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkleft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkleft2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkleft3.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkdown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkdown2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkdown3.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkright1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkright2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/pcwalkright3.png"));
			idleUp1 = ImageIO.read(getClass().getResourceAsStream("/player/pcidleup1.png"));
			idleUp2 = ImageIO.read(getClass().getResourceAsStream("/player/pcidleup2.png"));
			idleLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/pcidleleft1.png"));
			idleLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/pcidleleft2.png"));
			idleDown1 = ImageIO.read(getClass().getResourceAsStream("/player/pcidledown1.png"));
			idleDown2 = ImageIO.read(getClass().getResourceAsStream("/player/pcidledown2.png"));
			idleRight1 = ImageIO.read(getClass().getResourceAsStream("/player/pcidleright1.png"));
			idleRight2 = ImageIO.read(getClass().getResourceAsStream("/player/pcidleright2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true && keyH.leftPressed == true) {
				idle = false;
				direction = "upL";
			}
			else if(keyH.rightPressed == true && keyH.upPressed == true) {
				idle = false;
				direction = "upR";
			}
			else if(keyH.upPressed == true) {
				idle = false;
				direction = "up";
			}
			else if(keyH.leftPressed == true && keyH.downPressed == true) {
				idle = false;
				direction = "downL";
			}
			else if(keyH.leftPressed == true) {
				idle = false;
				direction = "left";
			}
			else if(keyH.downPressed == true && keyH.rightPressed == true) {
				idle = false;
				direction = "downR";
			}
			else if(keyH.downPressed == true) {
				idle = false;
				direction = "down";
			}
			else if(keyH.rightPressed == true) {
				idle = false;
				direction = "right";
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "upL":
					x -= diagSpeed;
					y -= diagSpeed;
					break;
				case "upR":
					x += diagSpeed;
					y -= diagSpeed;
					break;
				case "up":
					y -= speed;
					break;
				case "downL":
					x -= diagSpeed;
					y += diagSpeed;
					break;
				case "left":
					x -= speed;
					break;
				case "downR":
					x += diagSpeed;
					y += diagSpeed;
					break;
				case "down":
					y += speed;
					break;
				case "right":
					x += speed;
					break;
				}
			}
			spriteCounter++;
			if(spriteCounter > 6) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}else {
			idle = true;
			spriteCounter++;
			if(spriteCounter > 6) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		
		switch(direction) {
		case "up":
		case "upL":
		case "upR":
			if(idle != true) {
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				if(spriteNum == 3) {
					image = up1;
				}
				if(spriteNum == 4) {
					image = up3;
				}
			}else {
				if(spriteNum == 1) {
					image = idleUp1;
				}
				if(spriteNum == 2) {
					image = idleUp2;
				}
				if(spriteNum == 3) {
					image = idleUp1;
				}
				if(spriteNum == 4) {
					image = idleUp2;
				}
			}
			break;
		case "left":
			if(idle != true) {
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				if(spriteNum == 3) {
					image = left1;
				}
				if(spriteNum == 4) {
					image = left3;
				}
			}else {
				if(spriteNum == 1) {
					image = idleLeft1;
				}
				if(spriteNum == 2) {
					image = idleLeft2;
				}
				if(spriteNum == 3) {
					image = idleLeft1;
				}
				if(spriteNum == 4) {
					image = idleLeft2;
				}
			}
			break;
		case "down":
		case "downL":
		case "downR":
			if(idle != true) {
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				if(spriteNum == 3) {
					image = down1;
				}
				if(spriteNum == 4) {
					image = down3;
				}
			}else {
				if(spriteNum == 1) {
					image = idleDown1;
				}
				if(spriteNum == 2) {
					image = idleDown2;
				}
				if(spriteNum == 3) {
					image = idleDown1;
				}
				if(spriteNum == 4) {
					image = idleDown2;
				}
			}
			break;
		case "right":
			if(idle != true) {
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				if(spriteNum == 3) {
					image = right1;
				}
				if(spriteNum == 4) {
					image = right3;
				}
			}else {
				if(spriteNum == 1) {
					image = idleRight1;
				}
				if(spriteNum == 2) {
					image = idleRight2;
				}
				if(spriteNum == 3) {
					image = idleRight1;
				}
				if(spriteNum == 4) {
					image = idleRight2;
				}
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
