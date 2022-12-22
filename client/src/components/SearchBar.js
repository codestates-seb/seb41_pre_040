import React from "react";
import styled from "styled-components";

const SearchContainer = styled.form`
  /* position: relative; */
  padding: 0px 8px;
  display: flex;
  align-items: center;
  flex-grow: 1;
`;

const InputContainer = styled.div`
  height: 33px;
  position: relative;
  flex-grow: 1;
`;

const Input = styled.input`
  border: 1px solid #babfc4;
  color: #3b4045;
  border-radius: 3px;
  height: 100%;
  width: 100%;
  padding-left: 30px;

  &:focus {
    outline: none;
    border: 1px solid #69b9f7;
    box-shadow: 0 0 4px #78bef5;
  }
`;

const SearchIcon = styled.svg`
  position: relative;
  top: -80%;
  margin: 0px 8px;
  width: 18px;
  height: 18px;
  path {
    fill: #838c95;
  }
`;

const SearchBar = () => {
  // 검색 기능 추가 필요

  return (
    <SearchContainer>
      <InputContainer>
        <Input placeholder="Search..."></Input>
        <SearchIcon>
          <path d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z" />
        </SearchIcon>
      </InputContainer>
    </SearchContainer>
  );
};

export default SearchBar;
