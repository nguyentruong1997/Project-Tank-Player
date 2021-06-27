package com.dvt.other;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageMgr {
	
	public static ArrayList<Image> arrItemsImages;
	public static ArrayList<Image> arrPlayerImages;
	public static ArrayList<Image> arrBoss1Images;
	public static ArrayList<Image> arrBoss21Images;
	public static ArrayList<Image> arrBoss22Images;
	public static ArrayList<Image> arrBoss31Images;
	public static ArrayList<Image> arrBoss32Images;
	public static ArrayList<Image> arrBoss33Images;
	public static ArrayList<Image> arrBulletImages;
	public static ArrayList<Image> arrGiftImages;
	public static ArrayList<Image> arrBumImages;
	public static ArrayList<Image> arrNumImages;
	public static ArrayList<Image> arrBirdImages;
	public static Image imageStart;
	public static Image imageIconBoss;
	public static Image imageLeft;
	public static Image imageRight;
	
	public ImageMgr() {
		arrItemsImages = getImage("unit", Common.ITEM_SIZE, Common.ITEM_SIZE, 6);
		arrPlayerImages = getImage("player", Common.TANK_SIZE,Common.TANK_SIZE, 4);
		arrBoss1Images = getImage("boss1", Common.TANK_SIZE, Common.TANK_SIZE,4);
		arrBoss21Images = getImage("boss2", Common.TANK_SIZE, Common.TANK_SIZE,4);
		arrBoss22Images = getImage("boss22", Common.TANK_SIZE, Common.TANK_SIZE,4);
		arrBoss31Images = getImage("boss31", Common.TANK_SIZE,Common.TANK_SIZE, 4);
		arrBoss32Images = getImage("boss32", Common.TANK_SIZE,Common.TANK_SIZE, 4);
		arrBoss33Images = getImage("boss33", Common.TANK_SIZE,Common.TANK_SIZE, 4);
		arrBulletImages = getImage("bullet", Common.BULLET_SIZE,Common.BULLET_SIZE, 4);
		arrBumImages = getImage("bum", Common.TANK_SIZE * 2,Common.TANK_SIZE * 2, 9);
		arrGiftImages=getImage("gift",Common.TANK_SIZE, Common.TANK_SIZE, 2);
		arrNumImages = getImage("num_level", 16, 16, 10);
		arrBirdImages = getImage("bird", Common.TANK_SIZE, Common.TANK_SIZE, 2);
		imageStart = new ImageIcon(getClass().getResource("/IMAGES/game_start.png")).getImage();
		imageIconBoss = new ImageIcon(getClass().getResource("/IMAGES/icon_boss.png")).getImage();
		imageLeft = new ImageIcon(getClass().getResource("/IMAGES/left.png")).getImage();
		imageRight = new ImageIcon(getClass().getResource("/IMAGES/right.png")).getImage();
	}
	
	private ArrayList<Image> getImage(String imgName, int width, int height,int number) {
		ArrayList<Image> arrImg = new ArrayList<Image>();
		try {
			BufferedImage buffReadImage = ImageIO.read(getClass().getResourceAsStream("/IMAGES/" + imgName + ".png"));
			BufferedImage buffCutImage;
			for (int i = 0; i < number; i++) {
				buffCutImage = buffReadImage.getSubimage(0, i * height, width, height); //
				arrImg.add(buffCutImage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrImg;
	}

}
