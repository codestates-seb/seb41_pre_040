import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import axios from "axios";
// import { useSelector } from "react-redux";
import { LoginStatus } from "../redux/user";
// import { loginSwitch } from "../redux/usersSlice";
// import { loginSwitch } from "../redux/usersSlice";


const WrapLogin = styled.div`
  border: none;
  display: flex;
  width: 100%;
  height: 260px;
  border-radius: 6px;
  background-color: white;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
  justify-content: center;
  align-items: center;

  > form {
    display: flex;
    flex-direction: column;

    > .form-col {
      width: 280px;
      height: 60px;
      margin-top: 10px;
      flex-grow: 1;
      display: flex;
      flex-direction: column;
      > label {
        font-weight: bold;
        margin-bottom: 6px;
      }
      > input {
        height: 35px;
        border: 1px solid hsl(210, 8%, 75%);
        border-radius: 4px;

        &:focus {
          border: 1px solid hsl(206, 100%, 40%);
          outline: 3px solid hsl(206, 96%, 90%);
        }
      }
    }

    > .Blue-button {
      flex-grow: 1;
      width: 100%;
      height: 37.78px;
      border-radius: 3px;
      border: none;
      background-color: hsl(206, 100%, 52%);
      color: white;
      margin-top: 13px;
      &:hover {
        background-color: hsl(206, 100%, 40%);
      }
    }
  }
`;

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const onEmailHandler = (e) => {
    setEmail(e.target.value);
  };
  const onPasswordHandler = (e) => {
    setPassword(e.target.value);
  };
  const navigte = useNavigate();
  const dispatch = useDispatch();

<<<<<<< Updated upstream
  // const userinfos = useSelector((state) => state.users.value); //로그인 상태위해 추가
  // console.log("이니셜 ",userinfos)
  // const loggedUser = userinfos.filter(e => e.userId === "40fighting@naver.com") //필터위해 추가
  // console.log("필터된  ", loggedUser[0].isLogin)
  // // loggedUser[0].isLogin = true
  // console.log("로그인바뀐  ", loggedUser[0].isLogin)


  const onClickLogin = () => {
    // 테스트를 위한 임의의 id, password
    const userId = "40fighting@naver.com";
    const userPassword = "1234";
    
    // const loggedUser = userinfos.filter(e => e.userId === userId) //필터위해 추가
    // console.log(loggedUser[0])
    
    if (userId !== email) {
      alert("입력하신 id가 일치하지 않습니다.");
    } else if (userPassword !== password) {
      alert("입력하신 비밀번호가 일치하지 않습니다.");
    } else {
      dispatch(LoginStatus({ isLogin: true }));
      console.log("로그인 성공");
      navigte("/");
    }
  };

  // 서버와 연동시켰을 때 코드
  // const onClickLogin = async () => {
  //   console.log("click login");
  //   console.log("Email : ", email);
  //   console.log("password : ", password);
=======
  const onClickLogin = (e) => {
    e.preventDefault();
>>>>>>> Stashed changes

    let body = {
      userId: email,
      userPassword: password,
    };
    return axios
      .post("/loin", body)
      .then((res) => {
        if (res.status === 200) {
          dispatch(LoginStatus({ isLogin: true }));
          console.log("로그인 성공");
          navigte("/");
        } else {
          alert("email과 password가 일치하지 않습니다.");
        }
      })
      .catch(
        (err) => console.error(err),
        alert("email과 password가 일치하지 않습니다.")
      );
  };

  // 서버와 연동시켰을 때 코드
  // const onClickLogin = (e) => {
  //   console.log("click login");
  //   console.log("Email : ", email);
  //   console.log("password : ", password);
  //   e.preventDefault();
  //   console.log()

  //   return axios
  //     .get("/users", {
  //       params: { userId: email },
  //       headers: {
  //         "ngrok-skip-browser-warning": "ojwodhfaofd",
  //       },
  //     })
  //     .then((res) => {
  //       console.log(res.data);
  //       // dispatch(LoginStatus());
  //       // navigate("/");
  //     })
  //     .catch((err) => console.error(err));
  // };


  return (
    <WrapLogin>
      <form>
        <div className="form-col">
          <label>Email</label>
          <input
            id="email"
            defaultValue={email}
            onChange={onEmailHandler}
          ></input>
        </div>
        <div className="form-col">
          <label>Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={onPasswordHandler}
          ></input>
        </div>
        <button className="Blue-button" onClick={onClickLogin}>
          Log in
        </button>
      </form>
    </WrapLogin>
  );
};

export default LoginForm;
