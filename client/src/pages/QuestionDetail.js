import React from "react";
import styled from "styled-components";
import AskButton from "../components/AskButton";
import Aside from "../components/Aside";
import AnswersList from "../components/AnswersList";
import AnswerQuestion from "../components/AnswerQuestion";
import PostLayout from "../components/PostLayout";

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;

  .ask-date {
    font-size: 13px;
    color: #6a737c;
    padding-bottom: 16px;
    border-bottom: 1px solid #e3e6e8;
    margin-bottom: 16px;

    .time-elapsed {
      color: black;
    }
  }
`;

const TitleAndButton = styled.div`
  display: flex;
  justify-content: space-between;

  h1 {
    font-size: 1.6rem;
    font-weight: 500;
    line-height: 1.3;
    margin: 0px 0px 8px 0px;
  }

  .ask-button {
    flex-shrink: 0;
    margin-left: 12px;
  }

  a {
    display: inline-block;
  }
`;

const MainContent = styled.div`
  padding: 24px;
`;

const Main = styled.div`
  float: left;
  width: calc(100% - 300px - 24px);
  padding-bottom: 24px;

  #answers {
    padding-top: 10px;
  }

  #answer-header {
    margin-top: 10px;
  }

  h2 {
    font-size: 1.3rem;
    font-weight: 500;
    line-height: 1.3;
    margin: 0px 0px 8px 0px;
  }
`;

const RightAside = styled.div`
  float: right;
  margin-left: 24px;
`;

const QuestionDetail = () => {
  return (
    <MainContent>
      <TitleContainer>
        <TitleAndButton>
          <h1>How do I UPDATE from a SELECT in SQL Server?</h1>
          <div className="ask-button">
            <AskButton />
          </div>
        </TitleAndButton>
        <div className="ask-date">
          Asked
          <span className="time-elapsed"> Today</span>
        </div>
      </TitleContainer>
      <Main>
        {/* 질문 하나, 답변 여러 개 */}
        <PostLayout editlink={"qrevise"} />
        <div id="answers">
          <div id="answer-header">
            <h2>answers.length Answers</h2>
          </div>
          <AnswersList />
          {/* 로그인 상태가 아니라면 답변 영역이 보이면 안 됨 */}
          <AnswerQuestion />
        </div>
      </Main>
      <RightAside>
        <Aside />
      </RightAside>
    </MainContent>
  );
};

export default QuestionDetail;
