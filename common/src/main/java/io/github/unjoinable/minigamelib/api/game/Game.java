package io.github.unjoinable.minigamelib.api.game;

import io.github.unjoinable.minigamelib.api.arena.Arena;
import io.github.unjoinable.minigamelib.api.state.StateSeries;
import io.github.unjoinable.minigamelib.api.team.Team;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Represents a game instance in a minigame.
 */
public sealed interface Game permits GameImpl {

    /**
     * Retrieves the arena associated with the game.
     *
     * @return the arena associated with the game
     */
    @NotNull Arena arena();

    /**
     * Retrieves the unique identifier (UUID) associated with the game instance.
     *
     * @return the unique identifier (UUID) of the game instance
     */
    @NotNull UUID uuid();

    /**
     * Retrieves the state series associated with the game instance.
     *
     * @return the state series associated with the game instance
     */
     @NotNull StateSeries stateSeries();

    //default methods

    /**
     * Retrieves the list of teams participating in the game.
     *
     * @return a set of teams participating in the game
     */
    default @NotNull Set<Team> teams() {
        return arena().locations().keySet();
    }

    /**
     * Iterates over all audiences in the game.
     *
     * @return an iterator over all audiences in the game
     */
    default @NotNull List<Audience> players() {
        List<Audience> audiences = new ArrayList<>();
        for (Team team : teams()) { //iterating on all teams
            for (Audience audience : team) { //iterating on players of a team
                audiences.add(audience); //added... this process is quite long  TODO: find a better way.
            }
        }
        return audiences;
    }

    /**
     * Broadcasts a message to all audiences in the game.
     *
     * @param message the message to be broadcasted
     */
    default void broadcast(@NotNull Component message) {
        for (Audience player : players()) {
            player.sendMessage(message);
        }
    }
}
