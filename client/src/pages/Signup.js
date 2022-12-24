import React from "react";
import styled from "styled-components";
// import { Link } from "react-router-dom";
import SignupForm from "../components/SignupForm";

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 24px;
  width: 100%;
  height: calc(100vh - 50px);
  background-color: #f1f2f3;
`;

const SideContainer = styled.span`
  display: flex;
  flex-direction: column;
  h1 {
    font-size: 27px;
    margin: 0px 0px 32px;
  }
  .collabo {
    color: #9199a1;
  }
`;

const SideMessge = styled.div`
  margin: 0px 0px 32px;
  align-items: center;
  span {
    margin-left: 10px;
  }
`;

const GetMessage = styled.div`
  color: #0074d3;
  cursor: pointer;
  &:hover {
    color: #0a95ff;
  }
`;

const SignupContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 316px;
  height: 919px;
`;

const SocialSign = styled.button`
  width: 292px;
  margin: 6px 0px 6px 0px;

  .google {
    padding-top: 6px;
    height: 38px;
    border-radius: 10px;
    border: 1px solid #d6d9dc;
    background-color: white;
  }
  .git {
    padding-top: 6px;
    background-color: #2f3337;
    height: 38px;
    border-radius: 10px;
    color: white;
  }
`;

const Signup = () => {
  return (
    <Container>
      <SideContainer>
        <h1>Join Stack Overflow community</h1>
        <SideMessge>
          <img
            width="24"
            alt="Img"
            src="https://user-images.githubusercontent.com/104320234/188269169-bf7b987f-e597-46cd-a8e7-c227efde5679.png"
          />
          {/* <svg width="26" height="26" class="svg-icon mtn2"><path opacity=".5" d="M4.2 4H22a2 2 0 012 2v11.8a3 3 0 002-2.8V5a3 3 0 00-3-3H7a3 3 0 00-2.8 2z"></path><path d="M1 7c0-1.1.9-2 2-2h18a2 2 0 012 2v12a2 2 0 01-2 2h-2v5l-5-5H3a2 2 0 01-2-2V7zm10.6 11.3c.7 0 1.2-.5 1.2-1.2s-.5-1.2-1.2-1.2c-.6 0-1.2.4-1.2 1.2 0 .7.5 1.1 1.2 1.2zm2.2-5.4l1-.9c.3-.4.4-.9.4-1.4 0-1-.3-1.7-1-2.2-.6-.5-1.4-.7-2.4-.7-.8 0-1.4.2-2 .5-.7.5-1 1.4-1 2.8h1.9v-.1c0-.4 0-.7.2-1 .2-.4.5-.6 1-.6s.8.1 1 .4a1.3 1.3 0 010 1.8l-.4.3-1.4 1.3c-.3.4-.4 1-.4 1.6 0 0 0 .2.2.2h1.5c.2 0 .2-.1.2-.2l.1-.7.5-.7.6-.4z"></path></svg> */}
          <span>Get unstuck — ask a question</span>
        </SideMessge>
        <SideMessge>
          <img
            width="24"
            alt="Img"
            src="https://user-images.githubusercontent.com/104320234/188269240-b38cf6ed-34c3-43eb-b63c-51d9619ade19.png"
          />
          <span>Unlock new privileges like voting and commenting</span>
        </SideMessge>
        <SideMessge>
          <img
            width="24"
            alt="Img"
            src="https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png"
          />
          <span>Save your favorite tags, filters, and jobs</span>
        </SideMessge>
        <SideMessge>
          <img
            width="24"
            alt="Img"
            src="https://user-images.githubusercontent.com/104320234/188269328-d049a0e0-6514-4ea6-b207-d5f58d02e943.png"
          />
          <span>Earn reputation and badges</span>
        </SideMessge>

        <div className="collabo">
          Collaborate and share knowledge with a private group for FREE.
        </div>
        <GetMessage
          onClick={() =>
            window.open(
              "https://stackoverflow.co/teams/?utm_source=so-owned&utm_medium=product&utm_campaign=free-50&utm_content=public-sign-up",
              "_blank"
            )
          }
        >
          Get Stack Overflow for Teams free for up to 50 users.
        </GetMessage>
      </SideContainer>

      <SignupContainer>
        <SocialSign>
          <div className="google">
            <svg
              aria-hidden="true"
              class="native svg-icon iconGoogle"
              width="18"
              height="18"
              viewBox="0 0 18 18"
            >
              <path
                d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 0 0 2.38-5.88c0-.57-.05-.66-.15-1.18Z"
                fill="#4285F4"
              ></path>
              <path
                d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 0 1-7.18-2.54H1.83v2.07A8 8 0 0 0 8.98 17Z"
                fill="#34A853"
              ></path>
              <path
                d="M4.5 10.52a4.8 4.8 0 0 1 0-3.04V5.41H1.83a8 8 0 0 0 0 7.18l2.67-2.07Z"
                fill="#FBBC05"
              ></path>
              <path
                d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 0 0 1.83 5.4L4.5 7.49a4.77 4.77 0 0 1 4.48-3.3Z"
                fill="#EA4335"
              ></path>
            </svg>
            Sign up with Google
          </div>
        </SocialSign>
        <SocialSign>
          <div className="git">
            <svg
              aria-hidden="true"
              class="svg-icon iconGitHub"
              width="18"
              height="18"
              viewBox="0 0 18 18"
            >
              <path
                d="M9 1a8 8 0 0 0-2.53 15.59c.4.07.55-.17.55-.38l-.01-1.49c-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82a7.42 7.42 0 0 1 4 0c1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48l-.01 2.2c0 .21.15.46.55.38A8.01 8.01 0 0 0 9 1Z"
                fill="#FFFFFF"
              ></path>
            </svg>
            Sign up with GitHub
          </div>
        </SocialSign>
        <SignupForm />  
      </SignupContainer>
    </Container>
  );
};

export default Signup;
