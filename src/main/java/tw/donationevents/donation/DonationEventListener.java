package tw.donationevents.donation;

import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public interface DonationEventListener {
    void onDonation(Object[] args);
    void onTimeout(Object[] args);
    void onError(Object[] args);
    void onDisconnect(Object[] args);
    void onConnect(Object[] args);

    @Nullable
    default JSONObject parseJsonStruct(String arg) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(arg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
