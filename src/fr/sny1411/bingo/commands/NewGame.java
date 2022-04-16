package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.utils.Game;

public class NewGame implements CommandExecutor {
	private Game game;
	public NewGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (game.InSetup == true) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("confirm")) {
						game.resetGame();
						game.setup();
					}
				} else {
					sender.sendMessage(ChatColor.RED + "la partie est en cours, faites "+ ChatColor.DARK_AQUA +  "/newGame confirm" + ChatColor.RED +" pour relancer une partie");
				}
				
			} else {
				game.resetGame();
				game.setup();
			}
		} else {
			Bukkit.getConsoleSender().sendMessage("commande impossible dans la console !");
		}
		return false;
	}

}
