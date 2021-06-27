package com.dvt.other;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Effect {
	
	private ArrayList<Image> arrImages;
	private int x;
	private int y;
	public int index=0;

	public Effect(ArrayList<Image> arrImages, int x, int y) {
		this.arrImages=arrImages;
		this.x=x;
		this.y=y;
	}
	public boolean draw(Graphics2D g){
		if(index<arrImages.size()){
			g.drawImage(arrImages.get(index++), x-Common.ITEM_SIZE, y-Common.ITEM_SIZE, null);
			return true;
		}
		return false;
	}
}
