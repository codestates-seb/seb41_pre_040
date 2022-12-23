import React from "react";
import styled from "styled-components";
import Tag from "./Tag";

const Tags = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
`;

const TagList = () => {
  const dummyTags = ["JavaScript", "MySQL", "Java", "CSS", "React"];

  return (
    <Tags>
      {dummyTags.map((el, idx) => {
        return <Tag content={el} key={idx} />;
      })}
    </Tags>
  );
};

export default TagList;
