package com.dvt.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import com.dvt.gui.MapPanel;
import com.dvt.map.MapManager;

public class BulletManager {
	
	private ArrayList<Bullet> arrBullet;

	public ArrayList<Bullet> getArrBullet() {
		return arrBullet;
	}

	public BulletManager() {
		arrBullet = new ArrayList<Bullet>();
	}

	public void setArrBullet(ArrayList<Bullet> arrBullet) {
		this.arrBullet = arrBullet;
	}

	public void addBullet(Bullet bullet) {
		arrBullet.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		arrBullet.remove(bullet);
	}

	public void drawAllBullet(Graphics2D g) {
		for (int i = arrBullet.size() - 1; i >= 0; i--) {
			try {
				if (arrBullet.get(i).isRuning())
					arrBullet.get(i).drawTank(g);
			} catch (Exception e) {
			}
		}
	}

	public void moveAllBullet(MapPanel mapPanel, MapManager mapManager,BulletManager bulletManager, TankPlayer tankPlayer,BossManager bossManager,GiftManager giftManager) {
		try {
			for (int i = arrBullet.size() - 1; i >= 0; i--) {
				if (!arrBullet.get(i).move(mapPanel, mapManager,bulletManager, tankPlayer, bossManager,giftManager) || !arrBullet.get(i).isRuning()) {
					arrBullet.remove(i);
				}
			}
		} catch (Exception e) {
		}
	}
}
