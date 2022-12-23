import React from "react";
import styled from "styled-components";
import { FooterBigSvg } from "../images/HomeSvg";
import { Link } from "react-router-dom";

const StyledFooter = styled.footer`
  background-color: #232629;
  color: hsl(210, 8%, 60%);
  height: 380px;
  width: 100%;
  bottom: 0;
  display: flex;

  > .footer--container {
    box-sizing: border-box;
    max-width: 1200px;
    width: 100%;
    margin: 0 auto;
    padding: 32px 12px 12px 12px;
    display: flex;
    flex-flow: row wrap;

    > .footer-logo {
      flex: 0 0 64px;
    }

    > .footer-nav {
      display: flex;
      flex: 2 1 auto;
      flex-wrap: wrap;
      > .footer-col {
        padding: 0 12px 24px 0;
        flex: 1 0 auto;
        > h5 {
          margin-top: 0;
          margin-bottom: 12px;
          font-size: 13px;
          font-weight: bold;
          color: hsl(210, 8%, 75%);
        }
        > ul {
          font-size: 13px;
          > li {
            margin: 0;
            padding: 4px 0;
          }
        }
      }
    }
    > .footer-copyright {
      display: flex;
      flex: 1 1 150px;
      flex-direction: column;
      box-sizing: border-box;
      font-size: 11px;
      margin-left: 10px;
      > ul {
        display: flex;
        > li {
          padding: 4px 0;
          margin-left: 12px;
          font-size: 11px;
          :first-child {
            margin-left: 0;
          }
        }
      }
      > p {
        margin-top: auto;
        margin-bottom: 24px;
        line-height: 1.4;
      }
    }
  }
`;

const Footer = () => {
  return (
    <StyledFooter>
      <div className="footer--container">
        <div className="footer-logo">
          <Link to="/question/id/qrevise">
            <FooterBigSvg />
          </Link>
        </div>
        <nav className="footer-nav">
          <div className="footer-col">
            <h5>STACK OVERFLOW</h5>
            <ul>
              <li>Questions</li>
              <li>Help</li>
            </ul>
          </div>
          <div className="footer-col">
            <h5>PRODUCTS</h5>
            <ul>
              <li>Teams</li>
              <li>Advertising</li>
              <li>Collectives</li>
              <li>Talent</li>
            </ul>
          </div>
          <div className="footer-col">
            <h5>COMPANY</h5>
            <ul>
              <li>About</li>
              <li>Press</li>
              <li>Work Here</li>
              <li>Legal</li>
              <li>Privacy Policy</li>
              <li>Terms of Service</li>
              <li>Contact Us</li>
              <li>Cookie Settings</li>
              <li>Cookie Policy</li>
            </ul>
          </div>
          <div className="footer-col">
            <h5>STACK EXCHANGE NETWORK</h5>
            <ul>
              <li>Technology</li>
              <li>Culture & recreation</li>
              <li>Life & arts</li>
              <li>Science</li>
              <li>Professional</li>
              <li>Business</li>
              <li></li>
              <li>API</li>
              <li>Data</li>
            </ul>
          </div>
        </nav>
        <div className="footer-copyright">
          <ul>
            <li>Blog</li>
            <li>Facebook</li>
            <li>Twitter</li>
            <li>LinkedIn</li>
            <li>Instagram</li>
          </ul>
          <p className="footer--copyright">
            Site design / logo © 2022 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. <span>rev 2022.12.21.43127</span>
          </p>
        </div>
      </div>
    </StyledFooter>
  );
};

export default Footer;
