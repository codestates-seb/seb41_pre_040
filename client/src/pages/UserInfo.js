import React from "react";
import styled from "styled-components";
// import axios from "axios";
import { useSelector } from "react-redux";


const UserInfoContainer = styled.section`
  display: flex;
  width: 100%;
  height: 100%;
  padding: 24px;
  display: flex;
  flex-direction: column;
  .div{
    margin-top: 20px;
    font-size: 20px;
  }
  `
const Userimg = styled.div`
display: flex;
`

const Username = styled.div`
font-size: 40px;
font-weight: bold;      
margin-top: 10px;
margin-left: 20px;
`

const Userdetail = styled.div`
    margin-top: 10px;
    border: 2px solid #9a99a1;
    width: 500px;
    border-radius: 10px;
    margin-left: 10px;
    .div{
        color: black;
        margin-left: 10px;
        margin-top: 5px;
        margin-bottom: 5px;
        font-size: 20px;
    }
`


const UserInfo = () => {
    const userinfos = useSelector((state) => state.users.value);
    console.log(userinfos)
    const loggedUser = userinfos.filter(e => (e.isLogin === true ))
    
    return (
        <UserInfoContainer>
            <Userimg>
                <img width="70" alt="img"src={loggedUser[0].userImg} />
                <Username>{loggedUser[0].display_name}</Username>
            </Userimg>
                <div className="div">Summary</div>
            <Userdetail>
                <div className="div"> - Email: {loggedUser[0].userId}</div>
                <div className="div"> - Location: {loggedUser[0].location}</div>
                <div className="div"> - Reputation: {loggedUser[0].reputation}</div>
                <div className="div">기타 더미정보2</div>
                <div className="div">기타 더미정보3</div>
                <div className="div">기타 더미정보4</div>
            </Userdetail>
        </UserInfoContainer>
    )
};

export default UserInfo;
