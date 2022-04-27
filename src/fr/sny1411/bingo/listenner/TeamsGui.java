package fr.sny1411.bingo.listenner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.sny1411.bingo.utils.Game;

public class TeamsGui implements Listener {

	private Game game;
	private Inventory teamsGui;

	public TeamsGui(Game game) {
		this.game = game;
	}

	public void openTeamsGui(Player p) {
		ArrayList<Material> itemsTeams = new ArrayList<Material>(Arrays.asList(Material.ORANGE_BANNER,
				Material.RED_BANNER,Material.PURPLE_BANNER,Material.PINK_BANNER,Material.LIME_BANNER,Material.LIGHT_BLUE_BANNER));
		ArrayList<String> nameTeams = new ArrayList<String>(Arrays.asList("§6Orange","§cRouge", "§5Violet", "§dRose", "§aVert", "§bBleu"));
		teamsGui = Bukkit.createInventory(null, 27, "§3§lSélection des équipes");
		int compteurTeams = 0;
		for (int i = 0; i < 27; i++) {
			if (i > 9 && i < 17) {
				if (i < (16 - (6 - game.teams.nombreTeams))) {
					ItemStack items = new ItemStack(itemsTeams.get(i - 10),1);
					ItemMeta meta = items.getItemMeta();
					List<String> listPlayer = new ArrayList<String>();
					for (int j = 0; j < game.teams.nombreJoueursParTeams; j++) {
						if (j < game.teams.teamsHash.get(game.teams.colorTeams.get(compteurTeams)).size()) {
							listPlayer.add("§7§o- " + game.teams.teamsHash.get(game.teams.colorTeams.get(compteurTeams)).get(j).getDisplayName());
						} else {
							listPlayer.add("§7§o- ");
						}
					}
					compteurTeams ++;
					meta.setLore(listPlayer);
					meta.setDisplayName(nameTeams.get(i - 10));
					items.setItemMeta(meta);
					teamsGui.setItem(i, items);
					
				}
			} else {
				ItemStack items = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
				ItemMeta meta = items.getItemMeta();
				meta.setDisplayName(" ");
				items.setItemMeta(meta);
				teamsGui.setItem(i, items);
			}
		}
		game.teams.playerInGui.add(p);
		ItemStack spec = new ItemStack(Material.ENDER_EYE);
		ItemMeta specMeta = spec.getItemMeta();
		specMeta.setDisplayName("§e§lSpectateur");
		ArrayList<String> specLore = new ArrayList<>();
		specLore.add("§8>>§7 Clique pour observer la partie !");
		specMeta.setLore(specLore);
		spec.setItemMeta(specMeta);
		teamsGui.setItem(16, spec);
		p.openInventory(teamsGui);
	}
	
	public void updateGui(List<Player> players) {
		List<Player> playersList = new ArrayList<Player>(game.teams.playerInGui);
		for (int i = 0; i < playersList.size(); i++) {
			Player player = playersList.get(i);
			openTeamsGui(player);
		}
		
	}
	
	@EventHandler
	public void clickItems(PlayerInteractEvent e) {
		if (game.InSetup == true) {
			if (e.getMaterial() == Material.COMPASS) {
				openTeamsGui(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void clickItemInGui(InventoryClickEvent e) {
		if (e.getCurrentItem() != null) {
			if (game.InSetup == true) {
				System.out.println(game.teams.playerInGui);
				if (e.getView().getTitle().equalsIgnoreCase("§3§lSélection des équipes")) {
					if (e.getCurrentItem().getType() == Material.ORANGE_BANNER) {
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Orange")) return;
						if (!(teamPlayer == "")) {
							game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
						} else if (teamPlayer == "Spectator") {
							game.teams.listSpectator.remove((Player) e.getWhoClicked());
						}
						game.teams.addPlayer("Orange", (Player) e.getWhoClicked());
						updateGui(game.teams.playerInGui);
					} else if (e.getCurrentItem().getType() == Material.RED_BANNER){
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Rouge")) return;
						if (!(teamPlayer == "")) {
							game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
						} else if (teamPlayer == "Spectator") {
							game.teams.listSpectator.remove((Player) e.getWhoClicked());
						}
						game.teams.addPlayer("Rouge", (Player) e.getWhoClicked());
						updateGui(game.teams.playerInGui);
					} else if (e.getCurrentItem().getType() == Material.PURPLE_BANNER){
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Violet")) return;
						if (!(teamPlayer == "")) {
							game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
						} else if (teamPlayer == "Spectator") {
							game.teams.listSpectator.remove((Player) e.getWhoClicked());
						}
						game.teams.addPlayer("Violet", (Player) e.getWhoClicked());
						updateGui(game.teams.playerInGui);
					} else if (e.getCurrentItem().getType() == Material.PINK_BANNER) {
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Rose")) return;
						if (!(teamPlayer == "")) {
							game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
						} else if (teamPlayer == "Spectator") {
							game.teams.listSpectator.remove((Player) e.getWhoClicked());
						}
						game.teams.addPlayer("Rose", (Player) e.getWhoClicked());
						updateGui(game.teams.playerInGui);
					} else if (e.getCurrentItem().getType() == Material.LIME_BANNER) {
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Vert")) return;
						if (!(teamPlayer == "")) {
							game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
						} else if (teamPlayer == "Spectator") {
							game.teams.listSpectator.remove((Player) e.getWhoClicked());
						}
						game.teams.addPlayer("Vert", (Player) e.getWhoClicked());
						updateGui(game.teams.playerInGui);
					} else if (e.getCurrentItem().getType() == Material.LIGHT_BLUE_BANNER) {
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Bleu")) return;
						if (!(teamPlayer == "")) {
							game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
						} else if (teamPlayer == "Spectator") {
							game.teams.listSpectator.remove((Player) e.getWhoClicked());
						}
						game.teams.addPlayer("Bleu", (Player) e.getWhoClicked());
						updateGui(game.teams.playerInGui);
					} else if (e.getCurrentItem().getType() == Material.ENDER_EYE) {
						String teamPlayer = game.teams.findTeamPlayer((Player) e.getWhoClicked());
						if (teamPlayer.equalsIgnoreCase("Spectator")) return;
						if (!(teamPlayer == "Spectator")) {
							if (!(teamPlayer == "")) {
								game.teams.removePlayer(teamPlayer, (Player) e.getWhoClicked());
							}
							game.teams.listSpectator.add((Player) e.getWhoClicked());
							((Player) e.getWhoClicked()).setPlayerListName(game.teams.prefixeColorTeams.get("Spectator") + e.getWhoClicked().getName());
							updateGui(game.teams.playerInGui);
						}
					}
						
				}
			}
		}
		
	}

}
