package tw.donationevents.donation;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import tw.donationevents.Config;
import tw.donationevents.DonationEvents;
import tw.donationevents.event.DonationAlertsEvent;

public class JavaPluginDonationEventListener implements DonationEventListener {
    private final JavaPlugin plugin;
    public JavaPluginDonationEventListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public void onDonation(Object[] args) {
        JSONObject donationJsonStruct = parseJsonStruct(args[0].toString());
        if (donationJsonStruct == null) return;
        final Donation donation = new Donation();
        donation.setDonor(donationJsonStruct.get("username").toString());
        donation.setMessage(donationJsonStruct.get("message").toString());
        donation.setCash(Float.parseFloat(donationJsonStruct.get("amount_formatted").toString()));
        donation.setCurrency(Currency.valueOf(donationJsonStruct.get("currency").toString()));
        Bukkit.getScheduler().runTask(DonationEvents.getInstance(), () -> new DonationAlertsEvent(donation).callEvent());
    }

    @Override
    public void onTimeout(Object[] args) {

    }

    @Override
    public void onError(Object[] args) {
        plugin.getLogger().info(Config.MESSAGES.ERROR_LOG.replace("%text%", args[0].toString()));
    }

    @Override
    public void onDisconnect(Object[] args) {
        plugin.getLogger().info(Config.MESSAGES.DISCONNECT_LOG);
    }

    @Override
    public void onConnect(Object[] args) {
        plugin.getLogger().info(Config.MESSAGES.CONNECT_LOG);
    }
}
