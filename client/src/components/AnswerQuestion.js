import React, { useState } from "react";
import styled from "styled-components";
import MDEditor from "@uiw/react-md-editor";

const AnswerForm = styled.div`
  h2 {
    padding-top: 20px;
    margin: 0 0 1em 0;
  }

  .editor {
    margin-bottom: 10px;
  }
`;

const ManageButton = styled.div`
  display: flex;
  gap: 24px;
  margin-top: 10px;
  margin-bottom: 20px;
  padding-top: 10px;
  padding-bottom: 15px;

  button {
    border-radius: 3px;
    font-weight: 550;
    padding: 11px 11px;
    font-size: 0.8rem;
  }

  .submit-question {
    background-color: #0a95ff;
    color: white;
    &:hover {
      background-color: #126fd4;
    }
  }
`;

const AnswerQuestion = () => {
  const [value, setValue] = useState("");

  return (
    <AnswerForm>
      <h2>Your Answer</h2>
      <div className="editor">
        <MDEditor value={value} onChange={setValue} />
        <MDEditor.Markdown source={value} style={{ whiteSpace: "pre-wrap" }} />
      </div>
      <ManageButton>
        {/* 글 등록 구현 필요 */}
        {/* 글 등록 시 해당 글의 detail 페이지로 이동 */}
        <button className="submit-question">Post Your Answer</button>
      </ManageButton>
    </AnswerForm>
  );
};

export default AnswerQuestion;
