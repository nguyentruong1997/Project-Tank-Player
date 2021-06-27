package com.dvt.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.dvt.gui.MapPanel;
import com.dvt.map.Bird;
import com.dvt.map.Items;
import com.dvt.map.MapManager;
import com.dvt.other.Common;
import com.dvt.other.Effect;
import com.dvt.other.EffectMgr;
import com.dvt.other.ImageMgr;
import com.dvt.other.SoundMgr;

public class Bullet extends Tank {

	private boolean isRuning = true;

	public Bullet(ArrayList<Image> arrImages, int id, int x, int y, int size,int orient, float speed) {
		super(arrImages, id, x, y, size, orient, speed);
		getStandartBullet();
	}

	public boolean isRuning() {
		return isRuning;
	}

	public void setRuning(boolean isRuning) {
		this.isRuning = isRuning;
	}

	public void getStandartBullet() {
		switch (orient) {
		case Common.UP_ORIENT:
			x += Common.TANK_SIZE / 2 - Common.BULLET_SIZE / 2;
			y -= Common.BULLET_SIZE;
			break;
		case Common.DOWN_ORIENT:
			x += Common.TANK_SIZE / 2 - Common.BULLET_SIZE / 2;
			y += Common.TANK_SIZE;
			break;
		case Common.LEFT_ORIENT:
			y += Common.TANK_SIZE / 2 - Common.BULLET_SIZE / 2;
			x -= Common.BULLET_SIZE;
			break;
		case Common.RIGH_ORIENT:
			y += Common.TANK_SIZE / 2 - Common.BULLET_SIZE / 2;
			x += Common.TANK_SIZE;
			break;
		}
	}

	public boolean move(MapPanel mapPanel, MapManager mapManager,BulletManager bulletManager, TankPlayer tankPlayer,BossManager bossManager, GiftManager giftManager) {
		switch (orient) {
		case Common.UP_ORIENT:
			if (y > 0) {
				if (!checkBulletAll(mapPanel, mapManager, bulletManager,tankPlayer, bossManager, giftManager)) {
					return false;
				}
				y -= speed;
				return true;
			} else {
				isRuning = false;
				return false;
			}
		case Common.DOWN_ORIENT:
			if (y < Common.MAP_SIZE - Common.BULLET_SIZE) {
				if (!checkBulletAll(mapPanel, mapManager, bulletManager,tankPlayer, bossManager, giftManager)) {
					return false;
				}
				y += speed;
				return true;
			} else {
				isRuning = false;
				return false;
			}
		case Common.LEFT_ORIENT:
			if (x > 0) {
				if (!checkBulletAll(mapPanel, mapManager, bulletManager,tankPlayer, bossManager, giftManager)) {
					return false;
				}
				x -= speed;
				return true;
			} else {
				isRuning = false;
				return false;
			}
		case Common.RIGH_ORIENT:
			if (x < Common.MAP_SIZE - Common.BULLET_SIZE) {
				if (!checkBulletAll(mapPanel, mapManager, bulletManager,tankPlayer, bossManager, giftManager)) {
					return false;
				}
				x += speed;
				return true;
			} else {
				isRuning = false;
				return false;
			}
		}
		return true;
	}

	public boolean checkBulletAll(MapPanel mapPanel, MapManager mapManager,BulletManager bulletManager, TankPlayer tankPlayer,BossManager bossManager, GiftManager giftManager) {
		if (checkKillBird(mapPanel)) {
			isRuning = false;
			return false;
		}
		if (checkKillWall(mapManager, bulletManager)) {
			isRuning = false;
			return false;
		}
		if (checkKillBullet(bulletManager)) {
			isRuning = false;
			return false;
		}
		if (checkKillTank(tankPlayer, bossManager, bulletManager, giftManager)) {
			isRuning = false;
			return false;
		}
		return true;
	}

	public boolean checkKillBird(MapPanel map) {
		Rectangle rectBird = map.getBird().getRectangle();
		if (getRectangle().intersects(rectBird)) {
			EffectMgr.addEffect(new Effect(ImageMgr.arrBumImages,12 * Common.ITEM_SIZE, 24 * Common.ITEM_SIZE));
			Bird.IS_LIVE = false;
			return true;
		}
		return false;
	}

	public boolean checkKillWall(MapManager map, BulletManager bulletManager) {
		boolean kt = false;
		int sizeMap = map.getArrItems().size() - 1;
		for (int i = sizeMap; i >= 0; i--) {
			Items item = map.getArrItems().get(i);
			Rectangle rectStone = item.getRectangle();
			if (getRectangle().intersects(rectStone)&& !(item.allowBulletPass())) {
				if (item.getType() == 1)
					map.removeItem(item);
				bulletManager.removeBullet(this);
				kt = true;
			}
		}
		return kt;
	}

	public boolean checkKillBullet(BulletManager bullentManager) {
		if (getId() == Common.BOSS_ID) {
			Bullet bullet;
			for (int i = 0; i < bullentManager.getArrBullet().size(); i++) {
				bullet = bullentManager.getArrBullet().get(i);
				if (getX() == bullet.getX() && getY() == bullet.getY() && getOrient() == bullet.getOrient()) {
					
				} else {
					if (bullet.getId() == Common.TANK_ID && getRectangle().intersects(bullet.getRectangle())) {
						bullentManager.removeBullet(this);
						bullentManager.removeBullet(bullet);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkKillTank(TankPlayer tankPlayer,BossManager bossManager, BulletManager bulletManager,GiftManager giftManager) {
		Boss boss;
		Random random = new Random();
		int xGift, yGift, indexGift;
		Gift gift;
		if (getId() == Common.TANK_ID) {
			for (int i = 0; i < bossManager.getArrBoss().size(); i++) {
				boss = bossManager.getArrBoss().get(i);
				if (getRectangle().intersects(boss.getRectangle())) {
					if (random.nextInt(100) > 90 && giftManager.getArrGift().size() <1) {
						MapPanel.canGift = true;
						if (MapPanel.canGift == true) {
							xGift = random.nextInt(650);
							yGift = random.nextInt(650);
							indexGift = random.nextInt(2);
							gift = new Gift(xGift, yGift, indexGift,ImageMgr.arrGiftImages);
							giftManager.arrGift.add(gift);
						}
					}
					if (boss.getLevelBoss() == 1) {
						EffectMgr.addEffect(new Effect(ImageMgr.arrBumImages,boss.getX(), boss.getY()));
						bossManager.getArrBoss().remove(i);
						SoundMgr.play(SoundMgr.killBoss);
						bulletManager.getArrBullet().remove(this);
						return true;
					} else if (boss.getLevelBoss() == 2) {
						if (boss.getHpTank() == 2) {
							SoundMgr.play(SoundMgr.killBoss);
							boss.setHpTank(1);
							boss.setArrImages(ImageMgr.arrBoss22Images);
							bulletManager.removeBullet(this);
						} else {
							EffectMgr.addEffect(new Effect(ImageMgr.arrBumImages, boss.getX(), boss.getY()));
							SoundMgr.play(SoundMgr.killBoss);
							bossManager.arrBoss.remove(boss);
							bulletManager.removeBullet(this);
						}
					} else {
						if (boss.getHpTank() == 3) {
							SoundMgr.play(SoundMgr.killBoss);
							boss.setHpTank(2);
							boss.setArrImages(ImageMgr.arrBoss32Images);
							bulletManager.removeBullet(this);
						} else if (boss.getHpTank() == 2) {
							SoundMgr.play(SoundMgr.killBoss);
							boss.setHpTank(1);
							boss.setArrImages(ImageMgr.arrBoss33Images);
							bulletManager.removeBullet(this);
						} else {
							EffectMgr.addEffect(new Effect(ImageMgr.arrBumImages, boss.getX(), boss.getY()));
							SoundMgr.play(SoundMgr.killBoss);
							bossManager.arrBoss.remove(boss);
							bulletManager.removeBullet(this);
						}
					}
				}
			}
		}
		if (getId() == Common.BOSS_ID) {
			if (getRectangle().intersects(tankPlayer.getRectangle())) {
				MapPanel.numPlayer -= 1;
				if (MapPanel.numPlayer > 0) {
					EffectMgr.addEffect(new Effect(ImageMgr.arrBumImages,tankPlayer.getX(), tankPlayer.getY()));
					SoundMgr.play(SoundMgr.newTank);
					tankPlayer.newTank();
					return true;
				}
			}
			return false;
		}
		return false;
	}
}