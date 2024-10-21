package io.github.unjoinable.minigamelib.team;

import net.kyori.adventure.key.Namespaced;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a team in a game.
 *
 * <p>This interface provides methods to retrieve the team's unique identifier and display name.
 *
 */
public interface Team {

    /**
     * Returns the team's unique identifier.
     *
     * @return the team's unique identifier
     */
    @NotNull Namespaced namespace();

    /**
     * Returns the team's display name.
     *
     * <p>The display name is a player-readable/viewable representation of the team, which may be used in game
     * interfaces or chat messages.
     *
     * @return the team's display name
     */
    @NotNull String displayName();

}
