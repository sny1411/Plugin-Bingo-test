package fr.sny1411.bingo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.sny1411.bingo.utils.Game;

public class Start implements CommandExecutor {
	
	private Game game;
	private Plugin plugin;
	private Boolean noTeam = false;

	public Start(Game game, fr.sny1411.bingo.Plugin plugin) {
		this.game = game;
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		noTeam = false;
		if (sender instanceof Player) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (game.teams.findTeamPlayer(player) == "") {
					player.sendMessage("§8[§c⚠§8] §fVeuillez rejoindre une équipe");
					noTeam = true;
				}
			}
			if (noTeam) {
				sender.sendMessage("§8[§c⚠§8] §fDes joueurs ne possèdent pas d'équipe");
				return false;
			}
			BukkitTask taskStart = Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			    @Override
			    public void run() {
			       startGame((Player) sender);
			    }
			});
			game.plugin.listTask.add(taskStart);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (game.teams.findTeamPlayer(player) == "Spectator") {
					player.setGameMode(GameMode.SPECTATOR);
				} else {
					player.setGameMode(GameMode.SURVIVAL);
				}
				player.sendMessage("§7===========[§eSettings§7]===========");
				player.sendMessage("§6Affichage des défis réalisés§f: " + game.modeAffichage);
				player.sendMessage("§6Mode de jeu§f: " + game.modeJeu);
				if (game.modeVictoire == "Bingo") {
					player.sendMessage("§6Condition de victoire§f: " + game.nombreBingos + " Bingos");
				} else {
					player.sendMessage("§6Condition de victoire§f: " + game.modeVictoire);
				}
				if (game.eventDefiBonus == "Off") {
					player.sendMessage("§6Event(s)§f: §c✖");
				} else {
					player.sendMessage("§6Event(s)§f: Défi Bonus");
				}
				player.sendMessage("§eDurée§f: 2h");
				player.sendMessage("§eInvulnérabilité§f: 30s");
				player.sendMessage("         §7---------------");
				player.sendMessage("§7§oTapez §8§o/info §7§opour revoir ce message");
				Bukkit.dispatchCommand(sender, "tellraw "+ player.getName() +" [\"\",{\"text\":\"\\u226b\",\"bold\":true,\"color\":\"dark_gray\"},{\"text\":\" Plugin par \",\"color\":\"gray\"},{\"text\":\"sny1411\",\"color\":\"#FFBBFF\"},{\"text\":\" & \",\"color\":\"gray\"},{\"text\":\"Unsense\",\"color\":\"#FFBBFF\"},{\"text\":\" \\u226a\",\"bold\":true,\"color\":\"dark_gray\"}]");
				player.sendMessage("§7==============================");
			}
			
			
		} else {
			Bukkit.getConsoleSender().sendMessage("Commande executable qu'en jeu");
		}
		
		BukkitTask taskDestroySpawn = new BukkitRunnable() {
			
			@Override
			public void run() {
				destroySpawn();
			}
		}.runTaskLater(plugin, 60);
		game.plugin.listTask.add(taskDestroySpawn);
		return false;
	}
	
	private void destroySpawn() {
		List<Player> playersList = new ArrayList<Player>(game.teams.playerInGui);
		for (int i = 0; i < playersList.size(); i++) {
			Player player = playersList.get(i);
			player.closeInventory();
		}
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
		Bukkit.dispatchCommand(console, "fill -20 200 -20 20 200 20 void_air replace");
		Bukkit.dispatchCommand(console, "fill -19 200 -19 19 200 19 void_air replace");
		Bukkit.dispatchCommand(console, "fill -20 201 -20 -20 203 20 void_air replace");
		Bukkit.dispatchCommand(console, "fill -20 201 -20 20 203 -20 void_air replace");
		Bukkit.dispatchCommand(console, "fill 20 201 20 -20 203 20 void_air replace");
		Bukkit.dispatchCommand(console, "fill 20 201 20 20 203 -20 void_air replace");
		Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
	}
	
	private void startGame(Player sender) {
		if (game.InSetup == true) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.setStatistic(Statistic.KILL_ENTITY,EntityType.ZOMBIE, 0);
				player.setStatistic(Statistic.KILL_ENTITY,EntityType.GLOW_SQUID, 0);
				player.getInventory().clear();
			}
			game.createGrille();
			
			if (!(game.eventDefiBonus == "Off")) {
				game.eventDefisBonus.initEvent();
			}
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
			}
			game.InSetup = false;
			game.gameLaunch = true;
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.sendTitle("\uE005", "", 0, 20, 50);
			}
			Bukkit.getWorld("world").setDifficulty(Difficulty.HARD);
			BukkitTask taskTimer = Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
				
				@Override
				public void run() {
					game.timer.startTimer();
				}
			});
			game.plugin.listTask.add(taskTimer);
			BukkitTask taskScoreBoard = Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
				
				@Override
				public void run() {
					game.scoreBoard.createScoreBoard();
				}
			});
			game.plugin.listTask.add(taskScoreBoard);
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.DamagePlayer = true;
			Bukkit.broadcastMessage("§7[§eBINGO§7] §fLes dégâts des joueurs sont §lactivés§r !");
		} else {
			sender.sendMessage("Crée une nouvelle partie avant ! (/newGame)");
		}
	}

}
