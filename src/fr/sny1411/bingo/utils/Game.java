package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Game {
	public List<ItemStack> grilleBingo = new ArrayList<ItemStack>(); 
	public String modeAffichage = "Chill";
	public String modeJeu = "Classic";
	public String eventDefiBonus = "Off";
	public String modeVictoire = "Bingo";
	public int nombreBingos = 1;
	public boolean InSetup = false;
	public boolean gameLaunch = false;
	public boolean DamagePlayer = true;
	public Teams teams;
	private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	public Defis defis;
	
	public void setClassTeams(Teams teams) {
		this.teams = teams;
	}
	
	public void setClassDefis(Defis defis) {
		this.defis = defis;
	}

	public void createGrille() {
		int compteurItem = 0;
		for (int i = 0; i <= 24; i++) {
			List<String> defi = defis.defi.get(compteurItem);
			String difficultyDefi = defi.get(2);
			boolean defiIsLibre = verifDiff(difficultyDefi);
			while (defiIsLibre == false) {
				compteurItem++;
				defi = defis.defi.get(compteurItem);
				difficultyDefi = defi.get(2);
				defiIsLibre = verifDiff(difficultyDefi);
			}
			if (defiIsLibre) {
				grilleBingo.add(createItemDefi(compteurItem));
			}
			compteurItem++;
		}
		Collections.shuffle(grilleBingo);
	}

	private ItemStack createItemDefi(int i) {
    	List<String> listeDefis = defis.defi.get(i);
    	ItemStack item = defis.grilleDisplay.get(listeDefis);
    	ItemMeta itemMeta = item.getItemMeta();
    	itemMeta.setDisplayName(listeDefis.get(0));
    	ArrayList<String> itemLore = new ArrayList<>();
    	if (listeDefis.get(1).length() <= 30) {
    		itemLore.add(listeDefis.get(1));
    		itemMeta.setLore(itemLore);
    		item.setItemMeta(itemMeta);
    	} else {
    		String[] splitLore = listeDefis.get(1).split("(?<=\\G.{30}");
    		for (int i1 = 0; i1 > splitLore.length-1; i1++) {
    			while (splitLore[i1].substring(splitLore[i1].length()-1) != " " && splitLore[i1+1].substring(0, 1) != " ") {
    				String letter = splitLore[i1].substring(splitLore[i1].length()-1);
    				splitLore[i1] = splitLore[i1].substring(0, splitLore[i1].length()-1);
    				splitLore[i1+1] = letter + splitLore[i1+1];
    			}
    			itemLore.add(splitLore[i1]);
    		}
    		itemMeta.setLore(itemLore);
    		item.setItemMeta(itemMeta);
    	}
    	return item;
    }

	public boolean verifDiff(String diff) {
    	if (diff.equalsIgnoreCase("easy")) {
    		if (defis.easy != 0) {
				defis.easy-=1;
    			return true;			
    		}
    	}
    	else if (diff.equalsIgnoreCase("medium")) {
    		if (defis.medium != 0) {
				defis.medium-=1;
    			return true;			
    		}
    	}
    	else if (diff.equalsIgnoreCase("hard")) {
    		if (defis.hard != 0) {
				defis.hard-=1;
    			return true;			
    		}
    	}
    	else {
    		if (defis.extreme != 0) {
				defis.extreme-=1;
    			return true;			
    		}
    	}
    	return false;
    }

	public void setup() {
		teams.createTeams();
		this.InSetup = true;
		this.DamagePlayer = false;
		inventorySelectTeams();
		Bukkit.dispatchCommand(console, "fill -20 200 -20 20 200 20 white_stained_glass replace");
		Bukkit.dispatchCommand(console, "fill -19 200 -19 19 200 19 barrier replace");
		Bukkit.dispatchCommand(console, "fill -20 201 -20 -20 203 20 cyan_stained_glass_pane replace");
		Bukkit.dispatchCommand(console, "fill -20 201 -20 20 203 -20 cyan_stained_glass_pane replace");
		Bukkit.dispatchCommand(console, "fill 20 201 20 -20 203 20 cyan_stained_glass_pane replace");
		Bukkit.dispatchCommand(console, "fill 20 201 20 20 203 -20 cyan_stained_glass_pane replace");
		Bukkit.dispatchCommand(console, "tp @a 0 204 0");
		Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
		Bukkit.dispatchCommand(console, "gamerule announceAdvancements false");
		Bukkit.dispatchCommand(console, "advancement revoke @a everything");
	}
	
	public void inventorySelectTeams() {
		Bukkit.dispatchCommand(console, "clear @a");
		List<Player> listOfPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
		for (Player player : listOfPlayers) {
			if (player.isOp()) {
				ItemStack itemSettings = new ItemStack(Material.COMPARATOR);
				ItemMeta metaSettings = itemSettings.getItemMeta();
				metaSettings.setDisplayName("§2◈ §a§lParamètres §r§2◈");
				itemSettings.setItemMeta(metaSettings);
				player.getInventory().setItem(0, itemSettings);
			}
			player.setPlayerListName(player.getName());
			Inventory playerInventory = player.getInventory();
			ItemStack itemTeamsSelector = new ItemStack(Material.COMPASS);
			ItemMeta metaTeamsSelector = itemTeamsSelector.getItemMeta();
			metaTeamsSelector.setDisplayName("§2◈ §a§lTeams §r§2◈");
			itemTeamsSelector.setItemMeta(metaTeamsSelector);
			playerInventory.setItem(4, itemTeamsSelector);
		}
	}
	
	public void inventorySelectTeams(Player player) {
		player.setPlayerListName(player.getName());
		Bukkit.dispatchCommand(console, "clear " + player.getName().toString());
		ItemStack itemTeamsSelector = new ItemStack(Material.COMPASS);
		ItemMeta metaTeamsSelector = itemTeamsSelector.getItemMeta();
		metaTeamsSelector.setDisplayName("§2◈ §a§lTeams §r§2◈");
		itemTeamsSelector.setItemMeta(metaTeamsSelector);
		if (player.isOp()) {
			ItemStack itemSettings = new ItemStack(Material.COMPARATOR);
			ItemMeta metaSettings = itemSettings.getItemMeta();
			metaSettings.setDisplayName("§2◈ §a§lParamètres §r§2◈");
			itemSettings.setItemMeta(metaSettings);
			player.getInventory().setItem(0, itemSettings);
		}
		player.getInventory().setItem(4, itemTeamsSelector);
	}
	
	public void resetSettings() {
		this.modeVictoire = "Bingo";
		this.nombreBingos = 1;
		this.eventDefiBonus = "Off";
		this.modeJeu = "Classic";
		this.modeAffichage = "Chill";
		this.teams.resetTeam();
		this.defis.resetDefi();
		this.defis.presetTwoPlayers();
	}
	
	public void resetGame() {
		this.grilleBingo = new ArrayList<ItemStack>();
		this.gameLaunch = false;
		this.InSetup = false; 
		this.DamagePlayer = true;
	}
}
