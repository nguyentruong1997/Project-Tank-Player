package com.dvt.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import com.dvt.gui.MapPanel;
import com.dvt.other.Effect;
import com.dvt.other.EffectMgr;
import com.dvt.other.ImageMgr;

public class GiftManager {
	
	ArrayList<Gift> arrGift=new ArrayList<Gift>();
	
	public void drawAllGift(Graphics2D g){
		int size=arrGift.size();
		for(int i=0;i<size;i++){
			arrGift.get(i).drawGift(g);
		}
	}
	
	public void removeGift(Gift g){
		arrGift.remove(g);
	}
	
	public ArrayList<Gift> getArrGift() {
		return arrGift;
	}
	
	public void setArrGift(ArrayList<Gift> arrGift) {
		this.arrGift = arrGift;
	}
	
	public void vaChamTankWithGift(TankPlayer tankPlayer,BossManager boss){
		int size=arrGift.size();
		if(size>0){
			for(int i=0;i<size;i++){
				if(arrGift.get(i).getRectangle().intersects(tankPlayer.getRectangle())){
					if(arrGift.get(i).getIndexGift()==0){
						for(int k=0;k<boss.getArrBoss().size();k++){
							EffectMgr.addEffect(new Effect(ImageMgr.arrBumImages, boss.getArrBoss().get(i).getX(), boss.getArrBoss().get(i).getY()));
						}
						boss.getArrBoss().clear(); 
						arrGift.remove(i);
					}else if(arrGift.get(i).getIndexGift()==1){
						MapPanel.numPlayer+=1;
						arrGift.remove(i);
					}
					MapPanel.canGift=false;
				}
			}
		}
	}
}
