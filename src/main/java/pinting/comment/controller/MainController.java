package pinting.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pinting.comment.controller.dto.CommentDto;
import pinting.comment.domain.Comment;
import pinting.comment.service.CommentService;

@Controller
@ResponseBody
public class MainController {

	private final CommentService commentService;

	public MainController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/comments/{id}")
	public Comment readOneById(@PathVariable Long id) {
		return commentService.readOneCommentById(id);
	}

	@PostMapping("/comments")
	public String createComment(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setMemberId(commentDto.getMemberId());
		comment.setPostId(commentDto.getPostId());
		comment.setContent(commentDto.getContent());
		comment.setStatus(1);

		Long id = commentService.createComment(comment);

		return "successful creation for id: " + id;
	}

	@PutMapping("/comments/{id}")
	public Comment updateComment(@PathVariable Long id, CommentDto commentDto) {
		Comment comment = commentService.readOneCommentById(id);
		comment.setMemberId(commentDto.getMemberId());
		comment.setPostId(commentDto.getPostId());
		comment.setContent(commentDto.getContent());

		return commentService.updateComment(comment);
	}

	@DeleteMapping("/comments/{id}")
	public void deleteComment(@PathVariable Long id) {
		commentService.deleteCommentById(id);
	}
}
