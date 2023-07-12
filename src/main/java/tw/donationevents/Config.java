package tw.donationevents;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private Config() {
        throw new IllegalStateException("Utility class");
    }

    public static void Load(FileConfiguration file) {
        final ConfigurationSection messages = file.getConfigurationSection("messages");
        if (messages != null)
            parseMessages(messages);

        TOKEN = file.getString("token");
    }

    public static void parseMessages(ConfigurationSection messages) {
        MESSAGES.CONNECT_LOG = messages.getString("connect-log");
        MESSAGES.DISCONNECT_LOG = messages.getString("disconnect-log");
        MESSAGES.ERROR_LOG = messages.getString("error-log");
    }

    public static String TOKEN;

    public static class MESSAGES {
        private MESSAGES() {
            throw new IllegalStateException("Utility class");
        }

        public static String CONNECT_LOG;
        public static String DISCONNECT_LOG;
        public static String ERROR_LOG;
    }
}
