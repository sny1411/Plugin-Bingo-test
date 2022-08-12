package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.sny1411.bingo.listener.DefiListener;
import net.md_5.bungee.api.ChatColor;

public class EventDefisBonus {
	public int nombreEvent;
	public List<Integer> timeEvents = new ArrayList<>();
	public List<List<String>> listDefis = new ArrayList<>();
	public List<List<String>> defisEnCours = new ArrayList<>();
	public List<String> rewards = new ArrayList<>(Arrays.asList("SPEED","HASTE","RESISTANCE","DEFI"));
	private Game game;
	private DefiListener defiListener;
	
	public EventDefisBonus(Game game, DefiListener defiListener) {
		this.game = game;
		this.defiListener = defiListener;
	}

	public void initEvent() {
		nombreEvent = (int)(Math.random() * ((4 - 1) + 1)) + 1;
		nombreEvent = 4; // à retirer 
		for (int i = 0; i < nombreEvent; i++) {
			int timeEvent = (int)(Math.random() * ((90 - 15) + 1)) + 15;
			timeEvents.add(timeEvent);
			
			List<String> defi = game.defis.defi.get(i);
			Boolean add = false;
			int compteurItem = 1;
			while (add == false) {
				add = true;
				for (ItemStack itemDefi : game.grilleBingo) {
					if (itemDefi.getItemMeta().getDisplayName().equalsIgnoreCase(defi.get(0))) {
						add = false;
					}
				}
				if (defi.get(2).equalsIgnoreCase("extreme")) {
					add = false;
				}
				for (List<String> defiListe : listDefis) {
					if (defiListe.equals(defi)) {
						add = false;
					}
				}
				if (add == false) {
					compteurItem++;
					defi = game.defis.defi.get(i + compteurItem);
				}
			}
			listDefis.add(defi);
			
		}
		Collections.sort(timeEvents);
		System.out.println(timeEvents);
		System.out.println(listDefis);
		
	}
	
	public void rewards(String niveauDefi,Player player) {
		int i = (int)(Math.random() * (((rewards.size()-1)) + 1));
		String reward = rewards.get(i);
		rewards.remove(i);
		
		switch (reward) {
		case "SPEED":
			switch (niveauDefi) {
			case "I":
				player.sendMessage("§8§l≫ §r§7Vous recevez le bonus §bSpeed I");
				PotionEffect potion = new PotionEffect(PotionEffectType.SPEED, 144000, 0);
				player.addPotionEffect(potion);
				break;
			
			case "II":
				String teamPlayer = game.teams.findTeamPlayer(player);
				for (Player playerTeam : game.teams.teamsHash.get(teamPlayer)) {
						playerTeam.sendMessage("§8§l≫ §r§7Votre équipe reçoit le bonus §bSpeed I");
						PotionEffect potion4 = new PotionEffect(PotionEffectType.SPEED, 144000, 0);
						playerTeam.addPotionEffect(potion4);
				}
				break;
			
			case "III":
				String teamPlayer2 = game.teams.findTeamPlayer(player);
				for (Player playerTeam : game.teams.teamsHash.get(teamPlayer2)) {
					if (playerTeam.equals(player)) {
						playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §bSpeed II §7 et votre équipe §bSpeed I");
						PotionEffect potion3 = new PotionEffect(PotionEffectType.SPEED, 144000, 1);
						playerTeam.addPotionEffect(potion3);
					} else {
						playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §bSpeed I");
						PotionEffect potion4 = new PotionEffect(PotionEffectType.SPEED, 144000, 0);
						playerTeam.addPotionEffect(potion4);
					}
				}
				break;

			default:
				break;
			}
			break;
			
		case "HASTE":
			switch (niveauDefi) {
			case "I":
				player.sendMessage("§8§l≫ §r§7Vous recevez le bonus §eHaste I");
				PotionEffect potion = new PotionEffect(PotionEffectType.FAST_DIGGING, 144000, 0);
				player.addPotionEffect(potion);
				break;
			
			case "II":
				String teamPlayer = game.teams.findTeamPlayer(player);
				for (Player playerTeam : game.teams.teamsHash.get(teamPlayer)) {
						playerTeam.sendMessage("§8§l≫ §r§7Votre équipe reçoit le bonus §bHaste I");
						PotionEffect potion4 = new PotionEffect(PotionEffectType.FAST_DIGGING, 144000, 0);
						playerTeam.addPotionEffect(potion4);
				}
			
			case "III":
				String teamPlayer2 = game.teams.findTeamPlayer(player);
				for (Player playerTeam : game.teams.teamsHash.get(teamPlayer2)) {
					if (playerTeam.equals(player)) {
						playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §eHaste II §7 et votre équipe §eHaste I");
						PotionEffect potion3 = new PotionEffect(PotionEffectType.FAST_DIGGING, 144000, 1);
						playerTeam.addPotionEffect(potion3);
					} else {
						playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §eHaste I");
						PotionEffect potion4 = new PotionEffect(PotionEffectType.FAST_DIGGING, 144000, 0);
						playerTeam.addPotionEffect(potion4);
					}
				}
				break;

			default:
				break;
			}
			break;
			
		case "RESISTANCE":
			switch (niveauDefi) {
			case "I":
				player.sendMessage("§8§l≫ §r§7Vous recevez le bonus §cResistance I");
				PotionEffect potion = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 144000, 0);
				player.addPotionEffect(potion);
				break;
			
			case "II":
				String teamPlayer = game.teams.findTeamPlayer(player);
				for (Player playerTeam : game.teams.teamsHash.get(teamPlayer)) {
						playerTeam.sendMessage("§8§l≫ §r§7Votre équipe reçoit le bonus §bResistance I");
						PotionEffect potion4 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 144000, 0);
						playerTeam.addPotionEffect(potion4);
				}
				break;
			
			case "III":
				String teamPlayer2 = game.teams.findTeamPlayer(player);
				for (Player playerTeam : game.teams.teamsHash.get(teamPlayer2)) {
					if (playerTeam.equals(player)) {
						playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §cResistance II §7 et votre équipe §cResistance I");
						PotionEffect potion3 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 144000, 1);
						playerTeam.addPotionEffect(potion3);
					} else {
						playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §cResistance I");
						PotionEffect potion4 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 144000, 0);
						playerTeam.addPotionEffect(potion4);
					}
				}
				break;

			default:
				break;
			}
			break;
			
		case "DEFI":
			String teamPlayer = game.teams.findTeamPlayer(player);
			for (Player playerTeam : game.teams.teamsHash.get(teamPlayer)) {
				playerTeam.sendMessage("§8§l≫ §r§7Vous recevez le bonus §cDéfi réalisé");
			}
			switch (niveauDefi) {
			case "I":
				Boolean defiReward = false;
				for (ItemStack itemGrille : game.grilleBingo) {
					if (!game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) { // si defi pas valid
						for (List<String> defi : game.defis.defi) {
							if (defi.get(2).equalsIgnoreCase("easy") && !defiReward) {
								game.teams.defiValid.get(teamPlayer).put(defi.get(0),true);
								game.teams.defiDone.get(teamPlayer).put(defi.get(0),true);
								defiListener.afficheValid(player, defi.get(0), false, "0");
					    		int i2 = game.teams.nbreDefiValid.get(teamPlayer);
								game.teams.nbreDefiValid.put(teamPlayer, i2 + 1);
								defiReward = true;
							}
						}
					}
				}
				if (!defiReward) {
					for (ItemStack itemGrille : game.grilleBingo) {
						if (!game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) { // si defi pas valid
							for (List<String> defi : game.defis.defi) {
								if (defi.get(2).equalsIgnoreCase("medium") && !defiReward) {
									game.teams.defiValid.get(teamPlayer).put(defi.get(0),true);
									game.teams.defiDone.get(teamPlayer).put(defi.get(0),true);
									defiListener.afficheValid(player, defi.get(0), false, "0");
									int i2 = game.teams.nbreDefiValid.get(teamPlayer);
									game.teams.nbreDefiValid.put(teamPlayer, i2 + 1);
									defiReward = true;
								}
							}
						}
					}
				}
				if (!defiReward) {
					for (ItemStack itemGrille : game.grilleBingo) {
						if (!game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) { // si defi pas valid
							for (List<String> defi : game.defis.defi) {
								if (defi.get(2).equalsIgnoreCase("hard") && !defiReward) {
									game.teams.defiValid.get(teamPlayer).put(defi.get(0),true);
									game.teams.defiDone.get(teamPlayer).put(defi.get(0),true);
									defiListener.afficheValid(player, defi.get(0), false, "0");
									int i2 = game.teams.nbreDefiValid.get(teamPlayer);
									game.teams.nbreDefiValid.put(teamPlayer, i2 + 1);
									defiReward = true;
								}
							}
						}
					}
				}
				break;
			
			case "II":
				Boolean defiReward2 = false;
				for (ItemStack itemGrille : game.grilleBingo) {
					if (!game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) { // si defi pas valid
						for (List<String> defi : game.defis.defi) {
							if (defi.get(2).equalsIgnoreCase("medium") && !defiReward2) {
								game.teams.defiValid.get(teamPlayer).put(defi.get(0),true);
								game.teams.defiDone.get(teamPlayer).put(defi.get(0),true);
								defiListener.afficheValid(player, defi.get(0), false, "0");
								int i2 = game.teams.nbreDefiValid.get(teamPlayer);
								game.teams.nbreDefiValid.put(teamPlayer, i2 + 1);
								defiReward2 = true;
							}
						}
					}
				}
				if (!defiReward2) {
					for (ItemStack itemGrille : game.grilleBingo) {
						if (!game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) { // si defi pas valid
							for (List<String> defi : game.defis.defi) {
								if (defi.get(2).equalsIgnoreCase("hard") && !defiReward2) {
									game.teams.defiValid.get(teamPlayer).put(defi.get(0),true);
									game.teams.defiDone.get(teamPlayer).put(defi.get(0),true);
									defiListener.afficheValid(player, defi.get(0), false, "0");
									int i2 = game.teams.nbreDefiValid.get(teamPlayer);
									game.teams.nbreDefiValid.put(teamPlayer, i2 + 1);
									defiReward2 = true;
								}
							}
						}
					}
				}
				break;
			
			case "III":
				Boolean defiReward3 = false;
				for (ItemStack itemGrille : game.grilleBingo) {
					if (!game.teams.defiValid.get(teamPlayer).get(itemGrille.getItemMeta().getDisplayName())) { // si defi pas valid
						for (List<String> defi : game.defis.defi) {
							if (defi.get(2).equalsIgnoreCase("hard") && !defiReward3) {
								game.teams.defiValid.get(teamPlayer).put(defi.get(0),true);
								game.teams.defiDone.get(teamPlayer).put(defi.get(0),true);
								defiListener.afficheValid(player, defi.get(0), false, "0");
								int i2 = game.teams.nbreDefiValid.get(teamPlayer);
								game.teams.nbreDefiValid.put(teamPlayer, i2 + 1);
								defiReward3 = true;
							}
						}
					}
				}
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
	}
	
	public void newEvent() {
		List<String> defi = listDefis.get(0);
		int i = 0;
		for (String team : game.teams.colorTeams) {
			if (i < game.teams.nombreTeams) {
				System.out.println(team);
				game.teams.defiValid.get(team).put(defi.get(0),false);
				game.teams.defiDone.get(team).put(defi.get(0),false);
				i++;
			}
		}
		listDefis.remove(0);
		timeEvents.remove(0);
		String niveauDefi = defi.get(2);
		switch (niveauDefi) {
		case "easy":
			niveauDefi = "I";
			break;
		case "medium":
			niveauDefi = "II";
			break;
		case "hard":
			niveauDefi = "III";
			break;
		default:
			break;
		}
		
		Bukkit.broadcastMessage("§7[§eBINGO§7] §fBonus: " + ChatColor.of("#FFBBFF") + defi.get(0).substring(4) + " §f| " + ChatColor.of("#FFBBFF") + defi.get(1).substring(2));
		Bukkit.broadcastMessage("§8≫ §7La première équipe à compléter le défi remportera une §9récompense de §oniveau " + niveauDefi);
		defisEnCours.add(defi);
	}
	
	
}
