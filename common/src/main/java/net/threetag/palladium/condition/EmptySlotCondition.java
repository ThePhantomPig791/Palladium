package net.threetag.palladium.condition;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.Power;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.util.PlayerSlot;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PlayerSlotProperty;
import org.jetbrains.annotations.Nullable;

public class EmptySlotCondition extends Condition {

    private final PlayerSlot slot;

    public EmptySlotCondition(PlayerSlot slot) {
        this.slot = slot;
    }

    @Override
    public boolean active(LivingEntity entity, @Nullable AbilityEntry entry, @Nullable Power power, @Nullable IPowerHolder holder) {
        for (ItemStack item : this.slot.getItems(entity)) {
            if (!item.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ConditionSerializer getSerializer() {
        return ConditionSerializers.EMPTY_SLOT.get();
    }

    public static class Serializer extends ConditionSerializer {

        public static final PalladiumProperty<PlayerSlot> SLOT = new PlayerSlotProperty("slot").configurable("Slot that must be empty");

        public Serializer() {
            this.withProperty(SLOT, PlayerSlot.get(EquipmentSlot.CHEST.getName()));
        }

        @Override
        public Condition make(JsonObject json) {
            return new EmptySlotCondition(this.getProperty(json, SLOT));
        }

        @Override
        public String getDocumentationDescription() {
            return "Checks if the given slot is empty.";
        }
    }
}
