package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftHitBox = gp.player.x + entity.hitBox.x;
		int entityRightHitBox = gp.player.x + entity.hitBox.x + entity.hitBox.width;
		int entityTopHitBox = gp.player.y + entity.hitBox.y;
		int entityBottomHitBox = gp.player.y + entity.hitBox.y + entity.hitBox.height;
		
		int entityLeftCol = entityLeftHitBox/gp.tileSize;
		int entityRightCol = entityRightHitBox/gp.tileSize;
		int entityTopRow = entityTopHitBox/gp.tileSize;
		int entityBottomRow = entityBottomHitBox/gp.tileSize;
		
		int tileNum1, tileNum2, tileNum3;
		
		//for the diagonal directions I need to figure out corners
		
		switch(entity.direction) {
		case "upL":
			entityTopRow = (entityTopHitBox - entity.diagSpeed)/gp.tileSize;
			entityLeftCol = (entityLeftHitBox - entity.diagSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			//need tiles 4 and 5 to make a "minecraft helmet" just think about it, it makes sense
			if(gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}else if((gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == false) || (gp.tileM.tile[tileNum1].collision == false && 
			   gp.tileM.tile[tileNum2].collision == true && gp.tileM.tile[tileNum3].collision == false)) {
				entity.collisionOn = true;
				entity.x -= entity.diagSpeed;
			}else if((gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == false && 
			   gp.tileM.tile[tileNum3].collision == true) || (gp.tileM.tile[tileNum1].collision == false &&
			   gp.tileM.tile[tileNum2].collision == false && gp.tileM.tile[tileNum3].collision == true)) {
				entity.collisionOn = true;
				entity.y -= entity.diagSpeed;
			}
			break;
		case "upR":
			entityTopRow = (entityTopHitBox - entity.diagSpeed)/gp.tileSize;
			entityRightCol = (entityRightHitBox + entity.diagSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}else if((gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == false) || (gp.tileM.tile[tileNum1].collision == true && 
			   gp.tileM.tile[tileNum2].collision == false && gp.tileM.tile[tileNum3].collision == false)) {
				entity.collisionOn = true;
				entity.x += entity.diagSpeed;
			}else if((gp.tileM.tile[tileNum1].collision == false && gp.tileM.tile[tileNum2].collision == true &&
			   gp.tileM.tile[tileNum3].collision == true) || (gp.tileM.tile[tileNum1].collision == false && 
			   gp.tileM.tile[tileNum2].collision == false && gp.tileM.tile[tileNum3].collision == true)) {
				entity.collisionOn = true;
				entity.y -= entity.diagSpeed;
			}
			break;
		case "up":
			entityTopRow = (entityTopHitBox - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "downL":
			entityBottomRow = (entityBottomHitBox + entity.diagSpeed)/gp.tileSize;
			entityLeftCol = (entityLeftHitBox - entity.diagSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}else if((gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == false) || (gp.tileM.tile[tileNum1].collision == false && 
			   gp.tileM.tile[tileNum2].collision == true && gp.tileM.tile[tileNum3].collision == false)) {
				entity.collisionOn = true;
				entity.x -= entity.diagSpeed;
			}else if((gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == false && 
			   gp.tileM.tile[tileNum3].collision == true) || (gp.tileM.tile[tileNum1].collision == false && 
			   gp.tileM.tile[tileNum2].collision == false && gp.tileM.tile[tileNum3].collision == true)){
				entity.collisionOn = true;
				entity.y += entity.diagSpeed;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftHitBox - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "downR":
			entityBottomRow = (entityBottomHitBox + entity.diagSpeed)/gp.tileSize;
			entityRightCol = (entityRightHitBox + entity.diagSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}else if((gp.tileM.tile[tileNum1].collision == true && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == false) || (gp.tileM.tile[tileNum1].collision == true && 
			   gp.tileM.tile[tileNum2].collision == false && gp.tileM.tile[tileNum3].collision == false)) {
				entity.collisionOn = true;
				entity.x += entity.diagSpeed;
			}else if((gp.tileM.tile[tileNum1].collision == false && gp.tileM.tile[tileNum2].collision == true && 
			   gp.tileM.tile[tileNum3].collision == true) || (gp.tileM.tile[tileNum1].collision == false && 
			   gp.tileM.tile[tileNum2].collision == false && gp.tileM.tile[tileNum3].collision == true)){
				entity.collisionOn = true;
				entity.y += entity.diagSpeed;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomHitBox + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightHitBox + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
}
