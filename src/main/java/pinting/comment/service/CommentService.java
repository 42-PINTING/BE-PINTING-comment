package pinting.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pinting.comment.domain.Comment;
import pinting.comment.repository.CommentRepository;

@Service
@Transactional
public class CommentService {
	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public Long createComment(Comment comment) {
		commentRepository.save(comment);
		return comment.getId();
	}

	public Comment readOneCommentById(Long id) {
		return commentRepository.findById(id).orElseGet(Comment::new);
	}

	public Comment updateComment(Comment comment) {
		return commentRepository.save(comment);
	}

	public void deleteCommentById(Long id) {
		commentRepository.deleteById(id);
	}
}
