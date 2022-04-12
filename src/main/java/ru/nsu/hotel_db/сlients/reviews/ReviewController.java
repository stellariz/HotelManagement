package ru.nsu.hotel_db.сlients.reviews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.сlients.ClientService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ClientService clientService;

    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviewsList", reviewService.getAllReviews());
        return "reviewsPage";
    }

    @GetMapping("/addReview")
    public String getReviewForm(Model model){
        model.addAttribute("reviewDTO", new ReviewDTO());
        return "reviewForm";
    }

    @PostMapping("/addReview")
    public String updateReviews(@ModelAttribute("reviewDTO") @Valid ReviewDTO reviewDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "reviewForm";
        }
        if (clientService.getClientByName(reviewDTO.getClientName()).isEmpty()){
            log.warn("There is no client: {}", reviewDTO.getClientName());
            model.addAttribute("errorMessage", "Sorry, but you haven't visited hotel yet!");
            return "reviewForm";
        }
        reviewService.addNewReview(reviewDTO, clientService.getClientByName(reviewDTO.getClientName()).get());
        return "redirect:/reviews";
    }

    @GetMapping("/{clientName}")
    public String getClientReviews(@PathVariable("clientName") String clientName, Model model){
        model.addAttribute("reviewsList", reviewService.getClientReviews(clientName));
        return "reviewsPage";
    }
}
