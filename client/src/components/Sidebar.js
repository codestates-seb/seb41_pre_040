import React from "react";
import styled from "styled-components";

const LeftSidebar = styled.section`
  min-width: 164px;
  border: 1px solid black;
  border-width: 0px 1px 0px 0px;
  text-align: right;
  flex: 1;
  //bottom: 200px;
  //position: fixed;
`;

const Menus = styled.div`
  position: sticky;
  top: 58px;
`;

const Menu = styled.div`
  font-weight: bold;
  padding-right: 10px;
  margin: 10px 0px;
`;

const Sidebar = () => {
  return (
    <LeftSidebar>
      <Menus>
        <Menu>홈</Menu>
        <Menu>Questions</Menu>
        <Menu>Users</Menu>
      </Menus>
    </LeftSidebar>
  );
};

export default Sidebar;
