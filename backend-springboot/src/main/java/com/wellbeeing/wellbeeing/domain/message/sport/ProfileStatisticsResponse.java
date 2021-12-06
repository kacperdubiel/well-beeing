package com.wellbeeing.wellbeeing.domain.message.sport;

import com.wellbeeing.wellbeeing.domain.sport.Training;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class ProfileStatisticsResponse {
    private double totalCalories;
    private double totalCaloriesAverage;
    private double userCalories;
    private double userCaloriesBetterThanPercent;

    private double totalTrainingMinutes;
    private double totalTrainingMinutesAverage;
    private double userTrainingMinutes;
    private double userTrainingMinutesBetterThanPercent;

    private double avgTrainingDays;
    private double userTrainingDays;

    private int userOverallLongestTrainingStreak;
    private List<LocalDate> userOverallLongestTrainingStreakDates;
    private Training userFavouriteTraining;

    private int userFavouriteTrainingCount;

    private LocalDate from;
    private LocalDate to;
    private int days;

}
