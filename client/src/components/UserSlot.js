import React from "react";
import styled from "styled-components";


const Slot = styled.div`
  display: flex;
  padding: 7px;
  background-color: #d8eaf6;
  border-radius: 5px;
  img {
    width: 48px;
    height: 48px;
    margin-right: 10px;
    border-radius: 5px;
  }
`;

const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 5px;
  color: #6a737c;
`;
const Name = styled.span`
  font-size: 15px;
  font-weight: bold;
  color: #0074d3;
  cursor: pointer;
  &:hover {
    color: #0a95ff;
  }
`;

const Location = styled.span`
  font-size: 12px;
`;

const Reputation = styled.span`
font-size: 12px;
font-weight: bold;
`;

const UserSlot = () => {
    return (
        <Slot>
            <img src="https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png" alt="img"/>
            <UserInfo>
                <Name>변수 이름</Name>
                <Location>지역</Location>
                <Reputation>추천수</Reputation>
            </UserInfo>



        </Slot>
    )
};

export default UserSlot;
