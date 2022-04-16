package fr.sny1411.bingo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.sny1411.bingo.utils.Game;

public class statsGame implements CommandExecutor {
	
	private Game game;

	public statsGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatColor.GREEN + "-------- STATS GAME --------");
		sender.sendMessage(ChatColor.AQUA +"jeux en preparation : " + ChatColor.GOLD + game.InSetup);
		sender.sendMessage(ChatColor.AQUA + "partie en cours : " + ChatColor.GOLD + game.gameLaunch);
		sender.sendMessage(ChatColor.AQUA + "degats joueurs : " + ChatColor.GOLD + game.DamagePlayer);
		return false;
	}

}
