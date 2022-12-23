import React from "react";
import styled from "styled-components";

// isCommentMode 상태로 댓글 입력창 나타났다 사라졌다 구현하기
const CommentContainer = styled.li`
  padding: 6px 0;
  border-bottom: 1px solid #f1f2f3;
  font-size: 13px;

  .content {
    padding-left: 10px;
    padding-right: 3px;
  }

  .commenter {
    padding-left: 3px;
    color: #0074cc;
  }

  .date {
    padding-left: 3px;
    color: #6a737c;
  }
`;

const Comment = ({ content, commenter, date }) => {
  return (
    <>
      <CommentContainer>
        <span className="content">{content}</span>-
        <span className="commenter">{commenter}</span>
        <span className="date">{date}</span>
      </CommentContainer>
    </>
  );
};

export default Comment;
