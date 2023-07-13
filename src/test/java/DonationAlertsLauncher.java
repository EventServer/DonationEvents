import org.json.JSONException;
import tw.donationevents.donation.DonationAlerts;

import java.net.URISyntaxException;

public class DonationAlertsLauncher {
    public static void main(String[] args) throws URISyntaxException, JSONException {
        new DonationAlerts("<token>", new TestEventListener());

    }
}
