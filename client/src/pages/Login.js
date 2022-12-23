import React from "react";
import styled from "styled-components";
import LoginForm from "../components/LoginForm";
import { LoginSvg } from "../images/HomeSvg";
import { GoogleSvg } from "../images/HomeSvg";
import { Link } from "react-router-dom";

const StyledContainer = styled.section`
  display: flex;
  width: 100%;
  height: 100%;
  padding: 24px;
  min-height: 94vh;
  background-color: hsl(210, 8%, 95%);
  display: flex;
  justify-content: center;
  align-items: center;

  > .login-content {
    width: 350px;
    height: 500px;
    display: flex;
    flex-direction: column;
    align-items: center;

    > .logo {
      margin-bottom: 20px;
    }

    > button {
      width: 100%;
      height: 37.78px;
      margin: 4px 0;
      font-size: 13px;
      border: solid 1px hsl(210, 8%, 85%);
      border-radius: 7px;
      background-color: white;
      color: #3b4045;
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-top: 30px;
      margin-bottom: 20px;
      &:hover {
        background-color: hsl(210, 8%, 97.5%);
      }

      > .google-svg {
        margin-right: 4px;
      }
    }
    > .Option-container {
      display: flex;
      margin-top: 60px;
      > .Option {
        > .SignUp-link {
          margin-left: 10px;
          color: hsl(206, 100%, 40%);
          text-decoration: none;
          cursor: pointer;
          &:hover {
            color: hsl(206, 85%, 57.5%);
          }
        }
      }
    }
  }
`;

const Login = () => {
  return (
    <StyledContainer>
      <div className="login-content">
        <LoginSvg />
        <button className="oauth-button-Google">
          <GoogleSvg />
          Login in with Google
        </button>
        <LoginForm />
        <div className="Option-container">
          <div className="Option">
            Don't have an accout?
            <Link className="SignUp-link" to="/Signup">
              Sign up
            </Link>
          </div>
        </div>
      </div>
    </StyledContainer>
  );
};

export default Login;
