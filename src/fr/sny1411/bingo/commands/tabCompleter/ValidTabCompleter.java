package fr.sny1411.bingo.commands.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.sny1411.bingo.utils.Game;

public class ValidTabCompleter implements TabCompleter {
	private Game game;

	public ValidTabCompleter(Game game) {
		this.game = game;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		final ArrayList<String> list = new ArrayList<String>();
		if (args.length == 1) {
			list.add("add");
			list.add("remove");
			return list;
		} else if (args.length == 2) {
			for (int i = 0; i < game.teams.nombreTeams; i++) {
				list.add(game.teams.colorTeams.get(i));
			}
			return list;
		} else if (args.length == 3) {
			for (List<String> defi : game.defis.defi) {
				String nameDefi = defi.get(0).replace(" ", "_").substring(4);
				list.add(nameDefi);
			}
			return list;
		} else {
			return list;
		}
	}

}
