package ru.nsu.hotel_db.сlients.reviews;

import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

public interface ReviewService {
    Review addNewReview(ReviewDTO reviewDTO, Client client);
    List<Review> getAllReviews();
    List<Review> getClientReviews(String name);
}
