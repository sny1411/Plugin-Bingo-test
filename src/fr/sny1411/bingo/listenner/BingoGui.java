package fr.sny1411.bingo.listenner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.sny1411.bingo.utils.Game;


public class BingoGui implements Listener {
    private Inventory inv; 
    private Game game;
    public BingoGui(Game game) {
		this.game = game;
	}

	private ItemStack createItemDefi(int i) {
    	List<String> listeDefis = game.defis.defi.get(i);
    	ItemStack item = game.defis.grilleDisplay.get(listeDefis);
    	ItemMeta itemMeta = item.getItemMeta();
    	itemMeta.setDisplayName(listeDefis.get(0));
    	ArrayList<String> itemLore = new ArrayList<>();
    	itemLore.add(listeDefis.get(1));
    	itemMeta.setLore(itemLore);
    	item.setItemMeta(itemMeta);
    	return item;
    	}
    
    public void openGui(Player p) {
    	inv = Bukkit.createInventory(p, 45, "§3§lBINGO");
    	ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemMeta meta = item.getItemMeta();
    	meta.setDisplayName("");
    	item.setItemMeta(meta);
    	for (int i = 0; i < 45; i++) {
			inv.setItem(i, item);
		}
    	ArrayList<Material> itemsTeams = new ArrayList<Material>(Arrays.asList(Material.ORANGE_BANNER,Material.RED_BANNER,Material.PURPLE_BANNER,Material.PINK_BANNER,Material.LIME_BANNER,Material.LIGHT_BLUE_BANNER));
		ArrayList<String> nameTeams = new ArrayList<String>(Arrays.asList("§6Orange","§cRouge", "§5Violet", "§dRose", "§aVert", "§bBleu"));
		ArrayList<ItemStack> teamsList = new ArrayList<ItemStack>();
    	for (int i = 0; i < 5; i++) {
    		if (i < game.teams.nombreTeams) {
    			ItemStack itemTeam = new ItemStack(itemsTeams.get(i));
    			ItemMeta itemTeamMeta = itemTeam.getItemMeta();
    			itemTeamMeta.setDisplayName(nameTeams.get(i));
    			ArrayList<String> loreTeams = new ArrayList<String>();
    			loreTeams.add("§9Défi(s) réalisé(s): §f" + game.teams.nbreDefiValid.get(game.teams.colorTeams.get(i)).toString());
    			for (int j = 0; j < game.teams.nombreJoueursParTeams; j++) {
    				if (j < game.teams.teamsHash.get(game.teams.colorTeams.get(i)).size()) {
						loreTeams.add("§7§o- " + game.teams.teamsHash.get(game.teams.colorTeams.get(i)).get(j).getDisplayName());
					} else {
						loreTeams.add("§7§o- ");
					}
				}
    			itemTeamMeta.setLore(loreTeams);
    			itemTeam.setItemMeta(itemTeamMeta);
    			teamsList.add(itemTeam);
    		} else {
    			teamsList.add(item);
    		}
    		switch (i) {
            case 0:
                inv.setItem(18, teamsList.get(i));
                break;
            case 1:
                inv.setItem(19, teamsList.get(i));
                break;
            case 2:
                inv.setItem(9, teamsList.get(i));
                break;
            case 3:
                inv.setItem(10, teamsList.get(i));
                break;
            case 4:
                inv.setItem(27, teamsList.get(i));
                break;
            case 5:
                inv.setItem(28, teamsList.get(i));
                break;
            }
    	}
    	int compteurItem = 0;
    	List<ItemStack> defiFinaux = new ArrayList<ItemStack>();
    	for (int i = 0; i<=24; i++) {
    		List<String> defi = game.defis.defi.get(compteurItem);
    		String difficultyDefi = defi.get(2);
    		boolean defiIsLibre = verifDiff(difficultyDefi);
    		while (defiIsLibre==false) {
				compteurItem++;
				defi = game.defis.defi.get(compteurItem);
	    		difficultyDefi = defi.get(2);
	    		defiIsLibre = verifDiff(difficultyDefi);
	    	}
    		if (defiIsLibre==true) {
                defiFinaux.add(createItemDefi(compteurItem));
    		}
    		compteurItem++;
		}
		Collections.shuffle(defiFinaux);
		int index = 0;
    	for (int i = 3; i < 44; i++) {
    		if (i == 8) {
    			i=12;
    		}
    		else if (i == 17) {
    			i=21;
    		}
    		else if (i == 26) {
    			i=30;
    		}
    		else if (i == 35) {
    			i=39;
    		}
    		inv.setItem(i, defiFinaux.get(index));
    		index++;
    	}
    	p.openInventory(inv);
    }
    
    public boolean verifDiff(String diff) {
    	if (diff.equalsIgnoreCase("easy")) {
    		if (game.defis.easy != 0) {
				game.defis.easy-=1;
    			return true;			
    		}
    	}
    	else if (diff.equalsIgnoreCase("medium")) {
    		if (game.defis.medium != 0) {
				game.defis.medium-=1;
    			return true;			
    		}
    	}
    	else if (diff.equalsIgnoreCase("hard")) {
    		if (game.defis.hard != 0) {
				game.defis.hard-=1;
    			return true;			
    		}
    	}
    	else {
    		if (game.defis.extreme != 0) {
				game.defis.extreme-=1;
    			return true;			
    		}
    	}
    	return false;
    }
}

    