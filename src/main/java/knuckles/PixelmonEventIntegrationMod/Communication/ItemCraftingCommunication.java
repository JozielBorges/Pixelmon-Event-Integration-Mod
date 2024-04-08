package knuckles.PixelmonEventIntegrationMod.Communication;

import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ItemCraftingCommunication {
    private final UUID player;

    private final ItemStack item;

    public ItemCraftingCommunication(UUID player, ItemStack item) {
        this.player = player;
        this.item = item;
    }

    public UUID getPlayer(){
        return player;
    }

    public String getItem(){
        return item.toString();
    }

    public int getAmount(){return item.getCount();}

    public void addAmount(){item.setCount(item.getCount()+1);}
}
