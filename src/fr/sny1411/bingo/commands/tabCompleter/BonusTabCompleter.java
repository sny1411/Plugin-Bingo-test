package fr.sny1411.bingo.commands.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.sny1411.bingo.utils.Game;

public class BonusTabCompleter implements TabCompleter {
	private Game game;

	public BonusTabCompleter(Game game) {
		this.game = game;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> defisTab = new ArrayList<>();
		for (List<String> nameDefi : game.eventDefisBonus.defisEnCours) {
			defisTab.add(nameDefi.get(0).substring(4));
		}
		return defisTab;
	}

}
