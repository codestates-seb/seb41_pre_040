import React from "react";
import styled from "styled-components";
import PostLayout from "./PostLayout";

const AnswerContainer = styled.li`
  padding: 16px 0px;
  border-bottom: 1px solid #f1f2f3;
`;

const Answer = () => {
  return (
    <AnswerContainer>
      <PostLayout />
    </AnswerContainer>
  );
};

export default Answer;
