package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.user.api.User;

import jakarta.annotation.Nullable;
import java.util.Date;

/**
 * Represents the data transfer object (DTO) for training information.
 * This class is used to transfer training details between the client
 * and the server.
 *
 * @param Id           the unique identifier of the training; can be null for new entries
 * @param user         the user associated with the training
 * @param startTime    the start time of the training session
 * @param endTime      the end time of the training session
 * @param activityType the type of activity performed during the training
 * @param distance     the distance covered during the training, in kilometers
 * @param averageSpeed the average speed during the training session, in kilometers per hour
 */
record TrainingDto(
    @Nullable Long Id, 
    User user, 
    Date startTime, 
    Date endTime, 
    ActivityType activityType, 
    double distance, 
    double averageSpeed
) {}
