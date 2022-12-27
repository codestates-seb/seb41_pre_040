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

const UserSlot = ( { display_name, location, reputation, profile_image }) => {

    return (
        <Slot>
            <img src={profile_image} alt="img"/>
            <UserInfo>
                <Name>{display_name}</Name>
                <Location>{location}</Location>
                <Reputation>{reputation}</Reputation>
            </UserInfo>



        </Slot>
    )
};

export default UserSlot;
