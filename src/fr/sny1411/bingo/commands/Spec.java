package fr.sny1411.bingo.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.utils.Game;

public class Spec implements CommandExecutor {

	private Game game;
	
	public Spec(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			String teamPlayer = game.teams.findTeamPlayer(player);
			if (game.teams.teamCanSpectator.get(teamPlayer)) {
				player.setGameMode(GameMode.SPECTATOR);
			} else {
				player.sendMessage("§8[§c⚠§8] §cVous n'avez pas terminé votre partie !");
			}
		}
		return false;
	}

}