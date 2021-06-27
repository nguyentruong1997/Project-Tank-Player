package com.dvt.map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.dvt.other.ImageMgr;

public class MapManager {
	
	private ArrayList<Items> arrItems;
	
	
	public MapManager(int level) {
		arrItems=new ArrayList<Items>();
		setArrItems("src/MAPS/map"+level);
	}
	
	public void setArrItems(String path){
		BufferedReader br=null;
		FileInputStream fis=null;
		InputStreamReader isr=null;
		File file=new File(path);
		try {
			fis=new FileInputStream(file);
			isr=new InputStreamReader(fis,"UTF-8");
			br = new BufferedReader(isr);
			String content="";
			int row=0;
			int type;
			while ((content = br.readLine()) != null) {
				for (int col = 0; col < content.length(); col++) {
					type=Integer.parseInt(content.charAt(col)+"");
					arrItems.add(new Items(col, row, type, ImageMgr.arrItemsImages.get(type)));
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Items> getArrItems() {
		return arrItems;
	}
	
	public void drawBottomUnit(Graphics2D g) {
		for (int i = arrItems.size() - 1; i >= 0; i--) {
			try {
				if (arrItems.get(i).getType() != 3)
					arrItems.get(i).drawItem(g);
			} catch (Exception e) {
			}
		}
	}

	public void drawTopUnit(Graphics2D g) {
		for (int i = arrItems.size() - 1; i >= 0; i--) {
			if (arrItems.get(i).getType() == 3)
				arrItems.get(i).drawItem(g);
		}
	}	
	
	public void removeItem(Items item){
		arrItems.remove(item);
	}
}
