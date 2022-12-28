// 질문 하나 컴포넌트
import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import TagList from "./TagsList";
import elapsedTime_ago from "../assets/dateparse";

const QuestionContainer = styled.div`
  display: flex;
  padding: 16px;
  border-bottom: 1px solid #e3e6e8;
`;

const QuestionStat = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 108px;
  gap: 6px;
  margin-right: 16px;
  margin-bottom: 4px;
  font-size: 13px;
  color: #6a737c;
  flex-shrink: 0;

  .question-votes {
    color: black;
    font-weight: 500;
  }
`;

const QuestionInfo = styled.div`
  flex-grow: 1;

  .question-title {
    padding-right: 24px;
    margin-top: -0.15rem;
    margin-bottom: 0.3846rem;
    font-weight: 500;
    overflow-wrap: break-word;
    color: #0074cc;
    font-size: 17px;
    hyphens: auto;

    &:hover {
      color: #0a95ff;
      cursor: pointer;
    }
  }

  .question-content {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    margin-top: 1px;
    margin-bottom: 8px;
    overflow: hidden;
    word-break: break-word;
    overflow-wrap: break-word;
    hyphens: auto;
    font-size: 13px;
  }
`;

const AdditionalInfo = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  column-gap: 6px;
  row-gap: 8px;
  font-size: 12px;

  .user-info {
    margin-left: auto;
    display: flex;
    gap: 4px;
    align-items: center;
    color: #6a737c;
    font-weight: 500;

    .username {
      color: #0074cc;
    }
  }
`;

const Question = ({ id, title, content, tags, createdAt, author }) => {
  return (
    <QuestionContainer>
      <QuestionStat>
        <div className="question-votes">0 votes</div>
        <div>0 answers</div>
        <div>3 views</div>
      </QuestionStat>
      <QuestionInfo>
        <Link to={`/question/${id}`}>
          <h3 className="question-title">{title}</h3>
        </Link>
        <div className="question-content">{content}</div>
        <AdditionalInfo>
          <TagList tags={tags} />
          <div className="user-info">
            <div className="username">{author}</div>
            <div>asked {elapsedTime_ago(createdAt)}</div>
          </div>
        </AdditionalInfo>
      </QuestionInfo>
    </QuestionContainer>
  );
};

export default Question;
