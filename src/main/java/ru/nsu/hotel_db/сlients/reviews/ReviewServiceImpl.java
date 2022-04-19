package ru.nsu.hotel_db.сlients.reviews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public Review addNewReview(ReviewDTO reviewDTO, Client client) {
        Review review = new Review(null, client, reviewDTO.getReview(), reviewDTO.getRating(), null);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getClientReviews(String name) {
        return reviewRepository.getReviewByClientName(name);
    }

    @Override
    public List<Review> getAngryReviews() {
        return reviewRepository.getAngryReviews();
    }
}
