package io.github.unjoinable.minigamelib.minestom.api.utils;

import io.github.unjoinable.minigamelib.api.utils.Coordinate;
import net.minestom.server.coordinate.Pos;
import org.jetbrains.annotations.NotNull;

public class Utils {

    /**
     * A way to fill the gap between Minestom and Paper.
     *
     * @param coordinate The library coordinate to be converted into Minestom Pos.
     * @return Minestom server Pos.
     */
    public static @NotNull Pos toMinestomPos(@NotNull Coordinate coordinate) {
        return new Pos(
                coordinate.x(),
                coordinate.y(),
                coordinate.z())
                .withPitch(coordinate.pitch())
                .withYaw(coordinate.yaw());
    }
}
