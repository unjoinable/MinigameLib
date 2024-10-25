package io.github.unjoinable.minigamelib.api.game;

import io.github.unjoinable.minigamelib.api.arena.Arena;
import io.github.unjoinable.minigamelib.api.state.StateSeries;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Represents a game instance
 *
 * @param arena       The Arena where the game will be played.
 * @param stateSeries The StateSeries that play outs the game.
 * @param uuid        A unique identifier for the game instance.
 */
public record GameImpl(@NotNull Arena arena,
                       @NotNull StateSeries stateSeries,
                       @NotNull UUID uuid) implements Game {

    /**
     * Creates a new instance of the Game with the provided Arena.
     *
     * @param arena The Arena where the game will be played.
     * @return A new instance of the GameImpl class with the provided Arena and a randomly generated UUID for the game.
     */
    public static Game create(@NotNull Arena arena) {
        return new GameImpl(arena, new StateSeries(), UUID.randomUUID());
    }
}
