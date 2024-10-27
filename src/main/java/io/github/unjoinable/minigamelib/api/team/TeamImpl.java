package io.github.unjoinable.minigamelib.api.team;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This an example team... though I recommend you implement Team your self.
 * Represents a team in a game.
 *
 * <p>This record provides a simple and immutable representation of a team, with the following parameters:
 * @param displayName The display name of the team, which will be used to identify the team.
 * @param maxPlayers The maximum number of players that can be part of this team.
 * @param players A list of players currently associated/playing with/within this team.
 */
public record TeamImpl(@NotNull Component displayName,
                       int maxPlayers,
                       @NotNull List<Audience> players) implements Team {

    /**
     * Creates a new instance of the TeamImpl record with the specified display name and maximum number of players.
     *
     * @param displayName The display name of the team, which will be used to identify the team.
     * @param maxPlayers The maximum number of players that can be part of this team.
     * @return A new instance of the TeamImpl record with the specified display name and maximum number of players.
     */
    public static Team create(@NotNull Component displayName, int maxPlayers) {
        return new TeamImpl(displayName, maxPlayers, new ArrayList<>());
    }
}
