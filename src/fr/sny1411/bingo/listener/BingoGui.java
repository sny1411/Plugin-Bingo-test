package fr.sny1411.bingo.listener;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.sny1411.bingo.utils.Game;


public class BingoGui implements Listener {
    private Inventory inv; 
    private Game game;
    public BingoGui(Game game) {
		this.game = game;
	}

    public void openGui(Player p, String teamPlayer) {
    	String spectatorLookTeam = null;
    	if (teamPlayer.equalsIgnoreCase("Spectator") || p.getGameMode().equals(GameMode.SPECTATOR)) {
    		String team = game.spectatorInBingoTeams.get(p);
    		if (team != null) {
    			spectatorLookTeam = team;
    		} else {
    			spectatorLookTeam = "Orange";
    			game.spectatorInBingoTeams.put(p, "Orange");
    		}
    	}
    	inv = Bukkit.createInventory(p, 45, "§3§lBINGO");
    	ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemMeta meta = item.getItemMeta();
    	meta.setDisplayName(" ");
    	item.setItemMeta(meta);
    	for (int i = 0; i < 45; i++) {
			inv.setItem(i, item);
		}
    	ArrayList<Material> itemsTeams = new ArrayList<Material>(Arrays.asList(Material.ORANGE_BANNER,Material.RED_BANNER,Material.PURPLE_BANNER,Material.PINK_BANNER,Material.LIME_BANNER,Material.LIGHT_BLUE_BANNER));
    	ArrayList<Material> itemTeamsSpectator = new ArrayList<>(Arrays.asList(Material.ORANGE_CONCRETE,Material.RED_CONCRETE,Material.PURPLE_CONCRETE,Material.PINK_CONCRETE,Material.LIME_CONCRETE,Material.LIGHT_BLUE_CONCRETE));
		ArrayList<String> nameTeams = new ArrayList<String>(Arrays.asList("§6Orange","§cRouge", "§5Violet", "§dRose", "§aVert", "§bBleu"));
		ArrayList<ItemStack> teamsList = new ArrayList<ItemStack>();
    	for (int i = 0; i < 6; i++) {
    		if (i < game.teams.nombreTeams) {
    			ItemStack itemTeam;
    			if (spectatorLookTeam == null) {
    				itemTeam = new ItemStack(itemsTeams.get(i));
    			} else {
    				itemTeam = new ItemStack(itemTeamsSpectator.get(i));
    			}

    			ItemMeta itemTeamMeta = itemTeam.getItemMeta();
    			if (spectatorLookTeam != null) {
    				if (nameTeams.get(i).substring(2).equalsIgnoreCase(spectatorLookTeam)) {
    					itemTeamMeta.addEnchant(Enchantment.DURABILITY, 5, true);
    	    			itemTeamMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    				}
    			}
    			itemTeamMeta.setDisplayName(nameTeams.get(i));
    			ArrayList<String> loreTeams = new ArrayList<String>();
    			if (game.modeAffichage.equalsIgnoreCase("Chill")) {
    				loreTeams.add("§9Défi(s) réalisé(s): §f" + game.teams.nbreDefiValid.get(game.teams.colorTeams.get(i)).toString());
    			} else {
    				loreTeams.add("§9Défi(s) réalisé(s): §f§k!!");
    			}
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
    		ItemStack itemGrille = game.grilleBingo.get(index);
    		if (spectatorLookTeam == null) {
    			if (game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) {
        			itemGrille = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        			ItemMeta metaGrille = itemGrille.getItemMeta();
        			metaGrille.setDisplayName(" ");
        			itemGrille.setItemMeta(metaGrille);
        		}
    		} else {
    			if (game.teams.defiValid.get(spectatorLookTeam).get(itemGrille.getItemMeta().getDisplayName())) {
        			itemGrille = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        			ItemMeta metaGrille = itemGrille.getItemMeta();
        			metaGrille.setDisplayName(" ");
        			itemGrille.setItemMeta(metaGrille);
        		}
    		}
    		
    		inv.setItem(i, itemGrille);
    		index++;
    	}
    	p.openInventory(inv);
    	if (!teamPlayer.equalsIgnoreCase(teamPlayer)) {
    		game.teams.playersOnBingoGui.get(teamPlayer).add(p);
    	}
    }
}

    