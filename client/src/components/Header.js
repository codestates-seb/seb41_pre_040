import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const StyledHeader = styled.header`
  display: flex;
  justify-content: center;
  height: 48px;
  border: 1px solid black;
  line-height: 48px;
  position: sticky;
  top: 0;
  background-color: white;
`;

const SignupButton = styled.button`
  margin-left: 10px;
  border: 1px solid black;
  border-radius: 3px;
  font-size: 0.9rem;
`;

const Header = () => {
  return (
    <StyledHeader>
      <div>헤더</div>
      <Link to="/signup">
        <SignupButton>회원가입</SignupButton>
      </Link>
    </StyledHeader>
  );
};

export default Header;
