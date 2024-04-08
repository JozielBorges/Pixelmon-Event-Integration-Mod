package knuckles.PixelmonEventIntegrationMod.Communication;

import java.util.UUID;

public class BerryCatchCommunication {
    private UUID player;

    public BerryCatchCommunication(UUID player) {
        this.player = player;
    }

    public UUID getPlayer() {
        return player;
    }
}
