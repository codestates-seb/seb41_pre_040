// import { useState } from "react";
import styled from "styled-components";

const TagsInput = styled.input`
  flex-grow: 1;
  border: none;
  &:focus {
    outline: none;
  }
`;

const TagBox = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  flex-wrap: wrap;
  /* min-height: 50px; */
  padding: 0.4em 0.5em;
  border-radius: 3px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 3px;

  &:focus-within {
    outline: none;
    border-color: #69b9f7;
    box-shadow: 0 0 4px #78bef5;
  }
`;

const TagItem = styled.div`
  display: flex;
  background-color: #dee9f1;
  font-size: 12px;
  color: #39678f;
  padding: 0.4em 0.5em;
  text-align: center;
  border-radius: 3px;
`;

const Button = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 15px;
  height: 15px;
  margin-left: 5px;
  font-size: 1.2rem;
  font-weight: bold;
  color: #39678f;

  &:hover {
    background-color: #39678f;
    border-radius: 3px;
    color: #dee9f1;
  }
`;

const TagInput = ({ tags, setTags }) => {
  const removeTags = (indexToRemove) => {
    setTags(tags.filter((_, index) => index !== indexToRemove));
  };

  const addTags = (event) => {
    // 뒤에 붙는 공백 지우기
    let tag = event.target.value;
    tag = tag.slice(0, tag.length - 1);
    const filtered = tags.filter((el) => el === tag);

    // 태그가 이미 존재하는 경우에는 자동으로 지워짐
    if (filtered.length > 0) event.target.value = "";

    if (tag !== "" && filtered.length === 0) {
      if (tags.length === 5) {
        event.target.value = "";
        return;
      }
      setTags([...tags, tag]);
      event.target.value = "";
    }
  };

  return (
    <>
      <TagBox>
        {tags &&
          tags.map((tagItem, idx) => {
            return (
              <TagItem key={idx}>
                <span>{tagItem}</span>
                <Button onClick={() => removeTags(idx)}>&times;</Button>
              </TagItem>
            );
          })}
        <TagsInput
          type="text"
          onKeyUp={(e) => (e.key === " " ? addTags(e) : null)}
        />
      </TagBox>
    </>
  );
};

export default TagInput;
