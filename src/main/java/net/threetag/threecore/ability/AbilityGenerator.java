package net.threetag.threecore.ability;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.threetag.threecore.ThreeCore;

import java.util.function.Supplier;

public class AbilityGenerator implements Supplier<Ability> {

    public final String key;
    public AbilityType abilityType;
    public ResourceLocation abilityId;
    public final JsonObject jsonObject;

    public AbilityGenerator(String key, AbilityType abilityType, JsonObject jsonObject) {
        this.key = key;
        this.abilityType = abilityType;
        this.jsonObject = jsonObject;
    }

    public AbilityGenerator(String key, ResourceLocation abilityId, JsonObject jsonObject) {
        this.key = key;
        this.abilityId = abilityId;
        this.jsonObject = jsonObject;
    }

    public AbilityType getAbilityType() {
        return abilityType != null ? this.abilityType : AbilityType.REGISTRY.getValue(this.abilityId);
    }

    public Ability create() {
        if(this.getAbilityType() == null) {
            ThreeCore.LOGGER.error("Ability type " + this.abilityId.toString() + " does not exist!");
            return null;
        }
        Ability ability = this.getAbilityType().create(this.key);
        ability.readFromJson(this.jsonObject);
        return ability;
    }

    @Override
    public Ability get() {
        return this.create();
    }
}
