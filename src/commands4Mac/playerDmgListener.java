package commands4Mac;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class playerDmgListener implements Listener{
	private main plugin;

	public playerDmgListener(main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) {
			return;
		}
		if(!(e.getDamager() instanceof Player)) {

		}else if(e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager();
			if (!(arrow.getShooter() instanceof Player)) {
				return;
			}
		}
		Player a = (Player) e.getDamager();
		String aName = a.getDisplayName();
		Player p = (Player) e.getEntity();
		String pName = p.getDisplayName();
		if(plugin.getConfig().contains(pName + "." + "friend"+ "."+ aName)) {
			e.setCancelled(true);
		}
	}
}
