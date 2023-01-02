import React from "react";
import styled from "styled-components";
import { useEffect, useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { usersList } from "../redux/usersSlice"



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
    const userinfos = useSelector((state) => state.users.value);
    const isLogged = useSelector((state) => state.user.isLogin);

    // //세션 이용
    // const dispatch = useDispatch();
    // const sessionId = sessionStorage.getItem("userId");
    // const sessionName = sessionStorage.getItem("display_name");
    // const sessionLocation =  sessionStorage.getItem("location");
    // const sessionReputation = sessionStorage.getItem("reputatiom");
    // const sessionImg = sessionStorage.getItem("userImg");

    // useEffect(() => {
    //     dispatch(usersList({
    //         display_name: sessionName,
    //         location: sessionLocation,
    //         reputation: sessionReputation,
    //         userImg: sessionImg,
    //         userId: sessionId,
    // }));
    // }, []);


    // //api 사용
    // const [data, setData] = useState(null);
    // const dispatch = useDispatch();
    // useEffect(() => {
    //     axios
    //   .get("https://jsonplaceholder.typicode.com/users")
    //   .then((res) => {
    //     console.log("res data",res.data)
    //     setData(res.data.filter(e => e.id === 4));   
    //     console.log("data",data)


    // //   setData(res.data.filter(e => e.user_id === sessionStorage.getItem("user_id")));
    //     dispatch(usersList({
    //             location: data[0].username,
    //             reputation: data[0].name,
    //           }))
    //   });
    // },  []
    // )



    
    return (
        <UserInfoContainer>
            {isLogged ? 
                (
            <>
            <Userimg>
                <img width="70" alt="img"src={userinfos[0].userImg} />
                <Username>{userinfos[0].display_name}</Username>
            </Userimg>
                <div className="div">Summary</div>
            <Userdetail>
                <div className="div"> - Email: {userinfos[0].userId}</div>
                <div className="div"> - Location: {userinfos[0].location}</div>
                <div className="div"> - Reputation: {userinfos[0].reputation}</div>
            </Userdetail>
                </>
            ):
            (<div className="unlogged">Login Please</div>)
            }
        </UserInfoContainer>
    )
};

export default UserInfo;
