package vg.civcraft.mc.civchat2.utility;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.ConfigurationSection;
import java.util.Map;
import java.util.TreeMap;

public class CivChat2Config {

	private static FileConfiguration config;

	public void setConfigOptions(FileConfiguration config) {

		CivChat2Config.config = config;
	}

	public boolean getGroupsEnabled() {

		return config.getBoolean("info.groups");
	}

	public boolean getDebug() {

		return config.getBoolean("info.debug");
	}

	public String getAfkMessage() {

		return config.getString("info.afkmessage");
	}

	public boolean getLoginAnnounce() {

		return config.getBoolean("info.loginAnnounce", false);
	}

	public int getChatRange() {

		return config.getInt("chat.globalChatRange");
	}

	public String getDefaultColor() {

		return config.getString("chat.defaultChatColor");
	}

	public String getOpChatColor() {

		return config.getString("chat.opChatColor");
	}

	public int getYInc() {

		return config.getInt("chat.yIncreaseDist");
	}

	public double getYScale() {

		return config.getDouble("chat.yIncreaseScale");
	}

	public String getMysqlUsername() {

		return config.getString("mysql.username");
	}

	public String getMysqlHost() {

		return config.getString("mysql.host");
	}

	public int getMysqlPort() {

		return config.getInt("mysql.port");
	}

	public String getMysqlPassword() {

		return config.getString("mysql.password");
	}

	public String getMysqlDBname() {

		return config.getString("mysql.dbname");
	}

	private TreeMap<Double, String> chatColor = null;

	public synchronized String getColorAtDistance(double distance) {

		if (chatColor == null) {
			chatColor = new TreeMap<Double, String>();
			chatColor.put(0.0, getDefaultColor());
			if (config.contains("chat.colors")) {
				ConfigurationSection section = config.getConfigurationSection("chat.colors");
				for (String s : section.getKeys(false)) {
					chatColor.put(section.getDouble(s + ".range"), section.getString(s + ".color"));
				}
			}
		}

		String ret = "-";
		Map.Entry<Double, String> let = chatColor.floorEntry(distance);
		return let == null ? ret : let.getValue();
	}
}
