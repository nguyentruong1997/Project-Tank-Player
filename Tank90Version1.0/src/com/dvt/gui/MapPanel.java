package com.dvt.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.dvt.map.Bird;
import com.dvt.map.MapManager;
import com.dvt.model.BossManager;
import com.dvt.model.BulletManager;
import com.dvt.model.GiftManager;
import com.dvt.model.TankPlayer;
import com.dvt.other.Common;
import com.dvt.other.EffectMgr;
import com.dvt.other.ImageMgr;
import com.dvt.other.SoundMgr;

public class MapPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private MapManager mapManager;
	private Bird bird;
	private TankPlayer tankPlayer;
	private BossManager bossManager;
	private BulletManager bulletManager;
	private GamePlayPanel gamePlayPanel;
	private EffectMgr effectMgr;
	private GiftManager giftManager;
	private BitSet bitset = new BitSet(256);
	private int count;
	private int countGift=0;
	private boolean is_Pause = false;
	public static int level = 1;
	public static int numBoss = 24;
	public static int numPlayer = 3;
	public static int statusGame = 0;
	private Graphics2D g;
	public static boolean canGift=false;
	
	public MapPanel() {
		setBounds((Common.WIDTH_FRAME - Common.MAP_SIZE) / 2,Common.PADDING_TOP, Common.MAP_SIZE, Common.MAP_SIZE);
		setBackground(Color.black);
		setFocusable(true);
		initObject();
		new Thread(MapPanel.this).start();
	}

	public void initObject() {
		GUI.IS_RUNNING = true;
		is_Pause = false;
		numBoss = 24;
		numPlayer = 3;
		bitset.clear();
		mapManager = new MapManager(level);
		tankPlayer = new TankPlayer(ImageMgr.arrPlayerImages, Common.TANK_ID,8 * Common.ITEM_SIZE, 24 * Common.ITEM_SIZE, 
				Common.TANK_SIZE,Common.UP_ORIENT, Common.STANDARD_SPEED / 2);
		tankPlayer.setArrImages(ImageMgr.arrPlayerImages);
		bulletManager = new BulletManager();
		bossManager = new BossManager();
		bird = new Bird();
		giftManager=new GiftManager();
		effectMgr = new EffectMgr();
		addKeyListener(keyEvent);
		SoundMgr.play(SoundMgr.startGame);
		SoundMgr.play(SoundMgr.move);
	}

	public void drawAllMap(Graphics2D g) {
		mapManager.drawBottomUnit(g);
		tankPlayer.drawTank(g);
		bird.drawBird(g);
		bossManager.drawAllBoss(g);
		bulletManager.drawAllBullet(g);
		mapManager.drawTopUnit(g);
		effectMgr.drawEffect(g);
	}

	public void setGamePlayPanel(GamePlayPanel gamePlayPanel) {
		this.gamePlayPanel = gamePlayPanel;
	}

	@Override
	protected void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		g = (Graphics2D) graphic;
		drawAllMap(g);
	}

	@Override
	public void run() {
		while (GUI.IS_RUNNING && Bird.IS_LIVE) {
			if (is_Pause == false) {
				count++;
				if (count == 100000000) {
					count = 0;
				}
				if (count % 80 == 0) {
					tankPlayer.setFireBullet(true);
				}
				if (count % 240 == 0) {
					bossManager.autoFireAll(bulletManager);
				}
				if (bossManager.addListTankBoss(bossManager, tankPlayer)) {
					gamePlayPanel.repaint();
					repaint();
				}
				if(giftManager.getArrGift().size()>0){
					countGift++;
					if(countGift>2000){
						giftManager.getArrGift().clear();
						countGift=0;
						canGift=false;
					}
				}
				drawGift();
				giftManager.vaChamTankWithGift(tankPlayer, bossManager);
				bossManager.autoMoveAllBoss(mapManager, tankPlayer, bird);
				bulletManager.moveAllBullet(this, mapManager, bulletManager,tankPlayer, bossManager,giftManager);
				checkGame();
			} else {

			}
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			catchEvent();
			gamePlayPanel.repaint();
			repaint();
		}
	}	
	
	public void drawGift(){
		if(canGift==true){
			giftManager.drawAllGift(g);
		}
	}
	
	@SuppressWarnings("static-access")
	private void checkGame() {
		if (numBoss == 0 && bossManager.getArrBoss().size() == 0) {
			statusGame = 1;
		} else if (bird.IS_LIVE == false || numPlayer == 0) {
			statusGame = -1;
		}
		if (statusGame == 0) {
			return;
		} else if (statusGame == 1) {
			JOptionPane.showMessageDialog(null,"YOU ARE WINER\n - You Are Awesome !\n - Cllick OK To Next Lelel.....");
			level++;
			initObject();
			statusGame = 0;
		} else if (statusGame == -1) {
			int option = JOptionPane.showConfirmDialog(null,"You are Lost! Do you want to continue playing it?",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				level = 1;
				initObject();
				statusGame = 0;
			} else {
				System.exit(0);
			}
		}
	}

	public Bird getBird() {
		return bird;
	}

	public void catchEvent() {
		if (bitset.get(KeyEvent.VK_P)) {
			is_Pause = !is_Pause;
			SoundMgr.stopAll();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (is_Pause == false) {
			if (bitset.get(KeyEvent.VK_UP)) {
				tankPlayer.changeOrient(Common.UP_ORIENT);
				tankPlayer.move(mapManager, bossManager, bird);
			}
			if (bitset.get(KeyEvent.VK_DOWN)) {
				tankPlayer.changeOrient(Common.DOWN_ORIENT);
				tankPlayer.move(mapManager, bossManager, bird);
			}
			if (bitset.get(KeyEvent.VK_LEFT)) {
				tankPlayer.changeOrient(Common.LEFT_ORIENT);
				tankPlayer.move(mapManager, bossManager, bird);
			}
			if (bitset.get(KeyEvent.VK_RIGHT)) {
				tankPlayer.changeOrient(Common.RIGH_ORIENT);
				tankPlayer.move(mapManager, bossManager, bird);
			}
			if(bitset.get(KeyEvent.VK_M)){
				SoundMgr.canPlay=!SoundMgr.canPlay;
				if(SoundMgr.canPlay==false){
					SoundMgr.stopAll();
				}else{
					SoundMgr.play(SoundMgr.move);
				}
				try {
					Thread.sleep(220);
				} catch (InterruptedException e) {
				}
			}
		}
		if (bitset.get(KeyEvent.VK_ESCAPE)) {
			int option = JOptionPane.showConfirmDialog(null,"Are you Quit?","Thông báo", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
			System.exit(0);
			}if(option==JOptionPane.NO_OPTION){
				bitset.clear(KeyEvent.VK_ESCAPE);
			}
		}
	}

	public KeyAdapter keyEvent = new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent e) {
			bitset.set(e.getKeyCode());
			if (bitset.get(KeyEvent.VK_SPACE)) {
				if (tankPlayer.getFireBullet()) {
					bulletManager.addBullet(tankPlayer.fireBullet(Common.TANK_ID));
					tankPlayer.setFireBullet(false);
					SoundMgr.play(SoundMgr.shoot);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			bitset.clear(e.getKeyCode());
		}
	};

}
