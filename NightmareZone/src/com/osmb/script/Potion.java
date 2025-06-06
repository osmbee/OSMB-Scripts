package com.osmb.script;

import com.osmb.api.item.ItemID;
import com.osmb.api.item.ItemSearchResult;
import com.osmb.api.utils.UIResultList;
import com.osmb.api.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public enum Potion {
    PRAYER_POTION("Prayer potion", ItemID.PRAYER_POTION4, ItemID.PRAYER_POTION3, ItemID.PRAYER_POTION2, ItemID.PRAYER_POTION1),
    ABSORPTION_POTION(1000, "Absorption potion", ItemID.ABSORPTION_4, ItemID.ABSORPTION_3, ItemID.ABSORPTION_2, ItemID.ABSORPTION_1),
    SUPER_COMBAT("Super combat potion", ItemID.SUPER_COMBAT_POTION4, ItemID.SUPER_COMBAT_POTION3, ItemID.SUPER_COMBAT_POTION2, ItemID.SUPER_COMBAT_POTION1),
    OVERLOAD(1500, "Overload potion", ItemID.OVERLOAD_4, ItemID.OVERLOAD_3, ItemID.OVERLOAD_2, ItemID.OVERLOAD_1),
    SUPER_RANGING_POTION(250, "Super ranging potion", ItemID.SUPER_RANGING_4, ItemID.SUPER_RANGING_3, ItemID.SUPER_RANGING_2, ItemID.SUPER_RANGING_1),
    SUPER_MAGIC_POTION(250, "Super magic potion", ItemID.SUPER_MAGIC_POTION_4, ItemID.SUPER_MAGIC_POTION_3, ItemID.SUPER_MAGIC_POTION_2, ItemID.SUPER_MAGIC_POTION_1),
    SUPER_STRENGTH_POTION("Super strength poton", ItemID.SUPER_STRENGTH4, ItemID.SUPER_STRENGTH3, ItemID.SUPER_STRENGTH2, ItemID.SUPER_STRENGTH1),
    RANGING_POTION("Ranging potion", ItemID.RANGING_POTION4, ItemID.RANGING_POTION3, ItemID.RANGING_POTION2, ItemID.RANGING_POTION1);


    private final int[] itemIDs;
    private final String name;
    private final int costPerDose;

    Potion(int costPerDose, String potionName, int... itemIDs) {
        this.itemIDs = itemIDs;
        this.costPerDose = costPerDose;
        this.name = potionName;
    }

    Potion(String potionName, int... itemIDs) {
        this.itemIDs = itemIDs;
        this.costPerDose = -1;
        this.name = potionName;
    }

    /**
     * Checks if the given itemID is a multi consumable.
     *
     * @param itemID the item ID to check
     * @return true if the itemID belongs to a multi consumable, false otherwise
     */
    public static Potion getMultiConsumable(int itemID) {
        for (Potion consumable : Potion.values()) {
            for (int id : consumable.getItemIDs()) {
                if (id == itemID) {
                    return consumable;
                }
            }
        }
        return null;
    }

    public static Potion getPotionForID(int itemID) {
        for (Potion potion : Potion.values()) {
            for (int id : potion.getItemIDs()) {
                if (id == itemID) {
                    return potion;
                }
            }
        }
        return null;
    }

    private static int getArrayIndex(int[] array, int index) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == index) {
                return i;
            }
        }
        return -1;
    }

    public boolean isBarrel() {
        return costPerDose != -1;
    }

    public int getCostPerDose() {
        return costPerDose;
    }

    public String getName() {
        return name;
    }

    public int[] getItemIDs() {
        return itemIDs;
    }

    public int getDose(int id) {
        for (int i = 0; i < itemIDs.length; i++) {
            if (itemIDs[i] == id) {
                return 4 - i;
            }
        }
        throw new RuntimeException("Incorrect ID (" + id + ") for type: " + this);
    }

    public int getFullID() {
        return itemIDs[0];
    }

    @Override
    public String toString() {
        return name;
    }
}
