package fr.sny1411.bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.sny1411.bingo.utils.Game;

public class StopGame implements CommandExecutor {
	
	private Game game;
	
	public StopGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (game.gameLaunch) {
			game.finDuJeu();
		}
		return false;
	}

}
