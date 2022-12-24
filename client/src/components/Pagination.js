import React from "react";
import styled from "styled-components";
import Pagination from "react-js-pagination"

const Container = styled.div`
  ul {
    display: flex;
    gap: 5px;
    & > li:first-child {
      display: none;
    }
    & > li:nth-child(2) {
      width: 50px;
    }
    & > li:nth-last-child(2) {
      width: 50px;
    }
    & > li:last-child {
      display: none;
    }
    & > li.active {
      color: white;
      border-color: #f48225;
      background-color: #f48225;
    }
  }
  li {
    width: 30px;
    height: 30px;
    padding-top: 3px;
    border: 1px solid #F1F2F3;
    border-radius: 3px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #3b4045;
    font-size: 13px;
    font-weight: 500;
    cursor: pointer;
    &:hover {
      background-color: #cecbcb;
    }
  }
`;


const Paging = ({ currentPage, 
  // itemsCountPerPage, 
  count, onChange, pageRangeDisplayed = 5,   }) => {


    return (
        <Container>
            <Pagination
            activePage={currentPage}
            itemsCountPerPage={36}
            totalItemsCount={count}
            pageRangeDisplayed={pageRangeDisplayed}
            onChange={onChange}
            prevPageText="Prev"
            nextPageText="Next"
            />
        </Container>
        
    )
  };
  
  export default Paging;
  