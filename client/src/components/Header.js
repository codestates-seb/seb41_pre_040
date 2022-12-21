import React from "react";
import styled from "styled-components";

const StyledHeader = styled.header`
  height: 48px;
  border: 1px solid black;
  line-height: 48px;
  position: sticky;
  top: 0;
  text-align: center;
  background-color: white;
`;

const Header = () => {
  return <StyledHeader>헤더 내용</StyledHeader>;
};

export default Header;
