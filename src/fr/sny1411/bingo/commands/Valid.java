package fr.sny1411.bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.utils.Game;

public class Valid implements CommandExecutor {
	private Game game;
	public Valid(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			System.out.println(args[0] + "  " + args[1]);
			String item = args[1].replace("_", " ");
			game.teams.defiDone.get(args[0]).put("§d§l" + item,true);
			game.teams.defiValid.get(args[0]).put("§d§l" + item,true);
			int i = game.teams.nbreDefiValid.get(args[0]);
			game.teams.nbreDefiValid.put(args[0], i + 1);
			for (Player playerGui : game.teams.playersOnBingoGui.get(args[0])) {
				game.bingoGui.openGui(playerGui, args[0]);
			}
		}
		return false;
	}

}
