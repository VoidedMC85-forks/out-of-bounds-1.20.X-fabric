package net.voidedmc85.outofbounds.config;

public class OutOfBoundsConfig {
    private static float teleportChance = 1.0f; // Default value
    private static boolean teleportCommandEnabled = true; // Track if the command is enabled

    public static void setTeleportChance(float chance) {
        teleportChance = chance;
    }

    public static float getTeleportChance() {
        return teleportChance;
    }

    public static boolean isTeleportCommandEnabled() {
        return teleportCommandEnabled;
    }

    public static void setTeleportCommandEnabled(boolean enabled) {
        teleportCommandEnabled = enabled;
    }
}
