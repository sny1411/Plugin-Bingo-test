package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.utils.Game;

public class Info implements CommandExecutor {
	
	private Game game;

	public Info(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage("§7===========[§eSettings§7]===========");
			player.sendMessage("§6Affichage des défis réalisés§f: " + game.modeAffichage);
			player.sendMessage("§6Mode de jeu§f: " + game.modeJeu);
			if (game.modeVictoire == "Bingo") {
				player.sendMessage("§6Condition de victoire§f: " + game.nombreBingos + " Bingos");
			} else {
				player.sendMessage("§6Condition de victoire§f: " + game.modeVictoire);
			}
			if (game.eventDefiBonus == "Off") {
				player.sendMessage("§6Event(s)§f: §c✖");
			} else {
				player.sendMessage("§6Event(s)§f: Défi Bonus");
			}
			player.sendMessage("§eDurée§f: 2h");
			player.sendMessage("§eInvulnérabilité§f: 30s");
			player.sendMessage("         §7---------------");
			player.sendMessage("§7§oTapez §8§o/info §7§opour revoir ce message");
			Bukkit.dispatchCommand(sender, "tellraw "+ player.getName() +" [\"\",{\"text\":\"\\u226b\",\"bold\":true,\"color\":\"dark_gray\"},{\"text\":\" Plugin par \",\"color\":\"gray\"},{\"text\":\"sny1411\",\"color\":\"#FFBBFF\"},{\"text\":\" & \",\"color\":\"gray\"},{\"text\":\"Unsense\",\"color\":\"#FFBBFF\"},{\"text\":\" \\u226a\",\"bold\":true,\"color\":\"dark_gray\"}]");
			player.sendMessage("§7==============================");
		}
		return false;
	}

}
