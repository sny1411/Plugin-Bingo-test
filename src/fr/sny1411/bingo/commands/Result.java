package fr.sny1411.bingo.commands;

import java.util.ArrayList;
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
			System.out.println(team);
		}
	}
	
	private void positionCalculation() {
		if (game.modeVictoire.equalsIgnoreCase("Bingo")) {
			
		} else {
			
		}
	}

}
