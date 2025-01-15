package pl.wsb.fitnesstracker.training.api;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.List;

public interface TrainingProvider {
    /**
     * Retrieves a list of all available trainings.
     *
     * @return a list containing all trainings
     */
    List<Training> findAllTrainings();

    /**
     * Fetches trainings filtered by a specific activity type.
     *
     * @param activityType the type of activity to filter trainings by
     * @return a list of trainings that match the specified activity type
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);

    /**
     * Retrieves trainings that were completed after a specified date.
     *
     * @param afterTime a string representing the date in the format yyyy-MM-dd
     * @return a list of trainings completed after the given date
     */
    List<Training> getTrainingsFinishedAfterTime(String afterTime);

    /**
     * Fetches all trainings associated with a specific user.
     *
     * @param userId the unique identifier of the user
     * @return a list of trainings linked to the specified user
     */
    List<Training> getTrainingsByUserId(Long userId);
}