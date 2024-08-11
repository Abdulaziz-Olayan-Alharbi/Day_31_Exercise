package com.example.capstone_1.Service;

import com.example.capstone_1.Model.Comment;
import com.example.capstone_1.Model.Order;
import com.example.capstone_1.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {



    final CommentRepository commentRepository;

    final ProductService productService;

    final OrderService orderService;

    public ArrayList<Comment> getComments() {
        return new ArrayList<Comment>(commentRepository.findAll());
    }

    public String addComment(Comment comment) {
        for (Order order : orderService.getOrders()) {
            if (order.getUserId() == comment.getUserId()) {
                if (order.getProductId() == comment.getProductId()) {
                    commentRepository.save(comment);
                    return "true";
                }
                return "Product does not exist";
            }
            return "User does not exist";
        }
        return "There is no Order";
    }

    public String updateComment(int id,Comment comment) {
        Comment c = commentRepository.getById(id);
        if (c != null){
            for (Order order : orderService.getOrders()) {
                if (order.getUserId() == comment.getUserId()) {
                    if (order.getProductId() == comment.getProductId()) {
                        c.setText(comment.getText());
                        c.setScore(comment.getScore());
                        c.setProductId(c.getProductId());
                        c.setUserId(c.getUserId());
                        commentRepository.save(c);
                        return "true";
                    }
                    return "Product does not exist";
                }
                return "User does not exist";
            }
            return "There is no Order";
        }
        return "Comment does not exist";
    }
    public boolean deleteComment(int id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ArrayList<Comment> getCommentsByProductId(int productId) {
        ArrayList<Comment> commentsOfProduct = new ArrayList<>();
        for (Comment comment : getComments()) {
            if (comment.getProductId() == productId) {
                commentsOfProduct.add(comment);
            }
        }
        return commentsOfProduct;
    }

    public double getAverageRatingOfProduct(int productId) {
        double averageRating = 0;
        for (Comment comment : getCommentsByProductId(productId)) {
            averageRating += comment.getScore();
        }
        return averageRating / getCommentsByProductId(productId).size();
    }
}
