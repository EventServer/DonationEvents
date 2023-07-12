package tw.donationevents;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import tw.donationevents.donation.DonationAlerts;
import tw.donationevents.donation.JavaPluginDonationEventListener;

public final class DonationEvents extends JavaPlugin {
    /**
     * DonationEvents instance
     */
    @Getter
    private static DonationEvents instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Config.Load(getConfig());

        new Thread(() ->
                new DonationAlerts(Config.TOKEN, new JavaPluginDonationEventListener(DonationEvents.getInstance())));
    }
}
