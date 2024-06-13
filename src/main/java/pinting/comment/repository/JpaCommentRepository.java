package pinting.comment.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import pinting.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCommentRepository implements CommentRepository {
	EntityManager em;

	public JpaCommentRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Comment save(Comment comment) {
		em.persist(comment);
		return comment;
	}

	@Override
	public void deleteById(Long id) {
		Assert.notNull(id, "The given id must not be null");
		this.findById(id).ifPresent(this::delete);
	}

	@Override
	public void delete(Comment entity) {
		Assert.notNull(entity, "Entity must not be null");
		Comment existing = this.em.find(Comment.class, entity.getId());
		if (existing != null) {
			this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
		}
	}

	@Override
	public Optional<Comment> findById(Long id) {
		Comment user = em.find(Comment.class, id);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<Comment> findByMemberId(Long id) {
		List<Comment> result = em.createQuery("select m from Comment m where m.memberId = :id", Comment.class)
				.setParameter("id", id)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public Optional<Comment> findByPostId(Long id) {
		List<Comment> result = em.createQuery("select m from Comment m where m.postId = :id", Comment.class)
				.setParameter("id", id)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Comment> findAll() {
		return em.createQuery("select m from Comment m", Comment.class)
				.getResultList();
	}
}
