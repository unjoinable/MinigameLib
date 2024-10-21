package io.github.unjoinable.minigamelib.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a 3D coordinate with yaw and pitch angles.
 * This 3D coordinate is to act a bridge between Spigot and Minestom.
 *
 * @param x The x-coordinate.
 * @param y The y-coordinate.
 * @param z The z-coordinate.
 * @param yaw The yaw angle in degrees, representing the rotation around the y-axis.
 * @param pitch The pitch angle in degrees, representing the rotation around the x-axis.
 */
public record Coordinate(double x,
                         double y,
                         double z,
                         float yaw,
                         float pitch) {

    public static Coordinate ZERO = new Coordinate(0D, 0D, 0D, 0F, 0F);

    /**
     * Creates a new Coordinate instance with the specified x, y, and z coordinates,
     * and default yaw and pitch values of 0.
     *
     * @param x The x-coordinate of the new Coordinate instance.
     * @param y The y-coordinate of the new Coordinate instance.
     * @param z The z-coordinate of the new Coordinate instance.
     *
     * @return A new Coordinate instance with the specified x, y, and z coordinates,
     *         and default yaw and pitch values of 0.
     */
    public static @NotNull Coordinate from(double x, double y, double z) {
        return new Coordinate(x, y, z, 0f, 0f);
    }
}
