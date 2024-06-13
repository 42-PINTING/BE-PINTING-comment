package pinting.comment.repository;

import pinting.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
	Comment save(Comment member);
	void deleteById(Long id);
	void delete(Comment entity);
	Optional<Comment> findById(Long id);
	Optional<Comment> findByMemberId(Long id);
	Optional<Comment> findByPostId(Long id);
	List<Comment> findAll();
}
