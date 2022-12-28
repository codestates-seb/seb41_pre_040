import React, { useEffect, useState } from "react";
import styled from "styled-components";
import axios from "axios";
import AskButton from "../components/AskButton";
import Aside from "../components/Aside";
import AnswersList from "../components/AnswersList";
import AnswerQuestion from "../components/AnswerQuestion";
import QuestionPostLayout from "../components/QuestionPostLayout";
import elapsedTime_ago from "../assets/dateparse";
import { Link, useParams } from "react-router-dom";
import { getQuestionById } from "../redux/questionsSlice";
import { useSelector, useDispatch } from "react-redux";

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
  const dispatch = useDispatch();
  // const [answers, setAnswers] = useState([]);

  // 상태는 새로고침하면 초기화되기 때문에 직접 axios로 받아오는 것이 적절한 것 같음
  const question = useSelector((state) => state.questions.single);
  // const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // 서버에서 questionId에 해당하는 질문과 답변 불러오기
    // 답변은 쿼리스트링 이용해서 불러오기 -> thunk 이용? 아니면 여기서 바로?
    dispatch(getQuestionById(questionId));
    window.scrollTo(0, 0);
  }, []);

  return (
    <MainContent>
      <TitleContainer>
        <TitleAndButton>
          <h1>{question.title ? question.title : " "}</h1>
          <div className="ask-button">
            <AskButton />
          </div>
        </TitleAndButton>
        <div className="ask-date">
          Asked
          {question ? (
            <span className="time-elapsed">
              {" "}
              {elapsedTime_ago(question.createdAt)}
            </span>
          ) : (
            <div> </div>
          )}
        </div>
      </TitleContainer>
      <Main>
        {question ? (
          <QuestionPostLayout
            id={question.id}
            content={question.content1}
            tags={question.questionHashtag}
            author={question.createdBy}
          />
        ) : (
          <div> </div>
        )}
        <div id="answers">
          <div id="answer-header">
            <h2>{question.answer && question.answer.length} Answers</h2>
          </div>
          <AnswersList />
          <AnswerQuestion id={question.id} />
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
