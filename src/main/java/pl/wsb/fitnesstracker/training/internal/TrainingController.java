package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Fetches the full list of trainings available in the system.
     *
     * @return a ResponseEntity containing a list of all training records
     */
    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        return new ResponseEntity<>(trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList(), HttpStatus.OK);
    }

    /**
     * Adds a new training record to the system.
     *
     * @param trainingDto data transfer object representing the training details
     * @return a ResponseEntity with the created training and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody TrainingDto trainingDto) {
        Training createdTraining = trainingService.addTraining(trainingMapper.toEntity(trainingDto));
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }

    /**
     * Retrieves all trainings assigned to a specific user.
     *
     * @param userId the ID of the user whose trainings are to be fetched
     * @return a ResponseEntity containing a list of training DTOs for the user
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(trainingService.getTrainingsByUserId(userId).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Filters trainings by a specified activity type.
     *
     * @param activityType the activity type to filter the trainings by
     * @return a ResponseEntity containing trainings of the specified activity type
     */
    @GetMapping("/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return new ResponseEntity<>(trainingService.getTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Retrieves all trainings completed after a certain date.
     *
     * @param afterTime date string in yyyy-MM-dd format representing the filter criteria
     * @return a ResponseEntity with a list of trainings finished after the specified date
     */
    @GetMapping("/finished/{afterTime}")
    public ResponseEntity<List<TrainingDto>> getTrainingsFinishedAfterTime(@PathVariable String afterTime) {
        return new ResponseEntity<>(trainingService.getTrainingsFinishedAfterTime(afterTime).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Modifies an existing training record with new details.
     *
     * @param trainingId the ID of the training to be updated
     * @param trainingDto the new details of the training
     * @return a ResponseEntity containing the updated training DTO
     */
    @PutMapping("/{trainingId}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingService.updateTraining(trainingId, trainingMapper.toEntity(trainingDto));
        return new ResponseEntity<>(trainingMapper.toDto(updatedTraining), HttpStatus.OK);
    }
}
