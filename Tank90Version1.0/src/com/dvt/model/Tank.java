package com.dvt.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.dvt.other.Common;
import com.dvt.other.ImageMgr;

public class Tank {

	protected ArrayList<Image> arrImages;
	protected int id;
	protected int x;
	protected int y;
	protected int size;
	protected int orient;
	protected float speed;
	private boolean FireBullet = false;

	public boolean getFireBullet() {
		return FireBullet;
	}

	public void setFireBullet(boolean setFireBullet) {
		this.FireBullet = setFireBullet;
	}

	public void drawTank(Graphics2D g) {
		g.drawImage(arrImages.get(orient), x, y, null);
	}

	public ArrayList<Image> getArrImages() {
		return arrImages;
	}

	public void setArrImages(ArrayList<Image> arrImages) {
		this.arrImages = arrImages;
	}

	public int getId() {
		return id;
	}

	public void changeOrient(int upOrient) {
		if (this.orient != upOrient) {
			this.orient = upOrient;
			x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
			y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
		}
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOrient() {
		return orient;
	}

	public void setOrient(int orient) {
		this.orient = orient;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Tank(ArrayList<Image> arrImages, int id, int x, int y, int size,
			int orient, float speed) {
		super();
		this.arrImages = arrImages;
		this.id = id;
		this.x = x;
		this.y = y;
		this.size = size;
		this.orient = orient;
		this.speed = speed;
	}

	public Tank() {
		super();
	}

	public Tank(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, size, size);
	}

	public Bullet fireBullet(int id) {
		return new Bullet(ImageMgr.arrBulletImages, id, x, y,Common.BULLET_SIZE, orient, Common.STANDARD_SPEED);
	}
}
