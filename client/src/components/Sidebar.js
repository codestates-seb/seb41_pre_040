import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const LeftSidebar = styled.section`
  width: 164px;
  border: 1px solid black;
  border-width: 0px 1px 0px 0px;
  text-align: right;
`;

const Menus = styled.ul`
  position: sticky;
  top: 58px;

  li {
    padding-right: 10px;
    margin: 10px 0px;
  }
`;

const StyledLink = styled(Link)`
  font-weight: bold;
`;

const Sidebar = () => {
  return (
    <LeftSidebar>
      <Menus>
        <StyledLink to="/">
          <li>Home</li>
        </StyledLink>
        <StyledLink to="/questions">
          <li>Questions</li>
        </StyledLink>
        <StyledLink to="/users">
          <li>Users</li>
        </StyledLink>
      </Menus>
    </LeftSidebar>
  );
};

export default Sidebar;
