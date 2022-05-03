package fr.sny1411.bingo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sny1411.bingo.utils.Game;

public class Start implements CommandExecutor {
	
	private Game game;
	private Plugin plugin;

	public Start(Game game, fr.sny1411.bingo.Plugin plugin) {
		this.game = game;
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			    @Override
			    public void run() {
			       startGame((Player) sender);
			    }
			});
			
		} else {
			Bukkit.getConsoleSender().sendMessage("Commande executable qu'en jeu");
		}
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				destroySpawn();
				
			}
		}.runTaskLater(plugin, 60);
		game.timer.startTimer();
		return false;
	}
	
	private void destroySpawn() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console, "fill -20 200 -20 20 200 20 void_air replace");
		Bukkit.dispatchCommand(console, "fill -19 200 -19 19 200 19 void_air replace");
		Bukkit.dispatchCommand(console, "fill -20 201 -20 -20 203 20 void_air replace");
		Bukkit.dispatchCommand(console, "fill -20 201 -20 20 203 -20 void_air replace");
		Bukkit.dispatchCommand(console, "fill 20 201 20 -20 203 20 void_air replace");
		Bukkit.dispatchCommand(console, "fill 20 201 20 20 203 -20 void_air replace");
	}
	
	private void startGame(Player sender) {
		if (game.InSetup == true) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.setStatistic(Statistic.KILL_ENTITY,EntityType.ZOMBIE, 0);
				player.setStatistic(Statistic.KILL_ENTITY,EntityType.GLOW_SQUID, 0);
			}
			game.createGrille();
			game.InSetup = false;
			game.gameLaunch = true;
			List<String> colorStart = new ArrayList<String>(Arrays.asList("§b","§9","§1"));
			for (int i = 3; i > 0; i--) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.sendTitle(colorStart.get(3 - i) + i, "", 0, 20, 0);
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.sendTitle("§1Lezzzgoo !", "", 0, 20, 0);
				}
			}
			
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.DamagePlayer = true;
			
			Bukkit.broadcastMessage("Les dégats des joueurs sont activé !");
		} else {
			sender.sendMessage("Crée une nouvelle partie avant ! (/newGame)");
		}
	}

}
