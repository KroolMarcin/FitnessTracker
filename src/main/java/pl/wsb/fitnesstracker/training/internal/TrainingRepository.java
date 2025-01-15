package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

/**
 * Repository interface for accessing Training data from the database.
 * Provides methods for common query operations based on specific fields.
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves a list of trainings based on the specified activity type.
     *
     * @param activityType the type of activity to filter trainings by
     * @return a list of trainings matching the specified activity type
     */
    List<Training> findTrainingsByActivityType(ActivityType activityType);

    /**
     * Retrieves a list of trainings that were completed after the specified date.
     *
     * @param date the date to filter trainings by; only trainings with an end time after this date are returned
     * @return a list of trainings completed after the specified date
     */
    List<Training> findTrainingsByEndTimeAfter(Date date);

    /**
     * Retrieves a list of trainings associated with a specific user.
     *
     * @param userId the unique identifier of the user
     * @return a list of trainings belonging to the specified user
     */
    List<Training> findTrainingsByUserId(long userId);
}
