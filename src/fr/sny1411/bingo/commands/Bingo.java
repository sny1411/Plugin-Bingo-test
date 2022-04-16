package fr.sny1411.bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.listenner.BingoGui;
import fr.sny1411.bingo.utils.Game;

public class Bingo implements CommandExecutor {
	private Game game;

	public Bingo(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		BingoGui grille = new BingoGui(game);
		grille.openGui((Player) sender);
		return false;
	}

}
