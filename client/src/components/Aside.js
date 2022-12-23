import React from "react";
import styled from "styled-components";
import { AsidebarSvg } from "../images/HomeSvg";
import icon1 from "../images/icon1.png";
import icon3 from "../images/icon3.png";

const AsideBar = styled.div`
  margin-left: auto;
  margin-right: auto;
  border: solid 1px hsl(47, 65%, 84%);
  border-radius: 4px;
  width: 300px;
  height: 390px;
  > ul {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    color: hsl(210, 8%, 25%);
    line-height: 30px;
    > li {
      background-color: hsl(47, 87%, 94%);
      display: flex;
      flex-direction: row;
      flex-grow: 1;

      > div {
        background-color: hsl(47, 87%, 94%);

        > img {
          color: hsl(210°, 3%, 40%);
          margin-left: 10px;
        }
      }
      > .icon3-div {
        width: 26px;
        > .icon3 {
          width: 26px;
          margin-top: 3px;
          margin-left: 3px;
        }
      }
      > .text {
        line-height: 20px;
        font-size: 13px;
        margin-left: 10px;
        margin-top: 5px;
      }
      > .Icon2 {
        margin-left: 10px;
        margin-top: 5px;
      }
    }
    > .title {
      border: solid 1px hsl(47, 65%, 84%);
      background-color: hsl(47, 83%, 91%);
      font-size: 12px;
      font-weight: bold;
      > p {
        margin: 0;
        margin-left: 10px;
        margin-top: 4px;
      }
    }
  }
`;

const aside = () => {
  return (
    <AsideBar>
      <ul>
        <li className="title">
          <p>The Overflow Blog</p>
        </li>
        <li>
          <div className="Icon2">
            <AsidebarSvg />
          </div>
          <div className="text">
            Best practices to increase the speed for Next.js apps
          </div>
        </li>
        <li>
          <div className="Icon2">
            <AsidebarSvg />
          </div>
          <div className="text">
            I spent two years trying to do what Backstage does for free
          </div>
        </li>
        <li className="title">
          <p>Featured on Meta</p>
        </li>
        <li>
          <div className="icon3-div">
            <img className="icon3" src={icon3} alt="icon3"></img>
          </div>
          <div className="text">Navigation and UI research starting soon</div>
        </li>
        <li>
          <div>
            <img src={icon1} alt="icon1"></img>
          </div>
          <div className="text">
            2022 Community Moderator Election Results - now with two more mods!
          </div>
        </li>
        <li>
          <div>
            <img src={icon1} alt="icon1"></img>
          </div>
          <div className="text">Temporary policy: ChatGPT is banned</div>
        </li>
        <li>
          <div>
            <img src={icon1} alt="icon1"></img>
          </div>
          <div className="text">I'm standing down as a moderator</div>
        </li>
        <li>
          <div>
            <img src={icon1} alt="icon1"></img>
          </div>
          <div className="text">
            Proposing a Community-Specific Closure Reason for non-English
            content
          </div>
        </li>
      </ul>
    </AsideBar>
  );
};

export default aside;
