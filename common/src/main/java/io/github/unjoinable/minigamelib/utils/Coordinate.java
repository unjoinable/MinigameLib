package io.github.unjoinable.minigamelib.utils;

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
}
