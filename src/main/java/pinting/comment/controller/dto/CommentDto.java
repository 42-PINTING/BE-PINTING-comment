package pinting.comment.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private Long memberId;
	private Long postId;
	private String content;
	private int status;
}
