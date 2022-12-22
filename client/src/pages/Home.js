import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const MainContent = styled.div`
  width: calc(100% - 300px - 24px);
  border: 1px solid green;
  padding: 24px;
`;

const TitleAndButton = styled.div`
  display: flex;
  align-items: center;
  border: 1px solid blue;
  padding-bottom: 30px;
  h1 {
    font-size: 1.6rem;
    margin: 0;
    font-weight: 500;
    flex: 1 auto;
  }
`;

const AskQuestion = styled(Link)`
  background-color: #278bf7;
  color: white;
  padding: 10px 12px;
  border-radius: 3px;
  font-size: 0.9rem;
  &:hover {
    background-color: #126fd4;
  }
`;

const FilterButtons = styled.div`
  border: 1px solid blue;
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
    </MainContent>
  );
};

export default Home;
