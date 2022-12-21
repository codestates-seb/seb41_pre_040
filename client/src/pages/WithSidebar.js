import React from "react";
import styled from "styled-components";
import Sidebar from "../components/Sidebar";
import { Outlet } from "react-router-dom";

const DivContainer = styled.div`
  display: flex;
`;

const PageContent = styled.div`
  flex: 3;
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
