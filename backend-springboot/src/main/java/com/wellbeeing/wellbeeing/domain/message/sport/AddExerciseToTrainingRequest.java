package com.wellbeeing.wellbeeing.domain.message.sport;

public class AddExerciseToTrainingRequest {
    private int reps;
    private int time_seconds;
    private int series;

    public AddExerciseToTrainingRequest(int reps, int time_seconds, int series) {
        this.reps = reps;
        this.time_seconds = time_seconds;
        this.series = series;
    }

    public int getReps() {
        return reps;
    }

    public int getTime_seconds() {
        return time_seconds;
    }

    public int getSeries() {
        return series;
    }
}
