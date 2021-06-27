package com.dvt.map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import com.dvt.other.Common;

public class Items {

	private int x, y, type;
	Image img;

	public void drawItem(Graphics2D g) {
		g.drawImage(img, x * Common.ITEM_SIZE, y * Common.ITEM_SIZE,
				Common.ITEM_SIZE, Common.ITEM_SIZE, null);
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Items(int x, int y, int type, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.img = img;
	}

	public Items() {
		super();
	}

	public Rectangle getRectangle() {
		return new Rectangle(x * Common.ITEM_SIZE, y * Common.ITEM_SIZE,Common.ITEM_SIZE, Common.ITEM_SIZE);
	}

	public boolean allowTankPass() {
		if (type == 0 || type == 3 || type == 5)
			return true;
		return false;
	}

	public boolean allowBulletPass() {
		if (type == 0 || type >= 3)
			return true;
		return false;
	}
}
