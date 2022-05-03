package fr.sny1411.bingo.listenner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.sny1411.bingo.utils.Game;

public class EventsListener implements Listener{
	

	private Game game;
	public EventsListener(Game game) {
		this.game = game;
	}

	@EventHandler
	public void damageEvent(EntityDamageEvent e) {
		if (game.DamagePlayer == false) {
			e.setCancelled(true);
		}
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
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		String teamPlayer = game.teams.findTeamPlayer(player);
		if (teamPlayer.equalsIgnoreCase("Spectator")) {
			game.teams.listSpectator.remove(player);
		} else if (!teamPlayer.equalsIgnoreCase("")) { // si le joueur à une team
			game.teams.removePlayer(teamPlayer, player);
		}
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (game.InSetup) {
			game.inventorySelectTeams(e.getPlayer());
			Location spawn = new Location(Bukkit.getServer().getWorld("world"), 0, 204, 0);
			e.getPlayer().teleport(spawn);
		} else if (game.gameLaunch) {
			Player player = e.getPlayer();
			if (game.teams.findTeamPlayer(player) == "") {
				game.teams.listSpectator.add(player);
			}
		}
		
	}
	
	@EventHandler
	public void playerQuitGui(InventoryCloseEvent e) {
		if (game.InSetup == true) {
			if (e.getView().getTitle().equalsIgnoreCase("§3§lSélection des équipes")) {
				game.teams.playerInGui.remove(e.getPlayer());
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
