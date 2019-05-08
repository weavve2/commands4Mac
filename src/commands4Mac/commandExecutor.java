package commands4Mac;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandExecutor implements CommandExecutor {

	private main plugin;

	public commandExecutor(main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(!(s instanceof Player)) {
			s.sendMessage("this cant be used in console");
			return true;
		}
		Player p = (Player) s;
		if (cmd.getName().equalsIgnoreCase("tp")) {
			if(args.length == 0) {
				p.sendMessage("Who you tryin to teleport to Retard?");
				return true;
			}
			Player t = Bukkit.getServer().getPlayer(args[0]);
			if(t == null) {
				p.sendMessage("Player doesnt exist try again later.");
				return true;
			}
			p.teleport(t.getLocation());
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(args.length == 0) {
				p.sendMessage("Please name your home.");
				return true;
			}
			String name = p.getDisplayName();
			String home = args[0];
			if(!(plugin.getConfig().getString(name +"."+ "sethomes").contains(home))) { //Stop the duplicating in the list when reseting homes.
				plugin.getConfig().set(name + "." + "sethomes", plugin.getConfig().getString(name +"."+ "sethomes") + "," + home);
			}
			plugin.getConfig().set(name + "." + home + ".world", p.getLocation().getWorld().getName());
			plugin.getConfig().set(name + "." + home + ".x", p.getLocation().getX());
			plugin.getConfig().set(name + "." + home + ".y", p.getLocation().getY());
			plugin.getConfig().set(name + "." + home + ".z", p.getLocation().getZ());
			plugin.getConfig().set(name + "." + home + ".pitch", p.getLocation().getPitch());
			plugin.getConfig().set(name + "." + home + ".yaw", p.getLocation().getYaw());
			plugin.saveConfig();
			p.sendMessage("Your home has been set.");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("home")) {
			String name = p.getDisplayName(); 
			if(args.length == 0) {
				if(plugin.getConfig().contains(name + ".home")) {
					World w = Bukkit.getServer().getWorld(plugin.getConfig().getString(name + ".home"+ ".world"));
					double x = plugin.getConfig().getDouble(name + ".home"+ ".x");
					double y = plugin.getConfig().getDouble(name + ".home" + ".y")+.5;
					double z = plugin.getConfig().getDouble(name + ".home"+ ".z");
					float pitch = (float) plugin.getConfig().getDouble(name + ".home"+ ".pitch");
					float yaw = (float) plugin.getConfig().getDouble(name + ".home"+ ".yaw");
					Location loc = new Location (w,x,y,z);
					loc.setYaw(yaw);
					loc.setPitch(pitch);
					p.teleport(loc);
					return true;
				}
				String prevSetHomes = plugin.getConfig().getString(name+"."+ "sethomes");
				p.sendMessage("Which home do you want to go to?");
				p.sendMessage(prevSetHomes);
				return true;
			}
			String home = args[0];
			if(plugin.getConfig().contains(name +"."+ home)) {
				World w = Bukkit.getServer().getWorld(plugin.getConfig().getString(name + "."+ home+ ".world"));
				double x = plugin.getConfig().getDouble(name + "."+ home+ ".x");
				double y = plugin.getConfig().getDouble(name + "."+ home+ ".y")+.5;
				double z = plugin.getConfig().getDouble(name + "."+ home+ ".z");
				float pitch = (float) plugin.getConfig().getDouble(name + "."+ home+ ".pitch");
				float yaw = (float) plugin.getConfig().getDouble(name + "."+ home+ ".yaw");
				Location loc = new Location (w,x,y,z);
				loc.setYaw(yaw);
				loc.setPitch(pitch);
				p.teleport(loc);
				return true;
			}
			String prevSetHomes = plugin.getConfig().getString(name+"."+ "sethomes");
			p.sendMessage("Which home do you want to go to?");
			p.sendMessage(prevSetHomes);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("friend")) {
			Player friend = Bukkit.getPlayer(args[0]);
			if(!(friend == null)) {
				String name = p.getDisplayName();
				plugin.getConfig().set(name + "." + "friend", friend.getDisplayName());
				plugin.saveConfig();
			}
		}
		return false;
	}
	
}
