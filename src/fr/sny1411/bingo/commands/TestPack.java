package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestPack implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendTitle("\uE001", "", 5, 70, 20);
		}
		return false;
	}

}
