package fr.sny1411.bingo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import fr.sny1411.bingo.Plugin;
import fr.sny1411.bingo.utils.Game;

public class Result implements CommandExecutor {

	private Game game;
	private Plugin plugin;
	public List<Hashtable<String, String>> classement = new ArrayList<>();
	public List<List<String>> classementPts = new ArrayList<>(); // compo list -> team : totalPts : easyPts : mediumPts : hardPts : extremePts 
	public int ptsEasy = 1;
	public int ptsMedium = 3;
	public int ptsHard = 9;
	public int ptsExtreme = 27;
	public List<String> iconesClassement = new ArrayList<>(Arrays.asList("\uE002","\uE003","\uE004","➃","➄","➅"));
	
	
	public Result(Game game, Plugin plugin) {
		this.game = game;
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§8[§c⚠§8] §fCommande non exécutable par la console");
		}
		if (!game.gameLaunch) {
			BukkitTask taskStart = Bukkit.getScheduler().runTask(plugin, new Runnable() {
			    @Override
			    public void run() {
			       displayResult();
			       displayResultPts();
			    }
			});
			game.plugin.listTask.add(taskStart);
		} else {
			sender.sendMessage("§8[§c⚠§8] §fLa partie est en cours !");
		}
		return false;
	}
	
	private void displayResultPts() {
		positionCalculationPts();
		System.out.println(classementPts);
		Bukkit.broadcastMessage("§7=========[§eClassement par Points§7]=========");
		int i = 0;
		for (List<String> team : classementPts) {
			Bukkit.broadcastMessage(" " + iconesClassement.get(i) + " §l" + game.teams.prefixeColorTeams.get(team.get(0)) + team.get(0) + "§f " + team.get(1) + " point(s) (" + team.get(2) + " §f| " + team.get(3) + " §f| " + team.get(4) + " §f| " + team.get(5) + "§f)");
			i++;
		}
		Bukkit.broadcastMessage("§7======================================");
	}
	
	private void displayResult() {
		positionCalculation();
		// affiche la fin
		System.out.println(classement);
		
		int i = 0;
		if (game.modeVictoire.equalsIgnoreCase("Bingo")) {
			Bukkit.broadcastMessage("§7=======[§eClassement§7]=======");
			for (Hashtable<String, String> team : classement) {
				if (team.get("nbreBingo") != null) {
					Bukkit.broadcastMessage(" " + iconesClassement.get(i) + " §l" + game.teams.prefixeColorTeams.get(team.get("team")) + team.get("team") + "§f (" + team.get("time") + ") " + team.get("nbreBingo") + " bingo(s)");
				} else {
					Bukkit.broadcastMessage(" " + iconesClassement.get(i) + " §l" + game.teams.prefixeColorTeams.get(team.get("team")) + team.get("team") + "§f (" + team.get("time") + ")");
				}
				i++;
			}
			Bukkit.broadcastMessage("§7=========================");
		} else {
			Bukkit.broadcastMessage("§7=======[§eClassement§7]=======");
			for (Hashtable<String, String> team : classement) {
				if (team.get("nbreDefis") != null) {
					Bukkit.broadcastMessage(" " + iconesClassement.get(i) + " §l" + game.teams.prefixeColorTeams.get(team.get("team")) + team.get("team") + "§f (" + team.get("time") + ") " + team.get("nbreDefis") + " défi(s)");
				} else {
					Bukkit.broadcastMessage(" " + iconesClassement.get(i) + " §l" + game.teams.prefixeColorTeams.get(team.get("team")) + team.get("team") + "§f (" + team.get("time") + ")");
				}
				i++;
			}
			Bukkit.broadcastMessage("§7=========================");
		}
	}
	
	private void positionCalculationPts() {
		Integer nbreDefisEasy;
		Integer nbreDefisMedium;
		Integer nbreDefisHard;
		Integer nbreDefisExtreme;
		List<String> teams = new ArrayList<>();
		for (int i = 0; i < game.teams.nombreTeams; i++) {
			teams.add(game.teams.colorTeams.get(i));
		}
		System.out.println(teams);
		for (String team : teams) {
			List<List<String>> defisValid = new ArrayList<>();
			for (Entry<String, Boolean> entry : game.teams.defiValid.get(team).entrySet()) {
				if (entry.getValue()) {
					for (List<String> defi : game.defis.defi) {
						if (defi.get(0).equalsIgnoreCase(entry.getKey())) {
							defisValid.add(defi);
						}
					}
				}
			}
			System.out.println("test 1");
			nbreDefisEasy = 0;
			nbreDefisMedium = 0;
			nbreDefisHard = 0;
			nbreDefisExtreme = 0;
			
			for (List<String> defi : defisValid) {
				switch (defi.get(2)) {
				case "easy":
					nbreDefisEasy++;
					break;
				
				case "medium":
					nbreDefisMedium++;
					break;
					
				case "hard":
					nbreDefisHard++;
					break;
					
				case "extreme":
					nbreDefisExtreme++;
					break;
				default:
					break;
				}
			}
			Integer totalPts = nbreDefisEasy*ptsEasy + nbreDefisMedium*ptsMedium + nbreDefisHard*ptsHard + nbreDefisExtreme*ptsExtreme;
			List<String> listTeamClassement = new ArrayList<>(Arrays.asList(team,
																			totalPts.toString(),
																			"§a" + nbreDefisEasy,
																			"§6" + nbreDefisMedium,
																			"§c" + nbreDefisHard,
																			"§8" + nbreDefisExtreme));
			classementPts.add(listTeamClassement);
			
			
		}
		Collections.sort(classementPts, comparator);
	}
	
	private void positionCalculation() {
		if (game.modeVictoire.equalsIgnoreCase("Bingo")) {
			List<String> nameTeamClassement = new ArrayList<>();
			for (Hashtable<String, String> team : classement) {
				nameTeamClassement.add(team.get("team"));
			}
			List<String> nameTeam = new ArrayList<>();
			for (String name : game.teams.colorTeams.subList(0, game.teams.nombreTeams)) {
				if (!nameTeamClassement.contains(name)) {
					nameTeam.add(name);
				}
			}
			List<List<String>> resteClassement = new ArrayList<>();
			
			for (String teamClassement : nameTeam) {
				System.out.println(teamClassement);
				List<String> teamTemp = new ArrayList<>(Arrays.asList(teamClassement, game.teams.nbreBingoValid.get(teamClassement).toString()));
				resteClassement.add(teamTemp);
			}
			
			Collections.sort(resteClassement, comparator);
			
			for (List<String> team : resteClassement) {
				Hashtable<String, String> addTeamClassement = new Hashtable<>();
				addTeamClassement.put("team", team.get(0));
				addTeamClassement.put("time", "02:00:00");
				addTeamClassement.put("nbreBingo", team.get(1));
				classement.add(addTeamClassement);
			}
		} else {
			List<String> nameTeamClassement = new ArrayList<>();
			if (classement != null) {
				for (Hashtable<String, String> team : classement) {
					nameTeamClassement.add(team.get("team"));
				}
			}
			List<String> nameTeam = new ArrayList<>();
			for (String name : game.teams.colorTeams) {
				if (!nameTeamClassement.contains(name)) {
					nameTeam.add(name);
				}
			}
			List<List<String>> resteClassement = new ArrayList<>();
			
			int i = 0;
			for (String teamClassement : nameTeam) {
				if (i < game.teams.nombreTeams) {
					List<String> teamTemp = new ArrayList<>(Arrays.asList(teamClassement, game.teams.nbreDefiValid.get(teamClassement).toString()));
					resteClassement.add(teamTemp);
				}
				i++;
			}
			
			Collections.sort(resteClassement, comparator);
			
			for (List<String> team : resteClassement) {
				Hashtable<String, String> addTeamClassement = new Hashtable<>();
				addTeamClassement.put("team", team.get(0));
				addTeamClassement.put("time", "02:00:00");
				addTeamClassement.put("nbreDefis", team.get(1));
				classement.add(addTeamClassement);
			}
		}
	}
	
	public static Comparator<List<String>> comparator = (x,y) -> { 
		return Integer.compare(Integer.parseInt(y.get(1)),Integer.parseInt(x.get(1)));
	};

}
