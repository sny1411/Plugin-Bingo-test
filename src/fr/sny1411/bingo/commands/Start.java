package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.sny1411.bingo.utils.Game;

public class Start implements CommandExecutor {
	
	private Game game;

	public Start(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (game.InSetup == true) {
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
			} else {
				sender.sendMessage("Crée une nouvelle partie avant ! (/newGame)");
			}
		} else {
			Bukkit.getConsoleSender().sendMessage("Commande executable qu'en jeu");
		}
		return false;
	}

}
