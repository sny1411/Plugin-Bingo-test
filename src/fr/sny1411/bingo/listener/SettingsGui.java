package fr.sny1411.bingo.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.sny1411.bingo.utils.Game;
import fr.sny1411.bingo.utils.SkullCustom;

public class SettingsGui implements Listener {
	private Game game;
	private Inventory settingsGui;
	private SkullCustom skull = new SkullCustom();
	
	public SettingsGui(Game game) {
		this.game = game;
	}
	
	public void openSettingsGui(Player p) {
		settingsGui = Bukkit.createInventory(p, 27 ,"§3§lParamètres");
		ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ArrayList<String> itemLore = new ArrayList<>();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		for (int i = 0; i< 27; i++){
			if (i < 10 || i > 16) {
				settingsGui.setItem(i,item);
			}
		}
		item = new ItemStack(Material.WHITE_BANNER);
		meta = item.getItemMeta();
		meta.setDisplayName("§b§lEquipes");
		itemLore.add("§7>> Clique pour modifier");
		meta.setLore(itemLore);
		item.setItemMeta(meta);
		settingsGui.setItem(10,item);
		
		itemLore = new ArrayList<>();
		if (game.modeAffichage == "Chill"){
			item = new ItemStack(Material.IRON_BLOCK);
			meta = item.getItemMeta();
			meta.setDisplayName("§b§lAffichage des défis");
			itemLore.add("§6§l>> §r§eChill");
			itemLore.add("§7Compétition");
		}
		else {
			item = new ItemStack(Material.NETHERITE_BLOCK);
			meta = item.getItemMeta();
			meta.setDisplayName("§b§lAffichage des défis");
			itemLore.add("§7Chill");
			itemLore.add("§6§l>> §r§eCompétition");
		}
		meta.setLore(itemLore);
		item.setItemMeta(meta);
		settingsGui.setItem(11,item);
			
		item = new ItemStack(Material.CAULDRON);
		meta = item.getItemMeta();
		meta.setDisplayName("§b§lDifficulté de la grille");
		itemLore = new ArrayList<>();
		itemLore.add("§7>> Clique pour modifier");
		meta.setLore(itemLore);
		item.setItemMeta(meta);
		settingsGui.setItem(12, item);
		
		item = new ItemStack(Material.CRAFTING_TABLE);
		meta = item.getItemMeta();
		meta.setDisplayName("§b§lMode de jeu");
		itemLore = new ArrayList<>();
		if (game.modeJeu == "Classic") {
			itemLore.add("§6§l>> §r§eClassic");
			itemLore.add("§7Duel");
			itemLore.add("§7Handicap");
		}
		else if (game.modeJeu == "Duel") {
			itemLore.add("§7Classic");
			itemLore.add("§6§l>> §r§eDuel");
			itemLore.add("§7Handicap");
		}
		else {
			itemLore.add("§7Classic");
			itemLore.add("§7Duel");
			itemLore.add("§6§l>> §r§eHandicap");
		}
		meta.setLore(itemLore);
		item.setItemMeta(meta);
		settingsGui.setItem(13,item);
		
		item = new ItemStack(Material.REDSTONE);
		meta = item.getItemMeta();
		meta.setDisplayName("§b§lCondition de victoire");
		itemLore = new ArrayList<>();
		itemLore.add("§7>> Clique pour modifier");
		meta.setLore(itemLore);
		item.setItemMeta(meta);
		settingsGui.setItem(14,item);
		
		if (game.eventDefiBonus == "On") {
			item = new ItemStack(Material.GLOWSTONE);
			meta = item.getItemMeta();
			meta.setDisplayName("§b§lEvent défis bonus");
			itemLore = new ArrayList<>();
			itemLore.add("§e§lOn §r§7/ Off");	
		}
		else {
			item = new ItemStack(Material.REDSTONE_LAMP);
			meta = item.getItemMeta();
			meta.setDisplayName("§b§lEvent défis bonus");
			itemLore = new ArrayList<>();
			itemLore.add("§7On /§e§l Off");	
		}
		meta.setLore(itemLore);
		item.setItemMeta(meta);
		settingsGui.setItem(15,item);
		
		ItemStack resetBlock = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta resetMeta = resetBlock.getItemMeta();
		resetMeta.setDisplayName("§c§lReset");
		resetBlock.setItemMeta(resetMeta);
		settingsGui.setItem(16,resetBlock);
		
	p.openInventory(settingsGui);
	}
	
	public void openTeamsSettingsGui(Player p){
		Inventory teamsSettingsGui = Bukkit.createInventory(p, 27 ,"§3§lParamètres teams");
		ItemStack plus = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1NmJjMTI0NGZjZmY5OTM0NGYxMmFiYTQyYWMyM2ZlZTZlZjZlMzM1MWQyN2QyNzNjMTU3MjUzMWYifX19");
		ItemMeta plusMeta = plus.getItemMeta();
		plusMeta.setDisplayName("§a+");
		plus.setItemMeta(plusMeta);
		teamsSettingsGui.setItem(3, plus);
		teamsSettingsGui.setItem(5,plus);
		ItemStack moins = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU0YjhiOGQyMzYyYzg2NGUwNjIzMDE0ODdkOTRkMzI3MmE2YjU3MGFmYmY4MGMyYzViMTQ4Yzk1NDU3OWQ0NiJ9fX0=");
		ItemMeta moinsMeta = moins.getItemMeta();
		moinsMeta.setDisplayName("§c-");
		moins.setItemMeta(moinsMeta);
		teamsSettingsGui.setItem(21, moins);
		teamsSettingsGui.setItem(23,moins);
		ItemStack nombreTeams = new ItemStack(Material.DIAMOND_HORSE_ARMOR, game.teams.nombreTeams);
		ItemMeta nombreTeamsMeta = nombreTeams.getItemMeta();
		nombreTeamsMeta.setDisplayName("§bNombre de teams");
		nombreTeams.setItemMeta(nombreTeamsMeta);
		teamsSettingsGui.setItem(12,nombreTeams);
		ItemStack nombreJoueursTeams = new ItemStack(Material.PUFFERFISH, game.teams.nombreJoueursParTeams);
		ItemMeta nombreJoueursTeamsMeta = nombreJoueursTeams.getItemMeta();
		nombreJoueursTeamsMeta.setDisplayName("§bNombre de joueurs");
		nombreJoueursTeams.setItemMeta(nombreJoueursTeamsMeta);
		teamsSettingsGui.setItem(14,nombreJoueursTeams);
		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.setDisplayName("§cRetour");
		back.setItemMeta(backMeta);
		teamsSettingsGui.setItem(26, back);
		p.openInventory(teamsSettingsGui);
	}
	
	public void openDiffGui(Player p){
		Inventory diffGui = Bukkit.createInventory(p, 27, "§3§lParamètres grille");
		ItemStack plus = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1NmJjMTI0NGZjZmY5OTM0NGYxMmFiYTQyYWMyM2ZlZTZlZjZlMzM1MWQyN2QyNzNjMTU3MjUzMWYifX19");
		ItemMeta plusMeta = plus.getItemMeta();
		plusMeta.setDisplayName("§a+");
		plus.setItemMeta(plusMeta);
		diffGui.setItem(1, plus);
		diffGui.setItem(3,plus);
		diffGui.setItem(5, plus);
		diffGui.setItem(7,plus);
		ItemStack moins = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU0YjhiOGQyMzYyYzg2NGUwNjIzMDE0ODdkOTRkMzI3MmE2YjU3MGFmYmY4MGMyYzViMTQ4Yzk1NDU3OWQ0NiJ9fX0=");
		ItemMeta moinsMeta = moins.getItemMeta();
		moinsMeta.setDisplayName("§c-");
		moins.setItemMeta(moinsMeta);
		diffGui.setItem(19, moins);
		diffGui.setItem(21,moins);;
		diffGui.setItem(23, moins);
		diffGui.setItem(25,moins);
		ItemStack easy = new ItemStack(Material.STRUCTURE_VOID);
		ItemStack medium = new ItemStack(Material.STRUCTURE_VOID);
		ItemStack hard = new ItemStack(Material.STRUCTURE_VOID);
		ItemStack extreme = new ItemStack(Material.STRUCTURE_VOID);
		if (game.defis.easy > 0) {
			easy = new ItemStack(Material.COAL, game.defis.easy);
		}
		if (game.defis.medium > 0) {
			medium = new ItemStack(Material.COPPER_INGOT, game.defis.medium);
		}
		if (game.defis.hard > 0) {
			hard = new ItemStack(Material.AMETHYST_SHARD, game.defis.hard);
		}
		if (game.defis.extreme > 0){
			extreme = new ItemStack(Material.NETHERITE_SCRAP, game.defis.extreme);
		}
		ItemMeta easyMeta = easy.getItemMeta();
		easyMeta.setDisplayName("§8Défi(s) facile(s)");
		ArrayList<String> easyLore = new ArrayList<>();
		easyLore.add("§7[ "+ game.defis.easy + " / "+ game.defis.nbreEasy+" ]");
		easyMeta.setLore(easyLore);
		easy.setItemMeta(easyMeta);
		ItemMeta mediumMeta = medium.getItemMeta();
		mediumMeta.setDisplayName("§6Défi(s) moyen(s)");
		ArrayList<String> mediumLore = new ArrayList<>();
		mediumLore.add("§7[ "+ game.defis.medium + " / "+ game.defis.nbreMedium+" ]");
		mediumMeta.setLore(mediumLore);
		medium.setItemMeta(mediumMeta);
		ItemMeta hardMeta = hard.getItemMeta();
		hardMeta.setDisplayName("§dDéfi(s) dur(s)");
		ArrayList<String> hardLore = new ArrayList<>();
		hardLore.add("§7[ "+ game.defis.hard + " / "+ game.defis.nbreHard+" ]");
		hardMeta.setLore(hardLore);
		hard.setItemMeta(hardMeta);
		ItemMeta extremeMeta = extreme.getItemMeta();
		extremeMeta.setDisplayName("§4Défi(s) extrême(s)");
		ArrayList<String> extremeLore = new ArrayList<>();
		extremeLore.add("§7[ "+ game.defis.extreme + " / "+ game.defis.nbreExtreme+" ]");
		extremeMeta.setLore(extremeLore);
		extreme.setItemMeta(extremeMeta);
		diffGui.setItem(10,easy);
		diffGui.setItem(12,medium);
		diffGui.setItem(14,hard);
		diffGui.setItem(16,extreme);
		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.setDisplayName("§cRetour");
		back.setItemMeta(backMeta);
		diffGui.setItem(26, back);
		p.openInventory(diffGui);
	}
	
	public void openWinGui(Player p){
		Inventory winGui = Bukkit.createInventory(p, 27,"§3§lParamètres victoire");
		ItemStack plus = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1NmJjMTI0NGZjZmY5OTM0NGYxMmFiYTQyYWMyM2ZlZTZlZjZlMzM1MWQyN2QyNzNjMTU3MjUzMWYifX19");
		ItemMeta plusMeta = plus.getItemMeta();
		plusMeta.setDisplayName("§a+");
		plus.setItemMeta(plusMeta);
		winGui.setItem(3, plus);
		ItemStack moins = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU0YjhiOGQyMzYyYzg2NGUwNjIzMDE0ODdkOTRkMzI3MmE2YjU3MGFmYmY4MGMyYzViMTQ4Yzk1NDU3OWQ0NiJ9fX0=");
		ItemMeta moinsMeta = moins.getItemMeta();
		moinsMeta.setDisplayName("§c-");
		moins.setItemMeta(moinsMeta);
		winGui.setItem(21, moins);
		ItemStack nombreBingos = new ItemStack(Material.SPECTRAL_ARROW, game.nombreBingos);
		ItemMeta nombreBingosMeta = nombreBingos.getItemMeta();
		nombreBingosMeta.setDisplayName("§b§lNombre de bingos");
		nombreBingos.setItemMeta(nombreBingosMeta);
		winGui.setItem(12, nombreBingos);
		ItemStack winMode = new ItemStack(Material.TARGET);
		ItemMeta winMeta = winMode.getItemMeta();
		winMeta.setDisplayName("§b§lMode de victoire");
		ArrayList<String> winLore = new ArrayList<>();
		if (game.modeVictoire == "Bingo"){
			winLore.add("§eBingos§7 / Défis");
		}
		else {
			winLore.add("§7Bingos /§e Défis");
		}
		winMeta.setLore(winLore);
		winMode.setItemMeta(winMeta);
		winGui.setItem(14,winMode);
		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.setDisplayName("§cRetour");
		back.setItemMeta(backMeta);
		winGui.setItem(26, back);
		p.openInventory(winGui);
	}
	
	@EventHandler
	public void clickItems(PlayerInteractEvent e) {
		if (game.InSetup == true) {
			if (e.getMaterial() == Material.COMPARATOR) {
				openSettingsGui(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		if (e.getCurrentItem() != null) {
			if (game.InSetup == true) {
				if (e.getView().getTitle().equalsIgnoreCase("§3§lParamètres")) {
					Material currentItem = e.getCurrentItem().getType();
					Player player = (Player) e.getWhoClicked();
					if (currentItem == Material.IRON_BLOCK) {
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_LIBRARIAN, 500.0f, 1.0f);
						game.modeAffichage = "Compétition";
						openSettingsGui(player);
					} else if (currentItem == Material.CRAFTING_TABLE) {
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_LIBRARIAN, 500.0f, 1.0f);
						if (game.modeJeu == "Classic") {
							game.modeJeu = "Duel";
							openSettingsGui(player);
						} else if (game.modeJeu == "Duel") {
							game.modeJeu = "Handicap";
							openSettingsGui(player);
						} else {
							game.modeJeu = "Classic";
							openSettingsGui(player);
						}
					} else if (currentItem == Material.WHITE_BANNER) {
						openTeamsSettingsGui((Player) e.getWhoClicked());
					} else if (currentItem == Material.REDSTONE_LAMP) {
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_LIBRARIAN, 500.0f, 1.0f);
						game.eventDefiBonus = "On";
						openSettingsGui(player);

					} else if (currentItem == Material.NETHERITE_BLOCK) {
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_LIBRARIAN, 500.0f, 1.0f);
						game.modeAffichage = "Chill";
						openSettingsGui(player);
					} else if (currentItem == Material.GLOWSTONE) {
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_LIBRARIAN, 500.0f, 1.0f);
						game.eventDefiBonus = "Off";
						openSettingsGui(player);
					} else if (currentItem == Material.CAULDRON) {
						openDiffGui(player);
					} else if (currentItem == Material.REDSTONE) {
						openWinGui(player);
					} else if (currentItem == Material.REDSTONE_BLOCK) {
						game.resetSettings();
						player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 500.0f, 1.0f);
						openSettingsGui(player);
					}
				} else if (e.getView().getTitle().equalsIgnoreCase("§3§lParamètres Teams")) {
					Material currentItem = e.getCurrentItem().getType();
					Player player = (Player) e.getWhoClicked();
					if (currentItem == Material.BARRIER) {
						openSettingsGui(player);
					} else if (e.getSlot() == 3) {
						if (game.teams.nombreTeams < 6) {
							game.teams.changeNumberOfTeams(game.teams.nombreTeams + 1);
							openTeamsSettingsGui(player);
						}
					} else if (e.getSlot() == 5) {
						if (game.teams.nombreJoueursParTeams < 10) {
							game.teams.changeNumberJoueursParTeams(game.teams.nombreJoueursParTeams + 1);
							if (game.teams.nombreTeams == 1) {
								game.defis.presetOnePlayer();
							} else if (game.teams.nombreJoueursParTeams == 2) {
								game.defis.presetTwoPlayers();
							} else if (game.teams.nombreJoueursParTeams == 3) {

								game.defis.presetThreePlayers();
							} else if (game.teams.nombreJoueursParTeams == 4) {
								game.defis.presetFourPlayers();
							}
							openTeamsSettingsGui(player);
						}
					} else if (e.getSlot() == 21) {
						if (game.teams.nombreTeams > 2) {
							game.teams.changeNumberOfTeams(game.teams.nombreTeams - 1);
							openTeamsSettingsGui(player);
						}
					} else if (e.getSlot() == 23) {
						if (game.teams.nombreJoueursParTeams > 1) {
							game.teams.changeNumberJoueursParTeams(game.teams.nombreJoueursParTeams - 1);
							if (game.teams.nombreJoueursParTeams == 1) {
								game.defis.presetOnePlayer();
							} else if (game.teams.nombreJoueursParTeams == 2) {
								game.defis.presetTwoPlayers();
							} else if (game.teams.nombreJoueursParTeams == 3) {
								game.defis.presetThreePlayers();
							} else if (game.teams.nombreJoueursParTeams == 4) {
								game.defis.presetFourPlayers();
							}
							openTeamsSettingsGui(player);
						}
					}
				} else if (e.getView().getTitle().equalsIgnoreCase("§3§lParamètres grille")) {
					Material currentItem = e.getCurrentItem().getType();
					Player player = (Player) e.getWhoClicked();
					int cursor = e.getSlot();
					if (currentItem == Material.BARRIER) {
						openSettingsGui(player);
					} else if (cursor == 1) {
						if (game.defis.verifNbMaxDefi()) {
							game.defis.easy++;
							openDiffGui(player);
						}
					} else if (cursor == 3) {
						if (game.defis.verifNbMaxDefi()) {
							game.defis.medium++;
							openDiffGui(player);
						}
					} else if (cursor == 5) {
						if (game.defis.verifNbMaxDefi()) {
							if (game.defis.hard < game.defis.nbreHard) {
								game.defis.hard++;
								openDiffGui(player);
							}
						}
					} else if (cursor == 7) {
						if (game.defis.verifNbMaxDefi()) {
							if (game.defis.extreme < game.defis.nbreExtreme) {
								game.defis.extreme++;
								openDiffGui(player);
							}
							
						}
					} else if (cursor == 19) {
						if (game.defis.easy > 0) {
							game.defis.easy--;
							openDiffGui(player);
						}
					} else if (cursor == 21) {
						if (game.defis.medium > 0) {
							game.defis.medium--;
							openDiffGui(player);
						}
					} else if (cursor == 23) {
						if (game.defis.hard > 0) {
							game.defis.hard--;
							openDiffGui(player);
						}
					} else if (cursor == 25) {
						if (game.defis.extreme > 0) {
							game.defis.extreme--;
							openDiffGui(player);
						}
					}
				} else if (e.getView().getTitle().equalsIgnoreCase("§3§lParamètres victoire")) {
					Material currentItem = e.getCurrentItem().getType();
					Player player = (Player) e.getWhoClicked();
					int cursor = e.getSlot();
					if (currentItem == Material.BARRIER) {
						openSettingsGui(player);
					} else if (currentItem == Material.TARGET) {
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_LIBRARIAN, 500.0f, 1.0f);
						if (game.modeVictoire == "Bingo") {
							game.modeVictoire = "Défis";
							openWinGui(player);
						} else {
							game.modeVictoire = "Bingo";
							openWinGui(player);
						}
					} else if (cursor == 3) {
						if (!(game.nombreBingos == 10)) {
							game.nombreBingos++;
							openWinGui(player);
						}
					} else if (cursor == 21) {
						if (!(game.nombreBingos == 1)) {
							game.nombreBingos--;
							openWinGui(player);
						}
					}
				}
			}
		}
	}
}
