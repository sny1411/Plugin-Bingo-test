package fr.sny1411.bingo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

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
			    }
			});
			game.plugin.listTask.add(taskStart);
		} else {
			sender.sendMessage("§8[§c⚠§8] §fLa partie est en cours !");
		}
		return false;
	}
	
	private void displayResult() {
		positionCalculation();
		// affiche la fin
		for (Hashtable<String, String> team : classement) {
			Bukkit.getConsoleSender().sendMessage(team.toString());
		}
	}
	
	private void positionCalculation() {
		if (game.modeVictoire.equalsIgnoreCase("Bingo")) {
			List<String> nameTeamClassement = new ArrayList<>();
			for (Hashtable<String, String> team : classement) {
				nameTeamClassement.add(team.get("team"));
			}
			List<String> nameTeam = new ArrayList<>();
			for (String name : game.teams.colorTeams) {
				if (!nameTeamClassement.contains(name)) {
					nameTeam.add(name);
				}
			}
			List<List<String>> resteClassement = new ArrayList<>();
			
			for (String teamClassement : nameTeam) {
				List<String> teamTemp = new ArrayList<>(Arrays.asList(teamClassement, game.teams.nbreBingoValid.get(teamClassement).toString()));
				resteClassement.add(teamTemp);
			}
			
			Collections.sort(resteClassement, comparator);
			
			for (List<String> team : resteClassement) {
				Hashtable<String, String> addTeamClassement = new Hashtable<>();
				addTeamClassement.put("team", team.get(0));
				addTeamClassement.put("nbreBingo", team.get(1));
				classement.add(addTeamClassement);
			}
		} else {
			List<String> nameTeamClassement = new ArrayList<>();
			for (Hashtable<String, String> team : classement) {
				nameTeamClassement.add(team.get("team"));
			}
			List<String> nameTeam = new ArrayList<>();
			for (String name : game.teams.colorTeams) {
				if (!nameTeamClassement.contains(name)) {
					nameTeam.add(name);
				}
			}
			List<List<String>> resteClassement = new ArrayList<>();
			
			for (String teamClassement : nameTeam) {
				List<String> teamTemp = new ArrayList<>(Arrays.asList(teamClassement, game.teams.nbreDefiValid.get(teamClassement).toString()));
				resteClassement.add(teamTemp);
			}
			
			Collections.sort(resteClassement, comparator);
			
			for (List<String> team : resteClassement) {
				Hashtable<String, String> addTeamClassement = new Hashtable<>();
				addTeamClassement.put("team", team.get(0));
				addTeamClassement.put("nbreBingo", team.get(1));
				classement.add(addTeamClassement);
			}
		}
	}
	
	public static Comparator<List<String>> comparator = (x,y) -> { 
		return Integer.compare(Integer.parseInt(y.get(1)),Integer.parseInt(x.get(1)));
	};

}
