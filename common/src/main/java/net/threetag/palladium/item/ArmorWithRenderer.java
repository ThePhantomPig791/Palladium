package net.threetag.palladium.item;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public interface ArmorWithRenderer {

    void setCachedArmorRenderer(Object object);

    Object getCachedArmorRenderer();

    default ResourceLocation getArmorRendererFile() {
        return Registry.ITEM.getKey((Item) this);
    }

}
