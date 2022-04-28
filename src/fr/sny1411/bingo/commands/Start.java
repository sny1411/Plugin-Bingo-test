package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
				for (int i = 3; i >= 0; i--) {
					for (Player player : Bukkit.getPlayers()) {
						player.sendTitle("§a" + i);
					}
					TimeUnit.SECONDS.sleep(1);
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
