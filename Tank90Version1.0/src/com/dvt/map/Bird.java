package com.dvt.map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import com.dvt.other.Common;
import com.dvt.other.ImageMgr;

public class Bird {
	
	public static boolean IS_LIVE;
	private Image img;
	private static final int X_BIRD=12*Common.ITEM_SIZE;
	private static final int Y_BIRD=24*Common.ITEM_SIZE;
	
	public Bird() {
		IS_LIVE=true;
		this.img=ImageMgr.arrBirdImages.get(0);
	}

	public void drawBird(Graphics2D g){
		if(!IS_LIVE)
			img=ImageMgr.arrBirdImages.get(1);
		g.drawImage(img, X_BIRD, Y_BIRD, null);
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(X_BIRD, Y_BIRD, ImageMgr.arrBirdImages.get(0).getWidth(null),ImageMgr.arrBirdImages.get(0).getHeight(null));
	}
}
