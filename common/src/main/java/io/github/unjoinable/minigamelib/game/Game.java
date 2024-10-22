package io.github.unjoinable.minigamelib.game;

import io.github.unjoinable.minigamelib.arena.Arena;
import io.github.unjoinable.minigamelib.team.Team;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a game instance in a minigame.
 */
public interface Game extends Iterable<Audience> {

    /**
     * Retrieves the arena associated with the game.
     *
     * @return the arena associated with the game
     */
    @NotNull Arena arena();

    /**
     * Retrieves the list of teams participating in the game.
     *
     * @return a list of teams participating in the game
     */
    @NotNull List<Team> teams();

    /**
     * Iterates over all audiences in the game.
     *
     * @return an iterator over all audiences in the game
     */
    @Override
    @NotNull
    default Iterator<Audience> iterator() {
        List<Audience> audiences = new ArrayList<>();
        for (Team team : teams()) {
            audiences.addAll(team.players());
        }
        return audiences.iterator();
    }

    /**
     * Broadcasts a message to all audiences in the game.
     *
     * @param message the message to be broadcasted
     */
    default void broadcast(@NotNull Component message) {
        for (Audience audience : this) {
            audience.sendMessage(message);
        }
    }
}
