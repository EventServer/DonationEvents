package tw.donationevents.donation;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.Getter;
import org.json.JSONObject;

import java.net.URI;

@Getter
public class DonationAlerts {
    private static final URI SOCKET_IO_URL = URI.create("https://socket.donationalerts.ru:443");

    private final Socket socket;
    private final DonationEventListener eventListener;

    private final boolean reconnection = true;
    private final int reconnectionAttempts = 5;
    private final long reconnectionDelay = 5000L;
    public DonationAlerts (String accessToken, DonationEventListener eventListener) {
        this.eventListener = eventListener;

        socket = IO.socket(SOCKET_IO_URL);
        socket.io().reconnection(reconnection);
        socket.io().reconnectionAttempts(reconnectionAttempts);
        socket.io().reconnectionDelay(reconnectionDelay);
        final JSONObject authStruct = makeAuthStruct(accessToken);
        socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
        });
        socket.on(Socket.EVENT_CONNECT, data -> {
            eventListener.onConnect(data);
            socket.emit("add-user", makeAuthStruct(accessToken));
        });

        socket.on(Socket.EVENT_DISCONNECT, eventListener::onConnect);
        socket.on(Socket.EVENT_CONNECT_ERROR, eventListener::onError);
        socket.on("donation", eventListener::onDonation);
        socket.on("reconnect", args -> socket.emit("add-user", authStruct));
        socket.on("connect_timeout", eventListener::onTimeout);
        socket.connect();
    }


    private JSONObject makeAuthStruct(String accessToken){
        final JSONObject authStruct = new JSONObject();
        try {
            authStruct.put("type", "minor");
            authStruct.put("token", accessToken);
        } catch (Exception e) { e.printStackTrace(); }

        return authStruct;
    }
}
