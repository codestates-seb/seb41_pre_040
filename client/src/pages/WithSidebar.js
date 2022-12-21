import React from "react";
import styled from "styled-components";
import Sidebar from "../components/Sidebar";
import { Outlet } from "react-router-dom";

const DivContainer = styled.div`
  max-width: 1264px;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
`;

const PageContent = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  min-height: calc(100vh - 200px - 48px);
`;

const WithSidebar = () => {
  return (
    <DivContainer>
      <Sidebar />
      <PageContent>
        <Outlet />
      </PageContent>
    </DivContainer>
  );
};

export default WithSidebar;
