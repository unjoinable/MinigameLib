package io.github.unjoinable.minigamelib.minestom.api.state;

import io.github.unjoinable.minigamelib.api.state.State;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.GlobalEventHandler;
import org.jetbrains.annotations.NotNull;

/**
 * A State that requires/depends on Minecraft Event for executions
 * Automatically registers event to the provided event.
 *
 * @param <T> The type of event this State requires / depends on
 */
public abstract class EventState<T extends Event> extends State implements EventListener<T> {
    private final GlobalEventHandler globalEvtHandler;

    /**
     * Automatically registers event to the provided event handler.
     *
     * @param globalEvtHandler The global event handler of Server instance
     */
    public EventState(@NotNull GlobalEventHandler globalEvtHandler) {
        this.globalEvtHandler =  globalEvtHandler;
    }

    @Override
    public void start() {
        super.start();
        globalEvtHandler.addListener(this);
    }

    @Override
    public void end() {
        super.end();
        globalEvtHandler.removeListener(this);
    }
}
