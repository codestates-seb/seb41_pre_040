import React from "react";
import styled from "styled-components";
import Sidebar from "../components/Sidebar";

const DivContainer = styled.div`
  display: flex;
`;

const PageContent = styled.div`
  flex: 5;
  min-height: calc(100vh - 200px - 48px);
`;

const PageText = styled.div`
  margin: 0px 0px;
`;

const ExamplePage = () => {
  return (
    <DivContainer>
      <Sidebar />
      <PageContent>
        <PageText>페이지 내용</PageText>
      </PageContent>
    </DivContainer>
  );
};

export default ExamplePage;
