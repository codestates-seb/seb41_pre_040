import React, { useEffect } from "react";
import styled from "styled-components";
import AskButton from "../components/AskButton";
import Aside from "../components/Aside";
import AnswersList from "../components/AnswersList";
import AnswerQuestion from "../components/AnswerQuestion";
import QuestionPostLayout from "../components/QuestionPostLayout";
import elapsedTime_ago from "../assets/dateparse";
import { Link, useParams } from "react-router-dom";
import { useSelector } from "react-redux";

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

const BottomNotice = styled.div`
  margin: 15px 0px 17px 0px;
  h2 {
    font-weight: 400;
    font-size: 1.1rem;
    .ask-question {
      color: #0074cc;
      &:hover {
        color: #0a95ff;
      }
    }
  }
`;

const RightAside = styled.div`
  float: right;
  margin-left: 24px;
`;

const QuestionDetail = () => {
  const questionId = useParams().id;

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  // 상태에서 아이디에 해당하는 질문 가져오기
  const question = useSelector((state) => {
    let questions = state.questions.value;
    return questions.filter((el) => el.id === Number(questionId))[0];
  });

  return (
    <MainContent>
      <TitleContainer>
        <TitleAndButton>
          <h1>{question.title}</h1>
          <div className="ask-button">
            <AskButton />
          </div>
        </TitleAndButton>
        <div className="ask-date">
          Asked
          <span className="time-elapsed">{` ${elapsedTime_ago(
            question.created_at
          )}`}</span>
        </div>
      </TitleContainer>
      <Main>
        {/* 질문 하나, 답변 여러 개 */}
        <QuestionPostLayout
          id={question.id}
          content={question.content}
          tags={question.tags}
        />
        <div id="answers">
          <div id="answer-header">
            <h2>answers.length Answers</h2>
          </div>
          <AnswersList />
          <AnswerQuestion />
        </div>
        <BottomNotice>
          <h2>
            Not the answer you're looking for? Browse other questions or{" "}
            <Link className="ask-question" to="/ask">
              ask your own question.
            </Link>
          </h2>
        </BottomNotice>
      </Main>
      <RightAside>
        <Aside />
      </RightAside>
    </MainContent>
  );
};

export default QuestionDetail;
