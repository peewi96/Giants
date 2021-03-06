package me.pkt77.giants;

import me.pkt77.giants.utils.API;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class Commands implements CommandExecutor {
	private Giants _giants;

	public Commands(Giants giants) {
		_giants = giants;
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("giants")) {
			if (args.length == 0) {
				if (_giants.getPermissions().hasAnyPerm(sender)) {
					sender.sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" + "/giants reload   Reloads the config file.");
				} else {
					_giants.getPermissions().sendPermissionsMessage(sender);
				}
				return true;
			} else {
				if (args[0].equalsIgnoreCase("reload")) {
					@SuppressWarnings("unused")
					Player player = null;
					if (sender instanceof Player) {
						player = (Player) sender;
						if (_giants.getPermissions().hasReloadPerm(sender)) {
							API.getFileHandler().loadConfig();
							sender.sendMessage(ChatColor.GREEN + "Giants config file reloaded.");
						} else {
							_giants.getPermissions().sendPermissionsMessage(sender);
						}
					} else {
						API.getFileHandler().loadConfig();
						System.out.println("Giants config file reloaded.");
					}
				}
			}
		}
		return true;
	}
}