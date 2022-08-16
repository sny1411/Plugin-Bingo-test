package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.listener.BingoGui;
import fr.sny1411.bingo.utils.Game;

public class Bingo implements CommandExecutor {
	private Game game;
	private BingoGui grille;

	public Bingo(Game game, BingoGui grille) {
		this.game = game;
		this.grille = grille;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (game.gameLaunch) {
				
				grille.openGui((Player) sender, game.teams.findTeamPlayer((Player) sender));
			} else {
				sender.sendMessage("§8[§c⚠§8] §fLa partie n'a pas encore commencé !");
			}
		} else {
			Bukkit.getConsoleSender().sendMessage("§c[Bingo] commande non executable par la console");
		}
		
		return false;
	}

}
