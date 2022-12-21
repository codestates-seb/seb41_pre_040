import React from "react";
import styled from "styled-components";

const StyledFooter = styled.footer`
  border: 1px solid black;
  border-width: 1px 0px 0px 0px;
  height: 200px;
  width: 100vw;
  bottom: 0;
`;

const Footer = () => {
  return (
    <StyledFooter>
      <image>
        <span>STACK OVERFLOW</span>
        <span>PRODUCTS</span>
      </image>
    </StyledFooter>
  );
};

export default Footer;
