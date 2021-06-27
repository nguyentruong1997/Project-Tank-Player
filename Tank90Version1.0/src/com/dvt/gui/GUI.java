package com.dvt.gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import com.dvt.other.Common;
import com.dvt.other.EffectMgr;
import com.dvt.other.ImageMgr;
import com.dvt.other.SoundMgr;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private GameFristPanel gameFirst;
	private GamePlayPanel gamePlay;
	public static  boolean IS_RUNNING=false;
	
	public GUI(){
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setLayout(new CardLayout());	
		initObject();
		addWindowFocusListener(adapter);
	}
	public void initObject(){
		new Common();
		new ImageMgr();
		new SoundMgr();
		new EffectMgr();
		gameFirst=new GameFristPanel();
		gameFirst.setGui(this);
		gamePlay=new GamePlayPanel();
		add(gameFirst);
		add(gamePlay);
	}
	
	public GamePlayPanel getGamePlay() {
		return gamePlay;
	}

	public GameFristPanel getGameFirst() {
		return gameFirst;
	}

	WindowAdapter adapter=new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			IS_RUNNING=false;
			System.exit(0);
		}
	};
}
