import React from "react";
import styled from "styled-components";
import SearchBar from "./SearchBar";
import { Link } from "react-router-dom";

const StyledHeader = styled.header`
  display: flex;
  align-items: center;
  background-color: #f8f9f9;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
  border-top: 3px solid #e87c35;
  height: 50px;
  position: sticky;
  top: 0;
`;

const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 0 auto;
  width: 1264px;
  height: 100%;
  padding: 0px 5px;
  background-color: transparent;
`;

const LogoContainer = styled(Link)`
  display: flex;
  align-items: center;
  padding: 0 4px;
  height: 100%;
  &:hover {
    background-color: #e3e6e8;
    cursor: pointer;
  }
`;

const HeaderLogo = styled.span`
  background: url(https://cdn.sstatic.net/Img/unified/sprites.svg?v=fcc0ea44ba27);
  background-position: 0 -500px;
  text-indent: -9999em;
  width: 150px;
  height: 35px;
`;

const HeaderContents = styled.ul`
  display: flex;
  gap: 3px;
  color: #525960;
  font-size: 0.9rem;
  font-weight: 500;

  li {
    padding: 6px 12px;
    &:hover {
      background-color: #e3e6e8;
      cursor: pointer;
      border-radius: 1000px;
    }
  }
`;

const Buttons = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;
`;

const LoginButton = styled(Link)`
  background-color: #dee9f1;
  color: #39739d;
  border: 1px solid #39739d;
  padding: 7px 11px;
  border-radius: 3px;
  font-size: 0.9rem;
  &:hover {
    background-color: #b3d3ea;
  }
`;

const SignupButton = styled(Link)`
  background-color: #278bf7;
  color: white;
  padding: 8px 12px;
  border-radius: 3px;
  font-size: 0.9rem;
  &:hover {
    background-color: #126fd4;
  }
`;

const Header = () => {
  return (
    <StyledHeader>
      <HeaderContainer>
        <LogoContainer to="/">
          <HeaderLogo>로고</HeaderLogo>
        </LogoContainer>

        <HeaderContents>
          <li>About</li>
          <li>Products</li>
          <li>For Teams</li>
        </HeaderContents>

        <SearchBar />

        {/* 아래 버튼들은 로그인 상태에 따라 조건부렌더링 필요 */}
        <Buttons>
          <LoginButton to="/login">Log in</LoginButton>
          <SignupButton to="/signup">Sign up</SignupButton>
        </Buttons>
      </HeaderContainer>
    </StyledHeader>
  );
};

export default Header;
