import React from "react";
import styled from "styled-components";
import Comment from "./Comment";

const Comments = styled.ul`
  padding-bottom: 10px;
  margin-top: 12px;
  border-top: 1px solid #e3e6e8;
`;

const AddComment = styled.div`
  font-size: 13px;
  color: #838c95;

  &:hover {
    color: #0a95ff;
    cursor: pointer;
  }
`;

const CommentsList = () => {
  const dummyData = [
    {
      id: 0,
      content:
        "What was the original intent of the question? Specific to Microsoft's SQL Server (T-SQL)? Or a generic SQL question?",
      username: "Peter Mortensen",
      createdAt: "Jan 30 at 1:40",
    },
    {
      id: 1,
      content: "@PeterMortensen Microsoft SQL Server. ",
      username: "jamesmhaley",
      createdAt: "Sep 22 at 10:45",
    },
  ];

  return (
    <>
      <Comments>
        {dummyData.map((el, idx) => {
          return (
            <Comment
              key={el.id}
              content={el.content}
              commenter={el.username}
              date={el.createdAt}
            />
          );
        })}
      </Comments>
      <AddComment>Add a comment</AddComment>
    </>
  );
};

export default CommentsList;
