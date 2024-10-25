package io.github.unjoinable.minigamelib.api.arena;

import io.github.unjoinable.minigamelib.api.team.Team;
import io.github.unjoinable.minigamelib.api.utils.Coordinate;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an arena in a minigame.
 *
 * @param name          The name of the arena.
 * @param fallbackLoc   The fallback location / spawn for the arena.
 * @param worldPath     The path to the world where the arena is located.
 * @param locations     The locations associated with each team in the arena.
 */
public record ArenaImpl(@NotNull String name,
                        @NotNull Coordinate fallbackLoc,
                        @NotNull Path worldPath,
                        @NotNull Map<Team, Coordinate> locations) implements Arena {

    /**
     * Creates a new instance of the ArenaImpl record with default locations for each team.
     *
     * @param name          The name of the arena.
     * @param fallbackLoc   The fallback location / spawn for the arena.
     * @param worldPath     The path to the world where the arena is located.
     * @return              A new instance of the ArenaImpl record with default locations for each team.
     */
    public static Arena create(@NotNull String name, @NotNull Coordinate fallbackLoc, @NotNull Path worldPath) {
        return new ArenaImpl(name, fallbackLoc, worldPath, new HashMap<>());
    }
}
