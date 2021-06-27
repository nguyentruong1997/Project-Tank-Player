package com.dvt.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import com.dvt.map.Bird;
import com.dvt.map.MapManager;
import com.dvt.other.Common;

public class Boss extends Tank {

	private int levelBoss;
	private int hpTank = 3;

	public Boss(ArrayList<Image> arrImages, int id, int x, int y, int size,int orient, float speed, int levelBoss) {
		super(arrImages, id, x, y, size, orient, speed);
		this.levelBoss = levelBoss;
		this.hpTank = levelBoss;
	}

	public void changeOrientTankBos(int orient) {
		this.orient = orient;
	}

	public void changeOri() {
		Random rd = new Random();
		int orientA = rd.nextInt(4);
		if (this.orient == orientA && this.orient == 0) {
			this.orient = orientA + 1;
		} else if (this.orient == orientA && this.orient == 1) {
			this.orient = orientA + 1;
		} else if (this.orient == orientA && this.orient == 2) {
			this.orient = orientA + 1;
		} else if (this.orient == orientA && this.orient == 3) {
			this.orient = orientA - 1;
		} else {
			this.orient = orientA;
		}
	}

	public void moveBoss(MapManager map, TankPlayer tank, BossManager tankMg,Bird bird) {
		Random random = new Random();
		switch (orient) {
		case Common.UP_ORIENT:
			if (y > 0) {
				y -= speed;
				if (random.nextInt(1000) > 996) {
					changeOri();
				}
				if (vaCham(map, bird)) {
					y += speed;
					changeOri();
				} else if (vaChamTankBosHeight(tankMg)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				} else if (vaChamTankBos(tankMg)) {
					y += speed;
					changeOri();
				} else if (vaChamTankHeight(tank)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				} else if (vaChamTank(tank)) {
					y += speed;
					this.orient = Common.DOWN_ORIENT;
				}
			} else if (y == 0) {
				changeOri();
			}
			break;
		case Common.DOWN_ORIENT:
			if (y < Common.MAP_SIZE - Common.TANK_SIZE) {
				y += speed;
				if (random.nextInt(1000) > 996) {
					changeOri();
				}
				if (vaCham(map, bird)) {
					y -= speed;
					changeOri();
				} else if (vaChamTankBosHeight(tankMg)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				} else if (vaChamTankBos(tankMg)) {
					y -= speed;
					changeOri();
				} else if (vaChamTankHeight(tank)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				} else if (vaChamTank(tank)) {
					y -= speed;
					this.orient = Common.UP_ORIENT;
				}
			} else if (y == Common.MAP_SIZE - Common.TANK_SIZE) {
				changeOri();
			}
			break;
		case Common.LEFT_ORIENT:
			if (x > 0) {
				x -= speed;
				if (random.nextInt(1000) > 996) {
					changeOri();
				}
				if (vaCham(map, bird)) {
					x += speed;
					changeOri();
				} else if (vaChamTankBosWidth(tankMg)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE
							* Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE
							* Common.ITEM_SIZE;
				} else if (vaChamTankBos(tankMg)) {
					x += speed;
					changeOri();
				} else if (vaChamTankWidth(tank)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				} else if (vaChamTank(tank)) {
					x += speed;
					this.orient = Common.RIGH_ORIENT;
				}
			} else if (x == 0) {
				changeOri();
			}
			break;
		case Common.RIGH_ORIENT:
			if (x < Common.MAP_SIZE - Common.TANK_SIZE) {
				x += speed;
				if (random.nextInt(1000) > 996) {
					changeOri();
				}
				if (vaCham(map, bird)) {
					x -= speed;
					changeOri();
				} else if (vaChamTankBosWidth(tankMg)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				} else if (vaChamTankBos(tankMg)) {
					x -= speed;
					changeOri();
				} else if (vaChamTankWidth(tank)) {
					x = (x + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
					y = (y + Common.ITEM_SIZE / 2) / Common.ITEM_SIZE * Common.ITEM_SIZE;
				}
				else if (vaChamTank(tank)) {
					x -= speed;
					this.orient = Common.LEFT_ORIENT;
				}
			} else if (x == Common.MAP_SIZE - Common.TANK_SIZE) {
				changeOri();
			}
			break;
		}
	}

	public boolean vaCham(MapManager map, Bird bird) {
		Rectangle rectStone;
		Tank tank = new Tank(x, y, size);
		for (int i = 0; i < map.getArrItems().size(); i++) {
			rectStone = map.getArrItems().get(i).getRectangle();
			if (tank.getRectangle().intersects(rectStone) && !(map.getArrItems().get(i).allowTankPass())) {
				return true;
			}
			Rectangle recBird = bird.getRectangle();
			if (tank.getRectangle().intersects(recBird) && !(map.getArrItems().get(i).allowTankPass())) {
				return true;
			}
		}

		return false;
	}

	public boolean vaChamTankBos(BossManager tankBosMng) {
		Tank tank = new Tank(x, y, size);
		Rectangle recTankBos;
		int sizeTankbos = tankBosMng.arrBoss.size();
		for (int i = 0; i < sizeTankbos; i++) {
			recTankBos = tankBosMng.arrBoss.get(i).getRectangle();
			if (this.getX() == tankBosMng.arrBoss.get(i).getX() && this.getY() == tankBosMng.arrBoss.get(i).getY()) {
			} else if (tank.getRectangle().intersects(recTankBos)) {
				return true;
			}
		}
		return false;
	}

	public boolean vaChamTankBosWidth(BossManager tankBosMng) {
		Tank tank = new Tank(x, y, size);
		Rectangle recTankBos;
		int sizeTankbos = tankBosMng.arrBoss.size();
		for (int i = 0; i < sizeTankbos; i++) {
			recTankBos = tankBosMng.arrBoss.get(i).getRectangle();
			if (this.getX() == tankBosMng.arrBoss.get(i).getX() && this.getY() == tankBosMng.arrBoss.get(i).getY()) {

			} else
			if (tank.getRectangle().intersects(recTankBos) && recTankBos.union(tank.getRectangle()).getWidth() < 111) {
				return true;
			}
		}
		return false;
	}

	public boolean vaChamTankBosHeight(BossManager tankBosMng) {
		Tank tank = new Tank(x, y, size);
		int sizeTankbos = tankBosMng.arrBoss.size();
		Rectangle recTankBos;
		for (int i = 0; i < sizeTankbos; i++) {
			recTankBos = tankBosMng.arrBoss.get(i).getRectangle();
			if (getX() == tankBosMng.arrBoss.get(i).getX() && getY() == tankBosMng.arrBoss.get(i).getY()) {
				
			} else
			if (tank.getRectangle().intersects(recTankBos)
					&& recTankBos.union(tank.getRectangle()).getHeight() < 111) {
				return true;
			}
		}
		return false;
	}

	public boolean vaChamTank(TankPlayer tankPlay) {
		Tank tank = new Tank(x, y, size);
		Rectangle recTankPlay = tankPlay.getRectangle();
		if (tank.getRectangle().intersects(recTankPlay)) {
			return true;
		}
		return false;
	}

	public boolean vaChamTankWidth(TankPlayer tankPlay) {
		Tank tank = new Tank(x, y, size);
		Rectangle recTankPlay = tankPlay.getRectangle();
		if (tank.getRectangle().intersects(recTankPlay) && recTankPlay.union(tank.getRectangle()).getWidth() < 110) {
			return true;
		}
		return false;
	}

	public boolean vaChamTankHeight(TankPlayer tankPlay) {
		Tank tank = new Tank(x, y, size);
		Rectangle recTankPlay = tankPlay.getRectangle();
		if (tank.getRectangle().intersects(recTankPlay) && recTankPlay.union(tank.getRectangle()).getHeight() < 111) {
			return true;
		}
		return false;
	}

	public int getLevelBoss() {
		return levelBoss;
	}

	public void setLevelBoss(int levelBoss) {
		this.levelBoss = levelBoss;
	}

	public int getHpTank() {
		return hpTank;
	}

	public void setHpTank(int hpTank) {
		this.hpTank = hpTank;
	}
}