import React, { useState } from "react";
import styled from "styled-components";

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

  return (
    <WrapLogin>
      <form onSubmit>
        <div className="form-col">
          <label>Email</label>
          <input
            type="text"
            id="email-input"
            defaultValue={email}
            onChagne={(e) => setEmail(e.target.value)}
          ></input>
        </div>
        <div className="form-col">
          <label>Password</label>
          <input
            type="password"
            id="password-input"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          ></input>
        </div>
        <button className="Blue-button">Log in</button>
      </form>
    </WrapLogin>
  );
};

export default LoginForm;
