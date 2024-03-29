package fr.sny1411.bingo.listener;

import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import fr.sny1411.bingo.utils.Game;

public class EventsListener implements Listener{
	public Hashtable<String, Material> blockSpawn = new Hashtable<>();
	private Game game;
	public EventsListener(Game game) {
		this.game = game;
		blockSpawn.put("Orange", Material.ORANGE_STAINED_GLASS);
		blockSpawn.put("Rouge", Material.RED_STAINED_GLASS);
		blockSpawn.put("Violet", Material.PURPLE_STAINED_GLASS);
		blockSpawn.put("Rose", Material.PINK_STAINED_GLASS);
		blockSpawn.put("Vert", Material.LIME_STAINED_GLASS);
		blockSpawn.put("Bleu", Material.LIGHT_BLUE_STAINED_GLASS);
	}
	
	@EventHandler
	public void damageEvent(EntityDamageEvent e) {
		if (game.DamagePlayer == false) {
			if (e.getEntity().getType().toString().equalsIgnoreCase("PLAYER")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	private void test (PlayerResourcePackStatusEvent e) {
		Bukkit.getConsoleSender().sendMessage("[RESSOURCEPACK] " + e.getPlayer().getName() + ": " + e.getStatus());
	}
	
	@EventHandler
	public void inventoryMoveEvent(InventoryClickEvent e) {
		if (game.InSetup == true) {
			e.setCancelled(true);
		}	
	}
	
	@EventHandler
	public void inventoryDropEvent(PlayerDropItemEvent e) {
		if (game.InSetup == true) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (game.InSetup == true || game.gameLaunch == false) {
			e.setCancelled(true);
		}
 	} 
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (game.InSetup || game.gameLaunch == false) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (game.gameLaunch) {
			return;
		} else {
			Player player = e.getPlayer();
			String teamPlayer = game.teams.findTeamPlayer(player);
			if (teamPlayer.equalsIgnoreCase("Spectator")) {
				game.teams.listSpectator.remove(player);
			} else if (!teamPlayer.equalsIgnoreCase("")) { // si le joueur a une team
				game.teams.removePlayer(teamPlayer, player);
			}
		}
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		if (game.InSetup) {
			String teamPlayer = game.teams.findTeamPlayer(e.getPlayer());
			if (!teamPlayer.equalsIgnoreCase("Spectator") && !teamPlayer.equalsIgnoreCase("")) {
				Location location = e.getPlayer().getLocation();
				location.setY(200);
				Block block =  game.listWorld.get(0).getBlockAt(location);
				Material type = block.getType();
				if (type != Material.AIR && type != Material.WHITE_STAINED_GLASS) {
					block.setType(blockSpawn.get(game.teams.findTeamPlayer(e.getPlayer())));
				}
			} 
		}
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (game.InSetup) {
			game.inventorySelectTeams(e.getPlayer());
			Location spawn = new Location(Bukkit.getServer().getWorld(game.listWorld.get(0).getName()), 0, 204, 0); // overworld
			e.getPlayer().teleport(spawn);
		} else if (game.gameLaunch) {
			Player player = e.getPlayer();
			for (String nameTeam : game.teams.teamsHash.keySet()) {
				List<Player> listPlayerTeam = game.teams.teamsHash.get(nameTeam);
				int i = 0;
				for (Player playerInTeam : listPlayerTeam) {
					if (playerInTeam.getName().equalsIgnoreCase(player.getName())) {
						listPlayerTeam.set(i, player);
						player.setPlayerListName(game.teams.prefixeColorTeams.get(nameTeam) + player.getName());
						return;
					}
					i++;
				}
			}
			game.teams.listSpectator.add(player);
			player.setPlayerListName(game.teams.prefixeColorTeams.get("Spectator") + player.getName());
			player.setGameMode(GameMode.SPECTATOR);
		}
		
	}
	
	@EventHandler
	public void playerQuitGui(InventoryCloseEvent e) {
		if (game.InSetup == true) {
			if (e.getView().getTitle().equalsIgnoreCase("§3§lSélection des équipes")) {
				game.teams.playerInGui.remove(e.getPlayer());
			}
		} else if (e.getView().getTitle().equalsIgnoreCase("§3§lBINGO")){
			String teamPlayer = game.teams.findTeamPlayer((Player) e.getPlayer());
			if (!teamPlayer.equalsIgnoreCase("Spectator")) {
				game.teams.playersOnBingoGui.get(teamPlayer).remove(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void OnPlayerSendMessage(AsyncPlayerChatEvent e) {
		String teamPlayer = game.teams.findTeamPlayer(e.getPlayer());
		if (teamPlayer.equalsIgnoreCase("Spectator")) {
			e.setFormat("§8§l%s §r§e» §7%s");
		} else {
			e.setFormat(game.teams.prefixeColorTeams.get(teamPlayer) + "§l%s §r§e» §7%s");
		}
	}
}
