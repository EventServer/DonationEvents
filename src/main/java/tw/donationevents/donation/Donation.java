package tw.donationevents.donation;

import lombok.*;

@AllArgsConstructor @Getter @Setter @NoArgsConstructor @ToString
public class Donation {
    private String donor;
    private String message;
    private float cash;
    private Currency currency;
}
