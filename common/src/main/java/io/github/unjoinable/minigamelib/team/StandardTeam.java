package io.github.unjoinable.minigamelib.team;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A record representing a standard team in a game.
 *
 * @param displayName The display name of the team.
 * @param maxPlayers The maximum number of players allowed in the team.
 * @param players The list of players currently in the team.
 */
public record StandardTeam(@NotNull String displayName,
                           int maxPlayers,
                           @NotNull List<Audience> players) implements Team {

    /**
     * Creates a new instance of StandardTeam with the given display name and maximum number of players.
     * The players list is initialized as an empty ArrayList.
     *
     * @param displayName The display name of the team.
     * @param maxPlayers The maximum number of players allowed in the team.
     * @return A new instance of StandardTeam.
     */
    public static StandardTeam of(@NotNull String displayName, int maxPlayers) {
        return new StandardTeam(displayName, maxPlayers, new ArrayList<>());
    }
}
