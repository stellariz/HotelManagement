package ru.nsu.hotel_db.сlients.reviews;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> getReviewByClientName(String clientName);
    List<Review> findAll();
}
