package io.github.unjoinable.minigamelib.api.state;

import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

public class ScheduledStateSeries extends StateSeries {
    private final long interval;
    private Task task;

    public ScheduledStateSeries(long interval) {
        this.interval = interval;
    }

    public ScheduledStateSeries() {
        this(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        task = MinecraftServer.getSchedulerManager().scheduleTask(
                this::update,
                TaskSchedule.immediate(),
                TaskSchedule.millis(interval));
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        task.cancel();
    }
}
