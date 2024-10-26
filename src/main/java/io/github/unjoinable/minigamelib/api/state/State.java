package io.github.unjoinable.minigamelib.api.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

/**
 * Represents a state in a finite state machine.
 * This abstract class provides a framework for implementing states
 * with lifecycle methods and state tracking.
 * <p>
 * Inspired by github.com/Minikloon/FSMgasm
 * @author Minikloon
 */
public abstract class State {
    private static final Logger logger = LoggerFactory.getLogger(State.class);
    private boolean started = false;
    private boolean ended = false;
    private boolean frozen = false;
    private boolean updating = false;
    private Instant startInstant;

    /**
     * Returns the duration of the state.
     *
     * @return the duration of the state.
     */
     protected abstract Duration duration();

    /**
     * Called when the state is started.
     * Subclasses should implement this method to define behavior when the state begins.
     */
    protected abstract void onStart();

    /**
     * Called to update the state.
     * Subclasses should implement this method to define behavior during the state's lifecycle.
     */
    protected abstract void onUpdate();

    /**
     * Called when the state is ended.
     * Subclasses should implement this method to define behavior when the state concludes.
     */
    protected abstract void onEnd();

    /**
     * Checks if the state has been started.
     *
     * @return true if the state has been started, false otherwise
     */
    public boolean started() {
        return started;
    }

    /**
     * Checks if the state has ended.
     *
     * @return true if the state has ended, false otherwise
     */
    public boolean ended() {
        return ended;
    }

    /**
     * Checks if the state is frozen.
     *
     * @return true if the state is frozen, false otherwise
     */
    public boolean frozen() {
        return frozen;
    }

    //Additional methods...

    /**
     * Freezes/Unfreezes the state as per the argument
     *
     * @param frozen If the state should be frozen.
     */
    public void freeze(boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * Starts the state if it hasn't been started already.
     * It calls the onStart() method.
     */
    public void start() {
        synchronized (this) {
            if (started || ended) {
                return;
            }

            started = true;
        }

        startInstant = Instant.now();

        try {
            onStart();
        } catch (Exception e) {
            logger.warn("Exception during {} start", this.getClass().getName(), e);
        }
    }

    /**
     * Ends the current state if it has been started and not already ended.
     * Any exceptions during the update are logged but not propagated.
     */
    public void end() {
        synchronized (this) {
            if (!started || ended) {
                return;
            }
            ended = true;
        }

        try {
            onEnd();
        } catch (Exception e) {
            logger.warn("Exception during {} end", this.getClass().getName(), e);
        }
    }

    /**
     * Updates the state if it has been started and not ended.
     * If the state is not frozen and ready to end, it will end the state.
     * Otherwise, it calls the onUpdate() method.
     * Any exceptions during the update are logged but not propagated.
     */
    public void update() {
        synchronized (this) {
            if (!started || ended || updating) {
                return;
            }
            updating = true;
        }

        try {
            onUpdate();
        } catch (Exception e) {
            logger.warn("Exception during {} update", this.getClass().getName(), e);
        }

        updating = false;
    }

    /**
     * Calculates the remaining duration of the state.
     * If somehow remaining time is less than zero
     * We consider it zero.
     *
     * @return the remaining duration.
     */
    private Duration remainingDuration() {
        Duration timeElapsed = Duration.between(startInstant, Instant.now());
        Duration remaining = this.duration().minus(timeElapsed);

        return remaining.isNegative() ? Duration.ZERO : remaining;
    }

    /**
     * Checks if the state is ready to end.
     *
     * @return true if the state is ready to end, false otherwise
     */
    public boolean isReadytoEnd() {
        return ended || remainingDuration() == Duration.ZERO;
    }
}
