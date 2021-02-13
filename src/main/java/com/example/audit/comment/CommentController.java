package com.example.audit.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

  @Autowired
  CommentRepository commentRepository;

  @PostMapping("/api/1.0/comments")
  Comment post(@RequestBody Comment comment){
    return commentRepository.save(comment);
  }

  @PutMapping("/api/1.0/comments/{id}")
  Comment update(@RequestBody Comment comment, @PathVariable long id){
    Comment inDB = commentRepository.getOne(id);
    inDB.setComment(comment.getComment());
    return commentRepository.save(inDB);
  }
  
}
