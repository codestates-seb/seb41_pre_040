import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const AskQuestion = styled(Link)`
  background-color: #0a95ff;
  color: white;
  font-weight: 555;
  padding: 13px 11px;
  border-radius: 3px;
  font-size: 0.8rem;
  &:hover {
    background-color: #126fd4;
  }
`;

const AskButton = () => {
  const isLogged = useSelector((state) => state.user.isLogin);

  return (
    <AskQuestion to={isLogged ? "/ask" : "/login"}>Ask Question</AskQuestion>
    // 글작성 테스트 시에만 아래 주석 해제
    // <AskQuestion to="/ask">Ask Question</AskQuestion>
  );
};

export default AskButton;
