package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkLoader implements Listener {

	private List<Chunk> chunksForceLoad;
	
	public ChunkLoader() {
		chunksForceLoad = new ArrayList<>();
	}
	
	public void addChunkForceLoad(Chunk chunk) {
		chunksForceLoad.add(chunk);
	}
	
	public void addChunkForceLoad(Location location) {
		chunksForceLoad.add(location.getChunk());
	}
	
	public void addChunkForceLoad(World world, int x, int y, int z) {
		chunksForceLoad.add(new Location(world, x, y, z).getChunk());
	}
	
	public List<Chunk> getChunksLoad() {
		return chunksForceLoad;
	}
	
	public void unloadAllChunlks() {
		chunksForceLoad = new ArrayList<>();
	}
	
	
	
	@EventHandler
	private void OnChunkUnload(ChunkUnloadEvent e) {
		if (chunksForceLoad.contains(e.getChunk())) {
			e.getChunk().setForceLoaded(true);
			System.out.println("spawn unload");
		}
	}
}
