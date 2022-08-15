package fr.sny1411.bingo.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlocksFill {
	
	public void changeArea(int x,int y,int z, int x2, int y2, int z2, Material typeBlock, World world) { // en dev , fonctionne pas :(
		for (int xArea = x; xArea < x2; xArea++) {
			for (int yArea = y; yArea < y2; y++) {
				for (int zArea = z; zArea < z2; z++) {
					Block block = Bukkit.getWorld(world.getName()).getBlockAt(new Location(world, xArea, yArea, zArea));
					block.setType(typeBlock);
				}
			}
		}
	}
}
