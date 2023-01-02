import { useState } from "react";
import styled from "styled-components";
import MDEditor from "@uiw/react-md-editor";
import { useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import { postAnswer } from "../redux/answersSlice";

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
  const dispatch = useDispatch();
  const { questionId } = useParams();
  const [inputValue, setInputValue] = useState("");

  const handleSubmit = () => {
    // 질문 등록 함수 구현하기
    dispatch(
      postAnswer({
        questionId: questionId,
        content: inputValue,
      })
    );
    alert("답변이 등록되었습니다.");
    window.location.reload();
    window.scrollTo(0, 0);
  };

  return (
    <AnswerForm>
      <h2>Your Answer</h2>
      <div className="editor">
        <MDEditor value={inputValue} onChange={setInputValue} />
        <MDEditor.Markdown
          source={inputValue}
          style={{ whiteSpace: "pre-wrap" }}
        />
      </div>
      <ManageButton>
        <button className="submit-question" onClick={handleSubmit}>
          Post Your Answer
        </button>
      </ManageButton>
    </AnswerForm>
  );
};

export default AnswerQuestion;
