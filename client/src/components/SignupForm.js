import React, { useState, useEffect } from "react";
import styled from "styled-components";
import axios from "axios";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { signupList } from "../redux/signupSlice";

const Inputtext = styled.input`
  padding: 8px 10px 8px 10px;
  margin-bottom: 15px;
  width: 245px;
  height: 40px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 3px;
  font-size: 17px;
  /* .err{
      border-color: hsl(358, 68%, 59%);
      outline: hsl(358, 76%, 90%) solid 4px;
    } */
  &:focus {
    border-color: #0a95ff;
    outline: #c1f4ff solid 4px;
  }
`;

const Errtext = styled.input`
  padding: 8px 10px 8px 10px;
  margin-bottom: 15px;
  width: 245px;
  height: 40px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 3px;
  font-size: 17px;
  border-color: hsl(358, 68%, 59%);
  outline: hsl(358, 76%, 90%) solid 4px;

`

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
`

const SignupForm = () => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailErr, setEmailErr] = useState(false);
    const [passwordErr, setPasswordErr] = useState(false);

    const EMAIL_REGEX = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
    const signupLists = useSelector((state) => state.signupList.value);
    
    
    const navigte = useNavigate();
    const dispatch = useDispatch();


    const handleNameChange = (e) => {
        setName(e.target.value);
    };
    const handleEmailChange = (e) => {
        // if (EMAIL_REGEX.test(e.target.value)) {
        // }
        // setEmailErr(false);
        
        // signupList.map((e) =>{
        //     {e.email === e.target.value ? setEmailErr(true) : setEmailErr(false) }
        // }  
        // //     e.email 
        // //  === e.target.value ? setEmailErr(true) : setEmailErr(false)
        // )
        setEmail(e.target.value);
    };
    const handlePasswordChange = (e) => {
        // const passwordInput = e.target.value;
        // if (passwordInput.length >= 8) {
        // setPasswordErr(false);
        // }
        setPassword(e.target.value);
    };

    // const onClickSignup = () => {
    //   console.log(emailErr)
    //     if (!EMAIL_REGEX.test(email) || password.length < 8) {
    //         if (!EMAIL_REGEX.test(email)) setEmailErr(true);
    //         else setEmailErr(false);
    //         if (password.length < 8) setPasswordErr(true);
    //         else setPasswordErr(false);
    //         return;
    //     }
    //     else {
    //         dispatch(
    //             signupList({ 
    //             userId: email,
    //             display_name: name,
    //             password: password,
    //             userImg: "https://pic.onlinewebfonts.com/svg/img_312847.png",
    //             reputation: 0,
    //             location: "-",
    //             isLogin: false
    //             }));
    //             console.log(emailErr)
    //             console.log(signupLists)
    //             navigte("/login");
    //     }
    //     console.log(signupLists)
    // }

    //서버와 해보자!
    const onClickSignup = () => {
      console.log(emailErr)
        if (!EMAIL_REGEX.test(email) || password.length < 8) {
            if (!EMAIL_REGEX.test(email)) setEmailErr(true);
            else setEmailErr(false);
            if (password.length < 8) setPasswordErr(true);
            else setPasswordErr(false);
            return;
        }
        else {
            dispatch(
                signupList({ 
                userId: email,
                display_name: name,
                password: password,
                userImg: "https://pic.onlinewebfonts.com/svg/img_312847.png",
                reputation: 0,
                location: "-",
                isLogin: false
                }));
                console.log(signupLists)
                navigte("/login");
        }
        console.log(signupLists)
    }


    return (
        <SignInfo>
        <div className="text">Display name</div>
        <Inputtext onChange={handleNameChange} ></Inputtext>
        <div className="text">Email</div>
        {emailErr 
        ? <Errtext onChange={handleEmailChange}></Errtext>
        : <Inputtext onChange={handleEmailChange}></Inputtext>}
        {emailErr
        ? <ErrMsg>is not a valid email adress</ErrMsg>
        : <></>
        }
        {/* <Inputtext onChange={handleEmailChange}></Inputtext> */}
        <div className="text">Password</div>
        {passwordErr 
        ?<Errtext  onChange={handlePasswordChange} type="password" ></Errtext> 
        : <Inputtext onChange={handlePasswordChange} type="password"></Inputtext>}
        {passwordErr
        ? <ErrMsg>Passwords must contain at least eight characters</ErrMsg>
        : <></>
        }
        {/* <Inputtext onChange={handlePasswordChange} type="password"></Inputtext> */}
          <div className="pass">
          Passwords must contain at least eight characters.
        </div>
        <div>
          <SignupBtn onClick={onClickSignup}>Sign up
          </SignupBtn>
        </div>
      </SignInfo>
    )
}
export default SignupForm;
