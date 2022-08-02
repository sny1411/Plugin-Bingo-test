package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventDefisBonus {
	public int nombreEvent;
	public List<Integer> timeEvents = new ArrayList<>();
	
	
	public void initEvent() {
		nombreEvent = (int)(Math.random() * ((4 - 1) + 1)) + 1;
		for (int i = 0; i < nombreEvent; i++) {
			int timeEvent = (int)(Math.random() * ((100 - 15) + 1)) + 15;
			timeEvents.add(timeEvent);
		}
		Collections.sort(timeEvents);
		System.out.println(timeEvents);
	}
}
