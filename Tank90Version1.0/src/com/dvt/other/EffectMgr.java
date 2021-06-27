package com.dvt.other;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class EffectMgr {
	
	public static ArrayList<Effect> arrEffect;
	
	public EffectMgr(){
		arrEffect=new ArrayList<Effect>();
	}
	
	public static void addEffect(Effect effect){
		arrEffect.add(effect);
	}
	
	public void drawEffect(Graphics2D g){
		for (int i = arrEffect.size()-1; i >= 0; i--) {
			if(!arrEffect.get(i).draw(g))
				arrEffect.remove(i);
		}
	}
}
