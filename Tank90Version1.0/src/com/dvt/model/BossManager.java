package com.dvt.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.dvt.gui.MapPanel;
import com.dvt.map.Bird;
import com.dvt.map.MapManager;
import com.dvt.other.Common;
import com.dvt.other.ImageMgr;

public class BossManager {

	public ArrayList<Boss> arrBoss = new ArrayList<Boss>();

	public BossManager() {
	}

	public boolean addListTankBoss(BossManager bossManager,
			TankPlayer tankPlayer) {
		if (arrBoss.size() < 2 && MapPanel.numBoss > 0) {
			if (checkLocationBoss(bossManager, tankPlayer) == false) {
				if (MapPanel.numBoss >= 12) {
					for (int i = 0; i < 3; i++) {
						arrBoss.add(new Boss(ImageMgr.arrBoss1Images,Common.BOSS_ID,i * (Common.MAP_SIZE / 2 / Common.TANK_SIZE)
										* Common.TANK_SIZE, 0,Common.TANK_SIZE, Common.DOWN_ORIENT,Common.STANDARD_SPEED / 4, 1));
					}
					MapPanel.numBoss -= 3;
				} else if (MapPanel.numBoss >= 6) {
					for (int i = 0; i < 3; i++) {
						arrBoss.add(new Boss(ImageMgr.arrBoss21Images,Common.BOSS_ID,i * (Common.MAP_SIZE / 2 / Common.TANK_SIZE)
										* Common.TANK_SIZE, 0,Common.TANK_SIZE, Common.DOWN_ORIENT,Common.STANDARD_SPEED / 4, 2));
					}
					MapPanel.numBoss -= 3;
				} else if (MapPanel.numBoss >= 3) {
					for (int i = 0; i < 3; i++) {
						arrBoss.add(new Boss(ImageMgr.arrBoss31Images,Common.BOSS_ID,i * (Common.MAP_SIZE / 2 / Common.TANK_SIZE)
										* Common.TANK_SIZE, 0,Common.TANK_SIZE, Common.DOWN_ORIENT,Common.STANDARD_SPEED / 4, 3));
					}
					MapPanel.numBoss -= 3;
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public boolean checkLocationBoss(BossManager bossManager,TankPlayer tankPlayer) {
		Rectangle rect0 = new Rectangle(0, 0, 56, 56);
		Rectangle rect1 = new Rectangle(336, 0, 56, 56);
		Rectangle rect2 = new Rectangle(672, 0, 56, 56);
		Boss boss;
		for (int i = 0; i < bossManager.getArrBoss().size(); i++) {
			boss = bossManager.getArrBoss().get(i);
			if (boss.getRectangle().intersects(rect0) || boss.getRectangle().intersects(rect1) || boss.getRectangle().intersects(rect2)) {
				return true;
			}
		}
		if (tankPlayer.getRectangle().intersects(rect0) || tankPlayer.getRectangle().intersects(rect1) || tankPlayer.getRectangle().intersects(rect2)) {
			return true;
		}
		return false;
	}

	public void drawAllBoss(Graphics2D g) {
		for (int i = arrBoss.size() - 1; i >= 0; i--) {
			arrBoss.get(i).drawTank(g);
		}
	}

	public void autoMoveAllBoss(MapManager map, TankPlayer tankPlayer, Bird bird) {
		for (int i = arrBoss.size() - 1; i >= 0; i--) {
			arrBoss.get(i).moveBoss(map, tankPlayer, this, bird);
		}
	}

	public void autoFireAll(BulletManager bulletMgr) {
		Boss tankBoss;
		for (int i = arrBoss.size() - 1; i >= 0; i--) {
			tankBoss = arrBoss.get(i);
			bulletMgr.addBullet(tankBoss.fireBullet(Common.BOSS_ID));
		}
	}

	public ArrayList<Boss> getArrBoss() {
		return arrBoss;
	}

	public void setArrBoss(ArrayList<Boss> arrBoss) {
		this.arrBoss = arrBoss;
	}
}
