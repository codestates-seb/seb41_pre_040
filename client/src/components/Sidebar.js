import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { RiEarthLine } from "react-icons/ri";

// 현재 페이지를 볼드 및 호버로 표시해주는 기능 
// 좌측에 붙어있기
// width에 따라 사라졌다 나타났다는 페이지에서

const LeftSidebar = styled.section`
  width: 164px;
  border-right: 0.2px solid #627680;
  position: sticky;
  display: flex;
  flex-flow: column nowrap;
  /* position: fixed; */
`;

const Menus = styled.ul`
  display: flex;
  flex-flow: column wrap;
  color: #627680;
  margin-bottom: 15px;
  padding-top: 10px;
  position: fixed;
  .topmargin    {
    margin-top: 40px;
  }
`;

const Earth = styled(RiEarthLine)`
  margin: 5px;

`

const StyledLink = styled(Link)`
  display: flex;
  position: relative;
  align-items: center;
  justify-content: stretch;
  width: 100%;
  height: 40px;
  text-decoration: none;
  font-size: 0.9rem;
  padding-left: 10px;
  transition: 0.4s all;
  
  &:hover {
    background-color: #F1F2F3;
    color: black;
    transition: 0.4s all;
  }   
  &:focus{
    background-color: #F1F2F3;
    color: black;  }
`;

const LeftPadding = styled.li`
  padding-left: 25px;
`

const Publicspan = styled.span`
  display: inline-block;
  font-size: 0.8rem;
  padding: 5px 0px 5px 10px;
`

const MenuList = styled.span`
  flex: 1 1 100%;
  display: flex;
  align-items: center;
  width: 100%;
  height: 100%;
  font-size: 0.9rem;
`

const AdSection = styled.div`
  padding-left: 10px;
  width: 160px;
`

const StLine = styled.div`
  font-weight: bold;
  padding-left: 10px;
`

const Sidebar = () => {
  return (
    <LeftSidebar>
      <Menus>        
        <StyledLink to="/">
          Home
        </StyledLink>
        <ul>
        <Publicspan>PUBLIC</Publicspan>
          <li>
        <StyledLink to="/questions">
          <Earth />
        <MenuList>Questions</MenuList>
        </StyledLink>
        <StyledLink to="/users">
          <LeftPadding />
          <MenuList>Users</MenuList>
        </StyledLink>
          </li>
        </ul>
      <div className="topmargin">
      <StLine class="fc-black-750 mb6">Stack Overflow for</StLine>
      <StLine>Teams</StLine>
      <AdSection>
        – Start collaborating and sharing organizational knowledge.
        </AdSection>
      <img class="wmx100 mx-auto my8 h-auto d-block" width="160" height="114" src="https://cdn.sstatic.net/Img/teams/teams-illo-free-sidebar-promo.svg?v=47faa659a05e" alt=""></img>
      </div>
      </Menus>
    </LeftSidebar>
  );
};

export default Sidebar;
