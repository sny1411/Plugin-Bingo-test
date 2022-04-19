package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				
			} else {
				sender.sendMessage("Crée une nouvelle partie avant ! (/newGame)");
			}
		} else {
			Bukkit.getConsoleSender().sendMessage("Commande executable qu'en jeu");
		}
		return false;
	}

}
