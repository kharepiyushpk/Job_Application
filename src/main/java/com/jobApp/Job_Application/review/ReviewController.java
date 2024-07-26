package com.jobApp.Job_Application.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if(reviews != null)
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean isAdded = reviewService.addReview(companyId,review);
        if(isAdded)
            return new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId,reviewId);
        if(review != null)
            return new ResponseEntity<>(review,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review){
        boolean updated = reviewService.updateReview(companyId, reviewId,review);
        if(updated) {
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isDeleted)
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Found",HttpStatus.NOT_FOUND);
    }

}
