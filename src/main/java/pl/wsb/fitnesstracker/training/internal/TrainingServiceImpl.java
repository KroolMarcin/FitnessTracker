package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.TimeZone;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {
    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        log.info("Fetching Training with ID {}", trainingId);
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> findAllTrainings() {
        log.info("Retrieving all trainings");
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        log.info("Fetching trainings for user with ID {}", userId);
        return trainingRepository.findTrainingsByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        log.info("Retrieving trainings with activity type {}", activityType);
        return trainingRepository.findTrainingsByActivityType(activityType);
    }

    @Override
    public List<Training> getTrainingsFinishedAfterTime(String afterTime) {
        log.info("Fetching trainings finished after {}", afterTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date afterDate;
        try {
            afterDate = sdf.parse(afterTime);
        } catch (ParseException e) {
            log.error("Invalid date format provided: {}", afterTime);
            throw new InvalidDateFormatException(afterTime);
        }

        return trainingRepository.findTrainingsByEndTimeAfter(afterDate);
    }

    @Override
    public Training addTraining(final Training training) {
        log.info("Creating new Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training already has a DB ID. Creation not allowed!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long trainingId, Training training) {
        log.info("Updating Training with ID {}", trainingId);
        Training existingTraining = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));
        existingTraining.setUser(training.getUser());
        existingTraining.setStartTime(training.getStartTime());
        existingTraining.setEndTime(training.getEndTime());
        existingTraining.setActivityType(training.getActivityType());
        existingTraining.setAverageSpeed(training.getAverageSpeed());
        return trainingRepository.save(existingTraining);
    }

    private static class InvalidDateFormatException extends RuntimeException {
        public InvalidDateFormatException(String date) {
            super("The provided date format is invalid: " + date);
        }
    }
}
