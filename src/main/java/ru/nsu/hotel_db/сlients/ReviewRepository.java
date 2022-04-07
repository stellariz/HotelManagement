package ru.nsu.hotel_db.сlients;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> getReviewByClientClientId(Long clientId);
}
