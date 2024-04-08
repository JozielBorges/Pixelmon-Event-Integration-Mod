package knuckles.PixelmonEventIntegrationMod.Communication;

import java.util.UUID;

public class FishingCommunication {
    private UUID player;

    public FishingCommunication(UUID player) {
        this.player = player;
    }

    public UUID getPlayer() {
        return player;
    }
}
