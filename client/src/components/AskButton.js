import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const AskQuestion = styled(Link)`
  background-color: #0a95ff;
  color: white;
  font-weight: 555;
  padding: 11px 11px;
  border-radius: 3px;
  font-size: 0.8rem;
  &:hover {
    background-color: #126fd4;
  }
`;

const AskButton = () => {
  return <AskQuestion to="/ask">Ask Question</AskQuestion>;
};

export default AskButton;
