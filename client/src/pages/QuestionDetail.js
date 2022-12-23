import React from "react";
import styled from "styled-components";

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const MainContent = styled.div`
  width: calc(100% - 300px - 24px);
  padding: 24px;
`;

const QuestionDetail = () => {
  return (
    <MainContent>
      <TitleContainer>
        <div>제목이랑 버튼</div>
        <div>작성 날짜</div>
      </TitleContainer>
    </MainContent>
  );
};

export default QuestionDetail;
