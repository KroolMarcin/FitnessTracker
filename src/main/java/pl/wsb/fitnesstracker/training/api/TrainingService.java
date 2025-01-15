package pl.wsb.fitnesstracker.training.api;

import java.util.Optional;

public interface TrainingService {

    /**
     * Fetches a training by its unique ID.
     * Returns {@link Optional#empty()} if no training with the specified ID is found.
     *
     * @param trainingId the unique identifier of the training to retrieve
     * @return an {@link Optional} containing the found Training or {@link Optional#empty()} if no match is found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Creates a new training record in the system.
     *
     * @param training the training object to be created
     * @return the newly created training
     */
    Training addTraining(Training training);

    /**
     * Updates an existing training record identified by its ID.
     * 
     * @param trainingId the unique identifier of the training to update
     * @param training the updated training object
     * @return the updated training
     */
    Training updateTraining(Long trainingId, Training training);
}
