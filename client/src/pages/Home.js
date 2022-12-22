import React from "react";
import styled from "styled-components";
import Question from "../components/Question";
import { Link } from "react-router-dom";

const MainContent = styled.div`
  width: calc(100% - 300px - 24px);
  padding: 24px;
`;

const TitleAndButton = styled.div`
  display: flex;
  align-items: center;
  padding-bottom: 30px;
  h1 {
    font-size: 1.6rem;
    margin: 0;
    font-weight: 500;
    flex: 1 auto;
  }
`;

const AskQuestion = styled(Link)`
  background-color: #0a95ff;
  color: white;
  font-weight: 555;
  padding: 11px 11px;
  border-radius: 3px;
  font-size: 0.8rem;
  &:hover {
    background-color: #126fd4;
  }
`;

const Questions = styled.div`
  margin-left: -24px;
  border-top: 1px solid #e3e6e8;
`;

const FilterButtons = styled.div`
  border-top: 1px solid blue;
`;

const Home = () => {
  return (
    <MainContent>
      <TitleAndButton>
        <h1>Top Questions</h1>
        <AskQuestion to="/ask">Ask Question</AskQuestion>
      </TitleAndButton>
      <FilterButtons>
        <div>필터링 버튼 들어갈 자리</div>
      </FilterButtons>
      {/* 일단은 하드코딩해놓기 */}
      <Questions>
        <Question />
        <Question />
        <Question />
        <Question />
        <Question />
        <Question />
        <Question />
        <Question />
      </Questions>
    </MainContent>
  );
};

export default Home;
