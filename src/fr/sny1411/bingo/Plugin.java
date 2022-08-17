package fr.sny1411.bingo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.sny1411.bingo.commands.Bingo;
import fr.sny1411.bingo.commands.Bonus;
import fr.sny1411.bingo.commands.Info;
import fr.sny1411.bingo.commands.NewGame;
import fr.sny1411.bingo.commands.Result;
import fr.sny1411.bingo.commands.Spec;
import fr.sny1411.bingo.commands.Start;
import fr.sny1411.bingo.commands.StopGame;
import fr.sny1411.bingo.commands.TestPack;
import fr.sny1411.bingo.commands.DefisOP;
import fr.sny1411.bingo.commands.tabCompleter.BonusTabCompleter;
import fr.sny1411.bingo.commands.tabCompleter.ValidTabCompleter;
import fr.sny1411.bingo.listener.BingoGui;
import fr.sny1411.bingo.listener.DefiListener;
import fr.sny1411.bingo.listener.EventsListener;
import fr.sny1411.bingo.listener.SettingsGui;
import fr.sny1411.bingo.listener.TeamsGui;
import fr.sny1411.bingo.utils.ChunkLoader;
import fr.sny1411.bingo.utils.Defis;
import fr.sny1411.bingo.utils.EventDefisBonus;
import fr.sny1411.bingo.utils.Game;
import fr.sny1411.bingo.utils.ScoreBoard;
import fr.sny1411.bingo.utils.Teams;
import fr.sny1411.bingo.utils.Timer;



public class Plugin extends JavaPlugin{
	public ScoreboardManager manager = Bukkit.getScoreboardManager(); 
	ChunkLoader chunkLoader = new ChunkLoader();
	private Game game = new Game(this,chunkLoader);
	private TeamsGui teamsGui = new TeamsGui(game);
	private Defis defis = new Defis();
	private Teams teams = new Teams(teamsGui,game);
	private DefiListener defiListener = new DefiListener(game);
	private Timer timer = new Timer(this,game);
	private ScoreBoard scoreBoard = new ScoreBoard(game,this);
	private BingoGui bingoGui = new BingoGui(game);
	private Result result = new Result(game, this);
	private EventDefisBonus eventDefisBonus = new EventDefisBonus(game,defiListener);
	public List<BukkitTask> listTask = new ArrayList<BukkitTask>();


	@Override
	public void onEnable() {
		this.manager = Bukkit.getScoreboardManager();
		game.setClassScoreBoard(scoreBoard);
		game.setClassTimer(timer);
		game.setClassTeams(teams);
		game.setClassDefis(defis);
		game.setClassBingoGui(bingoGui);
		game.setClassResult(result);
		game.setClassEventDefisBonus(eventDefisBonus);
		
		getCommand("bingo").setExecutor(new Bingo(game,bingoGui));
		getCommand("newGame").setExecutor(new NewGame(game));
		getCommand("start").setExecutor(new Start(game,this));
		getCommand("stopGame").setExecutor(new StopGame(game));
		getCommand("result").setExecutor(result);
		getCommand("spec").setExecutor(new Spec(game));;
		getCommand("info").setExecutor(new Info(game));
		getCommand("defis").setExecutor(new DefisOP(game));
		getCommand("defis").setTabCompleter(new ValidTabCompleter(game));
		getCommand("testPack").setExecutor(new TestPack());
		getCommand("bonus").setExecutor(new Bonus(game,defiListener,this));
		getCommand("bonus").setTabCompleter(new BonusTabCompleter(game));
		Bukkit.getServer().getPluginManager().registerEvents(new EventsListener(game), this);
		Bukkit.getServer().getPluginManager().registerEvents(teamsGui, this);
		Bukkit.getServer().getPluginManager().registerEvents(bingoGui, this);
		Bukkit.getServer().getPluginManager().registerEvents(defiListener, this);
		Bukkit.getServer().getPluginManager().registerEvents(chunkLoader, this);
		Bukkit.getServer().getPluginManager().registerEvents(new SettingsGui(game), this);
		super.onEnable();
	}
	@Override
	public void onDisable() {
		System.out.println("disable");
		for (BukkitTask task : listTask) {
			task.cancel();
			Bukkit.getScheduler().cancelTask(task.getTaskId());
		}
		game.timer.reset();
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
		super.onDisable();
	}
	
}