package com.dvt.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import com.dvt.other.Common;
import com.dvt.other.ImageMgr;

public class GamePlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private MapPanel mapPanel;

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public GamePlayPanel(){
		setLayout(null);
		initObject();
		setBackground(Color.white);
		setVisible(false);	
	}
	
	public void initObject(){
		mapPanel=new MapPanel();
		mapPanel.setGamePlayPanel(this);
		add(mapPanel);
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D g=(Graphics2D)arg0;
		drawLeftRight(g);
	}
	
	public void drawLeftRight(Graphics2D g){
		g.drawImage(ImageMgr.imageLeft, Common.PADDING_LEFT, Common.PADDING_TOP, Common.LEFT_RIGHT_SIZE, Common.MAP_SIZE, null);
		g.drawImage(ImageMgr.imageRight, Common.RIGHT_START_X, Common.PADDING_TOP, Common.LEFT_RIGHT_SIZE, Common.MAP_SIZE, null);	
		g.drawImage(ImageMgr.arrNumImages.get(MapPanel.numPlayer), Common.RIGHT_START_X + 125, Common.PADDING_TOP + 500, 30, 30, null);
		g.drawImage(ImageMgr.arrNumImages.get(MapPanel.level), Common.RIGHT_START_X + 125, Common.PADDING_TOP + 650, 35, 35, null);
		for (int i = 0; i < MapPanel.numBoss/3; i++) {
			g.drawImage(ImageMgr.imageIconBoss, Common.RIGHT_START_X + 50,Common.PADDING_TOP + 50 * i + 30, 150, 50, null);
		}
	}
}
