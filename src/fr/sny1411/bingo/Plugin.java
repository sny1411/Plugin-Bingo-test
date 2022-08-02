package fr.sny1411.bingo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.sny1411.bingo.commands.Bingo;
import fr.sny1411.bingo.commands.Info;
import fr.sny1411.bingo.commands.NewGame;
import fr.sny1411.bingo.commands.Result;
import fr.sny1411.bingo.commands.Spec;
import fr.sny1411.bingo.commands.Start;
import fr.sny1411.bingo.commands.StopGame;
import fr.sny1411.bingo.commands.TestPack;
import fr.sny1411.bingo.commands.Valid;
import fr.sny1411.bingo.commands.tabCompleter.ValidTabCompleter;
import fr.sny1411.bingo.listenner.BingoGui;
import fr.sny1411.bingo.listenner.DefiListener;
import fr.sny1411.bingo.listenner.EventsListener;
import fr.sny1411.bingo.listenner.SettingsGui;
import fr.sny1411.bingo.listenner.TeamsGui;
import fr.sny1411.bingo.utils.Defis;
import fr.sny1411.bingo.utils.EventDefisBonus;
import fr.sny1411.bingo.utils.Game;
import fr.sny1411.bingo.utils.ScoreBoard;
import fr.sny1411.bingo.utils.Teams;
import fr.sny1411.bingo.utils.Timer;



public class Plugin extends JavaPlugin{
	public ScoreboardManager manager = Bukkit.getScoreboardManager(); 
	private Game game = new Game(this);
	private TeamsGui teamsGui = new TeamsGui(game);
	private Defis defis = new Defis();
	private Teams teams = new Teams(teamsGui,game);
	private DefiListener defiListener = new DefiListener(game);
	private Timer timer = new Timer(this,game);
	private ScoreBoard scoreBoard = new ScoreBoard(game,this);
	private BingoGui bingoGui = new BingoGui(game);
	private Result result = new Result(game, this);
	private EventDefisBonus eventDefisBonus = new EventDefisBonus();
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
		getCommand("valid").setExecutor(new Valid(game));
		getCommand("valid").setTabCompleter(new ValidTabCompleter(game));
		getCommand("testPack").setExecutor(new TestPack());
		Bukkit.getServer().getPluginManager().registerEvents(new EventsListener(game), this);
		Bukkit.getServer().getPluginManager().registerEvents(teamsGui, this);
		Bukkit.getServer().getPluginManager().registerEvents(bingoGui, this);
		Bukkit.getServer().getPluginManager().registerEvents(defiListener, this);
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