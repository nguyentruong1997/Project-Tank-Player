package com.dvt.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import com.dvt.other.Common;
import com.dvt.other.ImageMgr;
import com.dvt.other.SoundMgr;

public class GameFristPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private GUI gui;

	public GameFristPanel(){
		setLayout(null);
		setFocusable(true);
		addKeyListener(key); // lắng nghe sự kiện click
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D g=(Graphics2D)arg0;
		drawBackGround(g);
	}
	public void drawBackGround(Graphics2D g){
		g.drawImage(ImageMgr.imageStart, 0, 0, Common.WIDTH_FRAME,Common.HEIGHT_FRAME, null);
	}
	
	KeyAdapter key=new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent arg0) {
			gui.getGamePlay().setVisible(true);
			setFocusable(false);
			setVisible(false);
			SoundMgr.play(SoundMgr.move);
		}
	};

}
