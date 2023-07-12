import org.json.JSONException;
import org.json.simple.JSONObject;
import tw.donationevents.donation.Currency;
import tw.donationevents.donation.Donation;
import tw.donationevents.donation.DonationEventListener;

import java.util.Arrays;

public class TestEventListener implements DonationEventListener {
    @Override
    public void onDonation(Object[] args) {
        JSONObject donationJsonStruct = parseJsonStruct(args[0].toString());
        if (donationJsonStruct == null) return;
        final Donation donation = new Donation();
        donation.setDonor(donationJsonStruct.get("username").toString());
        donation.setMessage(donationJsonStruct.get("message").toString());
        donation.setCash(Float.parseFloat(donationJsonStruct.get("amount_formatted").toString()));
        donation.setCurrency(Currency.valueOf(donationJsonStruct.get("currency").toString()));
        System.out.println(donation);
    }

    @Override
    public void onTimeout(Object[] args) {

    }

    @Override
    public void onError(Object[] args) {
        System.out.println("Error to D-A!");
        for (Object arg: args) {
            System.out.println(arg);
        }
    }

    @Override
    public void onDisconnect(Object[] args) {
        System.out.println("Disconnect to D-A!");
        System.out.println(args);
        for (Object arg: args) {
            System.out.println(arg);
        }
    }

    @Override
    public void onConnect(Object[] args) {
        System.out.println("Connect to D-A!");
    }
}
