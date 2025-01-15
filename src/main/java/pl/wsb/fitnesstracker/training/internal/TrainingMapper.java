package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {

    /**
     * Converts a Training entity into a TrainingDto object.
     *
     * @param training the Training entity to be converted
     * @return a TrainingDto object containing the mapped data
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    /**
     * Converts a TrainingDto object into a Training entity.
     *
     * @param trainingDto the TrainingDto object to be converted
     * @return a Training entity constructed from the DTO data
     */
    Training toEntity(TrainingDto trainingDto) {
        return new Training(
                trainingDto.user(),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }
}
