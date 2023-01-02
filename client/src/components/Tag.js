// 태그 컴포넌트
import React from "react";
import styled from "styled-components";

const TagContainer = styled.a`
  background-color: #dee9f1;
  font-size: 12px;
  color: #39678f;
  padding: 0.4em 0.5em;
  text-align: center;
  line-height: 1;
  border-radius: 3px;
  &:hover {
    background-color: #d0e3f1;
    cursor: pointer;
  }
`;

const Tag = ({ content }) => {
  return <TagContainer>{content}</TagContainer>;
};

export default Tag;
