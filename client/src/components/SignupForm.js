import React, { useState } from "react";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Inputtext = styled.input`
  padding: 8px 10px 8px 10px;
  margin-bottom: 15px;
  width: 245px;
  height: 40px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 3px;
  font-size: 17px;

  &:focus {
    border-color: #0a95ff;
    outline: #c1f4ff solid 4px;
  }
`;

const SignInfo = styled.div`
  background-color: white;
  width: 292px;
  padding: 24px;
  margin-top: 20px;
  margin-bottom: 24px;
  border-radius: 10px;
  box-shadow: 3px 3px 3px 3px gray;
  .text {
    margin-bottom: 15px;
  }
  .pass {
    font-size: 13px;
    color: #9a99a1;
  }
`;
const SignupBtn = styled.button`
  flex-grow: 1;
  width: 100%;
  height: 45px;
  border-radius: 3px;
  border: none;
  background-color: hsl(206, 100%, 52%);
  color: white;
  margin-top: 13px;
  cursor: pointer;
  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
`;

const ErrMsg = styled.div`
  color: red;
  margin-top: -8px;
  margin-bottom: 12px;
  font-size: 13px;
`;

const SignupForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailErr, setEmailErr] = useState(false);
  const [passwordErr, setPasswordErr] = useState(false);

  const navigte = useNavigate();

  const handleNameChange = (e) => {
    setName(e.target.value);
  };
  const handleEmailChange = (e) => {
    const EMAIL_REGEX = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
    if (EMAIL_REGEX.test(e.target.value)) {
      setEmailErr(false);
    } else {
      setEmailErr(true);
    }
    setEmail(e.target.value);
  };
  const handlePasswordChange = (e) => {
    const passwordInput = e.target.value;
    if (passwordInput.length >= 8) {
      setPasswordErr(false);
    } else {
      setPasswordErr(true);
    }
    setPassword(e.target.value);
  };

  const onClickSignup = (e) => {
    e.preventDefault();
    let body = {
      nickname: name,
      userId: email,
      userPassword: password,
      email: email,
    };

    return axios
      .post(
        "api2/signup/join",
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
        if (res) {
          console.log("회원가입이 완료되었습니다.");
          navigte("/login");
        }
      })
      .catch((err) => {
        console.log(err);
        alert("이미 존재하는 id입니다.");
        window.location.replace("/signup");
      });
  };

  return (
    <SignInfo>
      <div className="text">Display name</div>
      <Inputtext onChange={handleNameChange}></Inputtext>
      <div className="text">Email</div>
      <Inputtext onChange={handleEmailChange}></Inputtext>
      {emailErr ? <ErrMsg>is not a valid email adress</ErrMsg> : <></>}

      <div className="text">Password</div>
      <Inputtext onChange={handlePasswordChange} type="password"></Inputtext>
      {passwordErr ? (
        <ErrMsg>Passwords must contain at least eight characters</ErrMsg>
      ) : (
        <></>
      )}

      <div className="pass">
        Passwords must contain at least eight characters.
      </div>
      <div>
        <SignupBtn onClick={onClickSignup}>Sign up</SignupBtn>
      </div>
    </SignInfo>
  );
};
export default SignupForm;
