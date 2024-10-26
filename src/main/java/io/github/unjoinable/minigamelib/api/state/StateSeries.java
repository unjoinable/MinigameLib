package io.github.unjoinable.minigamelib.api.state;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a series of states that can be executed in sequence.
 * This class extends the State class and manages a list of states.
 */
public class StateSeries extends StateContainer {
    private static final Logger logger = LoggerFactory.getLogger(StateSeries.class);
    protected int current = 0;
    protected boolean skipping = false;
    protected final List<State> states;

    /**
     * Constructs a new StateSeries with given list of states.
     * @param states The initializing states.
     */
    public StateSeries(List<State> states) {
        this.states = states;
    }

    /**
     * Constructs a new StateSeries with an empty list of states.
     * The series is initialized with no states, and states can be added later.
     */
    public StateSeries() {
        this.states = new ArrayList<>();
    }

    /**
     * Marks the current state for skipping.
     * When a state is skipped, the series will move to the next state
     * without waiting for the current state to complete naturally.
     */
    public void skip() {
        skipping = true;
    }

    /**
     * Adds a new state to the series, inserting it immediately after the current state.
     * This allows for dynamic modification of the series during execution.
     *
     * @param state The new State to be added to the series.
     */
    public void addNext(State state) {
        this.states.add(current + 1, state);
    }

    /**
     * Adds a new state to the series, inserting it immediately after the current state.
     * This allows for dynamic modification of the series during execution.
     *
     * @param newStates The new State to be added to the series.
     */
    public void addNext(List<State> newStates) {
        int i = 1;
        for (State state : newStates) {
            states.add(current + i, state);
            i++;
        }
    }

    @Override
    protected @NotNull Duration duration() {
        Duration total = Duration.ZERO;
        for (State state : states) {
            total = total.plus(state.duration());
        }
        return total;
    }

    @Override
    protected void onStart() {
        if (states.isEmpty()) {
            end();
            return;
        }

        try {
            states.get(current).start();
        } catch (Exception e) {
            logger.warn("State doesn't exist at index {} whilst starting.", current, e);
        }
    }

    @Override
    protected void onUpdate() {
        try {
            State state = states.get(current);

            state.update();

            if(state.isReadytoEnd() && !state.frozen() || skipping) {
                if (skipping) {
                    skipping = false;
                }

                state.end();
                if(++current >= states.size()) {
                    end();
                    return;
                }
            }
        } catch (Exception e) {
            logger.warn("State doesn't exist at index {} whilst updating.", current, e);
        }

        try {
            states.get(current).start();
        } catch (Exception e) {
            logger.warn("State doesn't exist at index {} whilst updating.", current, e);
        }
    }

    @Override
    protected void onEnd() {
        if(current < states.size()) {
            try {
                states.get(current).end();
            } catch (Exception e) {
                logger.warn("State doesn't exist at index {} whilst ending.", current, e);
            }
        }
    }

    @Override
    public boolean isReadytoEnd() {
        return (current == states.size() - 1) && states.get(current).isReadytoEnd();
    }
}
