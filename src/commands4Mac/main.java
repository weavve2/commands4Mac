package commands4Mac;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	public static main plugin;
	@Override
	public void onEnable() {
		new commandExecutor(this);
		this.getCommand("tp").setExecutor((commandExecutor)new commandExecutor(this));
		this.getCommand("sethome").setExecutor((commandExecutor)new commandExecutor(this));
		this.getCommand("home").setExecutor((commandExecutor)new commandExecutor(this));
		this.getCommand("friend").setExecutor((commandExecutor)new commandExecutor(this));
		PluginManager pm = getServer().getPluginManager();
		Listener listener = new playerDmgListener(this);
		pm.registerEvents(listener, this);
		Bukkit.getLogger().info("[Preparing to jack Mac off]");
	}
}
