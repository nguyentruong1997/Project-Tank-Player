package com.dvt.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.dvt.map.Bird;
import com.dvt.map.MapManager;
import com.dvt.other.Common;

public class TankPlayer extends Tank {

	public TankPlayer() {
		x = 8 * Common.ITEM_SIZE;
		y = 24 * Common.ITEM_SIZE;
		orient = Common.UP_ORIENT;
	}

	public void newTank() {
		x = 8 * Common.ITEM_SIZE;
		y = 24 * Common.ITEM_SIZE;
		orient = Common.UP_ORIENT;
	}

	public TankPlayer(ArrayList<Image> arrImages, int id, int x, int y,int size, int orient, float speed) {
		super(arrImages, id, x, y, size, orient, speed);
	}

	public void move(MapManager map,BossManager bossManager,Bird bird) {
		switch (orient) {
		case Common.UP_ORIENT:
			if (y > 0) {
				y-=speed;
				if (vaCham(map,bossManager,bird)) {
					y+=speed;
				}
			}
			break;
		case Common.DOWN_ORIENT:
			if (y < Common.MAP_SIZE - Common.TANK_SIZE) {
				y += speed;
				if (vaCham(map,bossManager,bird)) {
					y -= speed;
				}
			}
			break;
		case Common.LEFT_ORIENT:
			if (x > 0) {
				x -= speed;
				if (vaCham(map,bossManager,bird)) {
					x += speed;
				}
			}
			break;
		case Common.RIGH_ORIENT:
			if (x < Common.MAP_SIZE - Common.TANK_SIZE) {
				x += speed;
				if (vaCham(map,bossManager,bird)) {
					x -= speed;
				}
			}
			break;
		}
	}

	public boolean vaCham(MapManager map, BossManager bossManager, Bird bird) {
		Tank tank = new Tank(x, y, size);
		int sizeMap = map.getArrItems().size();
		Rectangle rectItem;
		for (int i = 0; i < sizeMap; i++) {
			rectItem = map.getArrItems().get(i).getRectangle();
			if (tank.getRectangle().intersects(rectItem) && !(map.getArrItems().get(i).allowTankPass())) {
				return true;
			}
		}
		int sizeBoss = bossManager.arrBoss.size();
		Rectangle rectBoss;
		for (int i = 0; i < sizeBoss; i++) {
			rectBoss = bossManager.arrBoss.get(i).getRectangle();
			if (tank.getRectangle().intersects(rectBoss)) {
				return true;
			}
		}
		if (bird.getRectangle().intersects(tank.getRectangle())) {
			return true;
		}
		return false;
	}
}
