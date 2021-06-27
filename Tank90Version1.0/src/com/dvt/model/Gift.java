package com.dvt.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.dvt.other.Common;
import com.dvt.other.ImageMgr;

public class Gift {

	private int x;
	private int y;
	private int indexGift;
	ArrayList<Image> arrImages;

	public Gift(int x, int y, int indexGift, ArrayList<Image> arrImages) {
		super();
		this.x = x;
		this.y = y;
		this.indexGift = indexGift;
		this.arrImages = arrImages;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getIndexGift() {
		return indexGift;
	}

	public void setIndexGift(int indexGift) {
		this.indexGift = indexGift;
	}

	public void drawGift(Graphics2D g) {
		g.drawImage(ImageMgr.arrGiftImages.get(indexGift), x, y, null);
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, Common.TANK_SIZE, Common.TANK_SIZE);
	}
}
