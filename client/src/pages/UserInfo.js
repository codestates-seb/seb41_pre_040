import React from "react";
import styled from "styled-components";
import { useEffect, useState } from "react";
import axios from "axios";
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
  .unlogged{
    font-size: 50px;
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
    const isLogged = useSelector((state) => state.user.isLogin);

    const [data, setData] = useState(null);
    const [isPending, setIsPending] = useState(true);
    useEffect(() => {
    axios
      .get("api2/users",
      {
        withCredentials: true,
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((res) => {
        setData(res.data.filter(e => e.email === sessionStorage.getItem("user_id")));
      })
      .then(()=> {
        setIsPending(false);
      })
      .catch((err)=>console.error(err));;
    },  []
    )
    
    return (
        <UserInfoContainer>
            { (isLogged && !isPending ) ? 
                (
            <>
            <Userimg>
                <img width="70" alt="img"src={data[0].photoUrl} />
                <Username>{data[0].nickname}</Username>
            </Userimg>
                <div className="div">Summary</div>
            <Userdetail>
                <div className="div"> - Email: {data[0].userId}</div>
                <div className="div"> - Location: {data[0].location}</div>
                <div className="div"> - Reputation: {data[0].reputation}</div>
            </Userdetail>
                </>
            ):
            (<div className="unlogged">Login Please</div>)
            }
        </UserInfoContainer>
    )
};

export default UserInfo;
