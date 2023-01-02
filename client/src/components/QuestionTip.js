import React from "react";
import styled from "styled-components";

const BoxContainer = styled.div`
  display: flex;
`;

const TipBox = styled.div`
  width: 70%;
  border: 1px solid #a1c7e7;
  border-radius: 3px;
  padding: 24px;
  margin: 16px 0px;
  background-color: #e8f2fa;

  h2 {
    font-weight: 400;
    margin: 0px 0px 8px 0px;
  }

  p {
    margin: 0px;
    font-size: 1rem;
  }

  .p-bottom {
    margin-bottom: 16px;
  }

  h5 {
    margin: 0px 0px 8px 0px;
  }

  ul {
    margin-left: 30px;
  }

  li {
    list-style-type: disc;
    font-size: 13px;
  }
`;

const QuestionTip = () => {
  return (
    <BoxContainer>
      <TipBox>
        <h2>Writing a good question</h2>
        <p>
          You're ready to ask a programming-related question and this form will
          help guide you through the process.
        </p>
        <p className="p-bottom">
          Looking to ask a non-programming question? See the topics here to find
          a relevant site.
        </p>
        <h5>Steps</h5>
        <ul>
          <li>Summarize your problem in a one-line title.</li>
          <li>Describe your problem in more detail.</li>
          <li>Describe what you tried and what you expected to happen.</li>
          <li>
            Add "tags" which help surface your question to members of the
            community.
          </li>
          <li>Review your question and post it to the site.</li>
        </ul>
      </TipBox>
    </BoxContainer>
  );
};

export default QuestionTip;
