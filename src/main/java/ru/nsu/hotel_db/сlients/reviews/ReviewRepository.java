package ru.nsu.hotel_db.сlients.reviews;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> getReviewByClientName(String clientName);

    List<Review> findAll();

    @Query(value = "SELECT * from Review where rating <= 3",
            nativeQuery = true)
    List<Review> getAngryReviews();

    @Query(value = "select * from REVIEW join (select CLIENT_ID, CHECK_IN_TIME from CLIENT join ROOM R on R.ROOM_ID = CLIENT.ROOM_ID where R.ROOM_ID = ?1 and CHECK_OUT_TIME > SYSDATE) current_visitor on REVIEW.CLIENT_ID = current_visitor.CLIENT_ID where REVIEW_DATE > CHECK_IN_TIME",
            nativeQuery = true)
    List<Review> getReviewsFromCurrentVisitor(Long roomId);
}
