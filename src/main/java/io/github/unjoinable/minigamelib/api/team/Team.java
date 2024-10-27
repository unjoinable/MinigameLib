package io.github.unjoinable.minigamelib.api.team;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a team in a game.
 *
 * <p>This interface provides methods to retrieve the team's unique identifier and display name.
 *
 */
public interface Team extends Iterable<Audience> {

    /**
     * Returns the team's display name.
     *
     * <p>The display name is a player-readable/viewable representation of the team, which may be used in game
     * interfaces or chat messages.
     *
     * @return the team's display name
     */
    @NotNull Component displayName();

    /**
     * Returns the maximum number of players allowed in this team.
     *
     * <p>This method provides the maximum number of players that can be added to this team.
     * The actual number of players currently in the team can be obtained using the {@link #players()} method.
     *
     * <p>The maximum number of players is a constant value defined for each team and cannot be changed.
     *
     * @return the maximum number of players allowed in this team
     */
    int maxPlayers();

    /**
     * Returns a list of players currently associated/playing with/within this team.
     *
     * <p>The returned list contains {@link Audience} instances representing the players currently
     * participating in the game and belonging to this team. The order of the players in the list is not
     * guaranteed to be consistent across different method calls.
     *
     * <p>The returned list is a snapshot of the current team members and does not reflect any changes made
     * to the team after this method is called.
     *
     * <p>This is a way to tackle with making code for both Spigot/Paper and Minestom same time.
     *
     * @return a list of {@link Audience} instances representing the players currently in this team
     */
    @NotNull List<Audience> players();


    /**
     * Adds a player to the team.
     *
     * <p>This method adds the specified player to the team. If the team already has the maximum number of players
     * defined by {@link #maxPlayers()}, an {@link IllegalStateException} is thrown.
     *
     * <p>The player is added to the team {@link #players()}.
     *
     * @param audience the {@link Audience} instance representing the player to be added to the team
     * @throws IllegalStateException if the team already has the maximum number of players
     */
    default void addPlayer(@NotNull Audience audience) {
        int size = this.players().size();

        if (size >= maxPlayers()) {
            throw new IllegalStateException("Expected upto " + maxPlayers() + " players");
        }

        this.players().add(audience);
    }

    /**
     * Removes a player from the team.
     *
     * <p>This method removes the specified player from the team. If the player is not found in the team,
     * an {@link IllegalStateException} is thrown.
     *
     * <p>The player is removed from the team {@link #players()}.
     *
     * @param audience the {@link Audience} instance representing the player to be removed from the team
     * @throws IllegalStateException if the player is not found in the team
     */
    default void removePlayer(@NotNull Audience audience) {
        if (!this.players().remove(audience)) {
            throw new IllegalStateException("Player not found! " + audience);
        }
    }

    /**
     * Checks if the specified player is currently associated/playing with/within this team.
     *
     * <p>This method returns true if the specified player is found in the team's list of players, otherwise false.
     * The order of the players in the list is not guaranteed to be consistent across different method calls.
     *
     * @param audience the {@link Audience} instance representing the player to be checked for presence in the team
     * @return true if the player is found in the team, false otherwise
     */
    default boolean isPresent(@NotNull Audience audience) {
        return this.players().contains(audience);
    }

    @Override @NotNull
    default Iterator<Audience> iterator() {
        return this.players().iterator();
    }
}
