import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { LoginStatus } from "../redux/user";

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

  const onClickLogin = (e) => {
    e.preventDefault();

    let body = {
      userId: email,
      userPassword: password,
    };

    console.log(body);
    return axios
      .post(
        "api2/users/login",
        body,
        {
          withCredentials: true,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        if (res.status === 200) {
          console.log(res.data);
          dispatch(LoginStatus({ isLogin: true }));
          console.log("로그인 성공");
          navigte("/");
        }
      })
      .catch((err) => console.error(err));
  };

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
