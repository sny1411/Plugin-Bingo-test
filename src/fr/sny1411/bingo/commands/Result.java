package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.sny1411.bingo.utils.Game;

public class Result implements CommandExecutor {

	private Game game;
	
	public Result(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// affiche les résultats de la partie
		Bukkit.broadcastMessage("yes results");
		return false;
	}

}
