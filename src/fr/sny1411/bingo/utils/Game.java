package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.sny1411.bingo.Plugin;
import fr.sny1411.bingo.commands.Result;
import fr.sny1411.bingo.listener.BingoGui;

public class Game {
	public List<ItemStack> grilleBingo = new ArrayList<ItemStack>(); 
	public List<World> listWorld = Bukkit.getWorlds();
	
	public int timeGameHour = 2;
	public int timeGameMinutes = 0;
	public String modeAffichage = "Chill";
	public String modeJeu = "Classic";
	public String eventDefiBonus = "Off";
	public String modeVictoire = "Bingo";
	public Hashtable<String, Integer> caseCacherHandicap = new Hashtable<>();
	public int nombreBingos = 3;
	public boolean InSetup = false;
	public boolean gameLaunch = false;
	public boolean DamagePlayer = true;
	public Hashtable<Player, String> spectatorInBingoTeams = new Hashtable<>();
	public Teams teams;
	private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	public Defis defis;
	public Timer timer;
	public ScoreBoard scoreBoard;
	public Plugin plugin;
	public BingoGui bingoGui;
	public Result result;
	public EventDefisBonus eventDefisBonus;
	public ChunkLoader chunkLoader;
	
	public Game(Plugin plugin, ChunkLoader chunkLoader) {
		this.plugin = plugin;
		this.chunkLoader = chunkLoader;
	}
	public void setClassEventDefisBonus(EventDefisBonus eventDefisBonus) {
		this.eventDefisBonus = eventDefisBonus;
	}
	public void setClassScoreBoard(ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}
	public void setClassTimer(Timer timer) {
		this.timer = timer;
	}
	public void setClassTeams(Teams teams) {
		this.teams = teams;
	}
	
	public void setClassDefis(Defis defis) {
		this.defis = defis;
	}
	
	public void setClassBingoGui(BingoGui bingoGui) {
		this.bingoGui = bingoGui;
	}
	
	public void setClassResult(Result result) {
		this.result = result;
	}
		
	public void verifGrilleBingo(Player player) {
		String teamPlayer = teams.findTeamPlayer(player);
		if (teams.teamCanSpectator.get(teamPlayer)) return;
		List<List<ItemStack>> grilleLigne = new ArrayList<List<ItemStack>>();
		List<ItemStack> ligne = new ArrayList<ItemStack>();
		for (int i = 0; i < 25; i++) {
			ligne.add(grilleBingo.get(i));
			if (((i+1) % 5) == 0) {
				grilleLigne.add(ligne);
				ligne = new ArrayList<ItemStack>();
			}	
		}
		
		int nbBingo = 0;
		for (List<ItemStack> items : grilleLigne) {
			int nbValid = 0;
			for (ItemStack item : items) {
				if (teams.defiValid.get(teamPlayer).get(item.getItemMeta().getDisplayName())) {
					nbValid++;
				}
			}
			if (nbValid == 5) {
				nbBingo++;
			}
		}
		for (int i = 0; i < 5; i++) {
			int nbValid = 0;
			for (int j = 0; j < 5; j++) {
				ItemStack item = grilleLigne.get(j).get(i);
				if (teams.defiValid.get(teamPlayer).get(item.getItemMeta().getDisplayName())) {
					nbValid++;
				}
			}
			if (nbValid == 5) {
				nbBingo++;
			}
		}
		teams.nbreBingoValid.put(teamPlayer, nbBingo);
		if (nbBingo == nombreBingos || teams.nbreDefiValid.get(teamPlayer) == 25) {
			teams.teamCanSpectator.put(teamPlayer, true);
			Hashtable<String, String> teamClassement = new Hashtable<>();
			teamClassement.put("team", teamPlayer);
			if (timeGameHour == 0) {
            	String temps = "";
            	int seconds = timer.seconds;
            	int minutes = timer.minutes;
            	if (minutes < 10) {
            		temps += ("0" + minutes);
            	} else {
            		temps += (minutes);
            	}
            	if (seconds < 10) {
            		temps += (":0" + seconds);
            	} else {
            		temps += (":" + seconds);
            	}
            	teamClassement.put("time", temps);
            } else {
            	String temps = "";
            	int seconds = timer.seconds;
            	int minutes = timer.minutes;
            	if (minutes < 10) {
            		temps += (":0" + minutes);
            	} else {
            		temps += (":" + minutes);
            	}
            	if (seconds < 10) {
            		temps += (":0" + seconds);
            	} else {
            		temps += (":" + seconds);
            	}
            	teamClassement.put("time", temps);
            }
			result.classement.add(teamClassement);
			for (Player player1 : Bukkit.getOnlinePlayers()) {
				player1.sendMessage("§7[§eBINGO§7] §fL'équipe " + teams.prefixeColorTeams.get(teamPlayer) + teamPlayer + " §fa fini sa partie");
				player1.sendMessage("Elle peut continuer de jouer ou devenir spectatrice");
				if (teams.teamsHash.get(teamPlayer).contains(player1)) {
					player1.sendMessage("§7§oUtilisez la commande §e§o/spec §7§opour devenir spectateur");
				}
			}
		}
	}
	
	public void finDuJeu() {
		timer.timerRun = false;
		this.gameLaunch = false;
		this.DamagePlayer = false;
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setGameMode(GameMode.SURVIVAL);
		}
		World overworld = listWorld.get(0);
		BlocksFill.changeArea(-20, 200, -20, 20, 200, 20, Material.WHITE_STAINED_GLASS, overworld);
		BlocksFill.changeArea(-19, 200, -19, 19, 200, 19, Material.BARRIER, overworld);
		BlocksFill.changeArea(-20, 201, -20, -20, 203, 20, Material.CYAN_STAINED_GLASS_PANE, overworld);
		BlocksFill.changeArea(-19, 201, -20, 20, 203, -20, Material.CYAN_STAINED_GLASS_PANE, overworld);
		BlocksFill.changeArea(-19, 201, 20, 20, 203, 20, Material.CYAN_STAINED_GLASS_PANE, overworld);
		BlocksFill.changeArea(20, 201, -19, 20, 203, 19, Material.CYAN_STAINED_GLASS_PANE, overworld);
	
		
		Location spawn = new Location(listWorld.get(0), 0, 204, 0);
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.getInventory().clear();
			player.teleport(spawn);			
		}
		
		for (World world : listWorld) {
			Bukkit.getWorld(world.getName()).setDifficulty(Difficulty.PEACEFUL);
		}
	}

	public void createGrille() {
		if (modeJeu == "Handicap") {
			for (int i = 0; i < teams.nombreTeams; i++) {
				String nameTeam = teams.colorTeams.get(i);
				int caseCacher = (int)(Math.random() * (24 + 1));
				caseCacherHandicap.put(nameTeam, caseCacher);
				System.out.println(nameTeam + " " + caseCacher);
			}
		}
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
    	String difficulte = "";
    	switch (listeDefis.get(2)) {
		case "easy":
			difficulte = "Facile";
			break;
			
		case "medium":
			difficulte = "Moyen";
			break;
			
		case "hard":
			difficulte = "Difficile";
			break;
			
		case "extreme":
			difficulte = "Extrême";
			break;

		default:
			break;
		}
    	if (listeDefis.get(1).length() <= 30) {
    		itemLore.add(listeDefis.get(1));
    		itemLore.add("§8Difficulté: §7" + difficulte);
    		itemMeta.setLore(itemLore);
    		item.setItemMeta(itemMeta);
    	} else {
    		String[] splitLore = listeDefis.get(1).split("(?<=\\G.{30})");
    		Integer len = splitLore.length;
    		for (int i1 = 0; i1 < splitLore.length-1; i1++) {
    			while (splitLore[i1].substring(splitLore[i1].length()-1) != " " && splitLore[i1+1].charAt(0) != ' ') {
    				String letter = splitLore[i1].substring(splitLore[i1].length()-1);
    				splitLore[i1] = splitLore[i1].substring(0, splitLore[i1].length()-1);
    				splitLore[i1+1] = letter + splitLore[i1+1];
    			}
    			splitLore[i1+1] = "§e§o" + splitLore[i1+1].substring(1);
    			itemLore.add(splitLore[i1]);
    		}
    		itemLore.add(splitLore[len-1]);
    		itemLore.add("§8Difficulté: §7" + difficulte);
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
		World overworld = listWorld.get(0);
		List<Chunk> chunksSpawn = new ArrayList<>(Arrays.asList(new Location(overworld, 19, 200, 19).getChunk(),
																new Location(overworld, 19, 200, 9).getChunk(),
																new Location(overworld, 19, 200, -8).getChunk(),
																new Location(overworld, 19, 200, -18).getChunk(),
																new Location(overworld, 7, 200, -18).getChunk(),
																new Location(overworld, -7, 200, -18).getChunk(),
																new Location(overworld, -18, 200, -18).getChunk(),
																new Location(overworld, -18, 200, -7).getChunk(),
																new Location(overworld, -18, 200, 7).getChunk(),
																new Location(overworld, -18, 200, 18).getChunk(),
																new Location(overworld, -7, 200, 19).getChunk(),
																new Location(overworld, 7, 200, 19).getChunk(),
																new Location(overworld, 8, 200, 9).getChunk(),
																new Location(overworld, 7, 200, 8).getChunk(),
																new Location(overworld, -6, 200, -7).getChunk(),
																new Location(overworld, -7, 200, 6).getChunk()));
		for (Chunk chunk : chunksSpawn) {
			chunkLoader.addChunkForceLoad(chunk);
		}
		Bukkit.getWorld(listWorld.get(0).getName()).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		Bukkit.getWorld(listWorld.get(0).getName()).setTime(0);
		teams.createTeams();
		this.InSetup = true;
		this.DamagePlayer = false;
		inventorySelectTeams();
		BlocksFill.changeArea(-20, 200, -20, 20, 200, 20, Material.WHITE_STAINED_GLASS, overworld);
		BlocksFill.changeArea(-19, 200, -19, 19, 200, 19, Material.BARRIER, overworld);
		BlocksFill.changeArea(-20, 201, -20, -20, 203, 20, Material.CYAN_STAINED_GLASS_PANE, overworld);
		BlocksFill.changeArea(-19, 201, -20, 20, 203, -20, Material.CYAN_STAINED_GLASS_PANE, overworld);
		BlocksFill.changeArea(-19, 201, 20, 20, 203, 20, Material.CYAN_STAINED_GLASS_PANE, overworld);
		BlocksFill.changeArea(20, 201, -19, 20, 203, 19, Material.CYAN_STAINED_GLASS_PANE, overworld);
		for (World world : listWorld) {
			Bukkit.getWorld(world.getName()).setDifficulty(Difficulty.PEACEFUL);
			world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
			world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
		}
		Location spawn = new Location(listWorld.get(0), 0, 204, 0);
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.teleport(spawn);
			Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();
	        while (iterator.hasNext())
	        {
	            AdvancementProgress progress = player.getAdvancementProgress(iterator.next());
	            for (String criteria : progress.getAwardedCriteria())
	                progress.revokeCriteria(criteria);
	        }
		}
	}
	
	public void inventorySelectTeams() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.getInventory().clear();
		}
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
		this.timeGameHour = 2;
		this.timeGameMinutes = 0;
		this.modeVictoire = "Bingo";
		this.nombreBingos = 3;
		this.eventDefiBonus = "Off";
		this.modeJeu = "Classic";
		this.modeAffichage = "Chill";
		this.teams.resetTeam();
		this.defis.resetDefi();
		this.defis.presetTwoPlayers();
	}
	
	public void resetGame() {
		this.resetSettings();
		this.grilleBingo = new ArrayList<ItemStack>();
		this.gameLaunch = false;
		this.InSetup = false; 
		this.DamagePlayer = true;
	}
	
}
