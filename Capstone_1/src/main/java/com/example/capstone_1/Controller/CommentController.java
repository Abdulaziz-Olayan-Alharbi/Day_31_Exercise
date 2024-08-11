package com.example.capstone_1.Controller;

import com.example.capstone_1.Api.ApiResponse;
import com.example.capstone_1.Model.Comment;
import com.example.capstone_1.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getComments() {
        return ResponseEntity.status(200).body(commentService.getComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment , Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (commentService.addComment(comment).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Comment Added Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(commentService.addComment(comment)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable int id,@Valid @RequestBody Comment comment , Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (commentService.updateComment(id, comment).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Comment Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(commentService.updateComment(id, comment)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) {
        if (commentService.deleteComment(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Comment Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Comment not found"));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getCommentsByProduct(@PathVariable int productId) {
        return ResponseEntity.status(200).body(commentService.getCommentsByProductId(productId));
    }

    @GetMapping("/average/{productId}")
    public ResponseEntity getAverageCommentsByProduct(@PathVariable int productId) {
        return ResponseEntity.status(200).body(commentService.getAverageRatingOfProduct(productId));
    }



















}
