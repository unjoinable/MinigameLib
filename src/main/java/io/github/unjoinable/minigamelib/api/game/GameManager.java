package io.github.unjoinable.minigamelib.api.game;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public abstract class GameManager {

    public abstract @NotNull Set<Game> games();

    //default methods

    /**
     * Retrieves the {@link Game} instance associated with the provided {@link Audience}.
     *
     * @param audience The player or audience participating in the game.
     * @return An {@link Optional} containing the {@link Game} instance if found, or an empty {@link Optional} if not found.
     */
    public @NotNull Optional<Game> getGameByPlayer(@NotNull Audience audience) {
        return games().stream().filter(game -> {
            boolean containsPlayer = false;
            for (Audience player : game.players()) {
                containsPlayer = (player == audience);
            }
            return containsPlayer;
        }).findFirst();
    }

    /**
     * Retrieves the {@link Game} instance associated with the provided {@link UUID}.
     *
     * @param uuid The unique identifier of the game instance.
     * @return An {@link Optional} containing the {@link Game} instance if found, or an empty {@link Optional} if not found.
     */
    public @NotNull Optional<Game> getGameByUUID(@NotNull UUID uuid) {
        return games().stream().filter(game -> game.uuid() == uuid).findFirst();
    }

    /**
     * Retrieves the {@link Game} instance with the highest number of players.
     *
     * @return An {@link Optional} containing the {@link Game} instance with the highest number of players, or an
     * empty {@link Optional} if no game has players or there are no games present.
     */
    public @NotNull Optional<Game> getGameWithHighestPlayers() {
        return games().stream().max(Comparator.comparingInt(game -> game.players().size()));
    }

    /**
     * Adds a new {@link Game} instance to the set of games.
     *
     * @param game The {@link Game} instance to be added.
     * @throws IllegalStateException if the {@link Game} instance is already added.
     */
    public void addGameInstance(@NotNull Game game) {
        if (!games().add(game)) {
            throw new IllegalStateException("Game Instance is already added!");
        }
    }

    /**
     * Removes a {@link Game} instance from the set of games.
     *
     * @param game The {@link Game} instance to be removed.
     * @throws IllegalStateException if the {@link Game} instance is not present.
     */
    public void removeGameInstance(@NotNull Game game) {
        if (!games().remove(game)) {
            throw new IllegalStateException("Game Instance is not present!");
        }
    }
}
