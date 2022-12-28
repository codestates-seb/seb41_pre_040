import React from "react";
import styled from "styled-components";
import Tag from "./Tag";

const Tags = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
`;

const TagList = ({ tags }) => {
  // const dummyTags = ["JavaScript", "sql", "Java", "CSS", "React"];

  return (
    <Tags>
      {tags &&
        tags.map((el, idx) => {
          return <Tag content={el} key={idx} />;
        })}
    </Tags>
  );
};

export default TagList;
