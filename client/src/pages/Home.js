import React from "react";
import styled from "styled-components";
import Question from "../components/Question";
import AskButton from "../components/AskButton";
// import AskButton from "../components/FilterButton";

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

const Questions = styled.div`
  margin-left: -24px;
  border-top: 1px solid #e3e6e8;
  margin-bottom: 50px;
`;

const ButtonContainer = styled.div`
  margin-bottom: 16px;
  display: flex;
  justify-content: flex-end;
`;

const FilterButtons = styled.div`
  display: inline-flex;

  button {
    margin-right: 0;
    padding: 10.4px;
    font-size: 13px;
    border-radius: 3px;
    border: 1px solid #838c95;
    color: #6a737c;

    &:hover {
      background-color: #f8f9f9;
      color: #3b4045;
    }

    &:active {
      background-color: #e3e6e8;
      color: #3b4045;
    }
  }

  .left {
    border-right: 0px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }

  .middle {
    border-right: 0px;
    border-radius: 0px;
  }

  .right {
    border-top-left-radius: 0px;
    border-bottom-left-radius: 0px;
  }
`;

const BottomNotice = styled.h2`
  font-weight: 450;
  font-size: 1.1rem;
`;

const Home = () => {
  return (
    <MainContent>
      <TitleAndButton>
        <h1>Top Questions</h1>
        <AskButton />
      </TitleAndButton>
      <ButtonContainer>
        <FilterButtons>
          <button className="left">Interesting</button>
          <button className="middle">Bountied</button>
          <button className="middle">Hot</button>
          <button className="middle">Week</button>
          <button className="right">Month</button>
        </FilterButtons>
      </ButtonContainer>
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
      <BottomNotice>
        Looking for more? Browse the complete list of questions, or popular
        tags. Help us answer unanswered questions.
      </BottomNotice>
    </MainContent>
  );
};

export default Home;
