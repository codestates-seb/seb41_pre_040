import React, { useState } from "react";
import styled from "styled-components";
import MDEditor from "@uiw/react-md-editor";
import TagInput from "../components/TagInput";
import { useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import QuestionTip from "../components/QuestionTip";
import { useDispatch, useSelector } from "react-redux";
import { postQuestion } from "../redux/questionsSlice";

const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
  background-color: #f8f9f9;
`;

const BodyContent = styled.div`
  min-height: 750px;
  overflow: visible;
  width: 1264px;
  padding: 0px 24px;
`;

const HeadAndNotice = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;

  .headline {
    display: flex;
    height: 130px;
    justify-content: space-between;
    align-items: center;
    h1 {
      font-size: 1.8rem;
    }
    img {
      height: 130px;
    }
  }
`;

const BoxContainer = styled.div`
  display: flex;
`;

const Box = styled.div`
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  width: 70%;
  padding: 24px;
  background-color: white;
  border: 1px solid #e3e6e8;
  border-radius: 3px;
  margin-bottom: 16px;

  .box-title {
    font-weight: 700;
  }

  .box-description {
    font-size: 13px;
    margin-top: 5px;
    margin-bottom: 10px;
  }

  .with-focus-css {
    border: 1px solid #babfc4;
    padding: 0.6em 0.7em;
    border-radius: 3px;
    &:focus {
      outline: none;
      border: 1px solid #69b9f7;
      box-shadow: 0 0 4px #78bef5;
    }
  }
`;

const ManageButton = styled.div`
  display: flex;
  gap: 24px;
  margin-top: 10px;
  margin-bottom: 20px;

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

  .cancel-question {
    color: #c22e32;
    &:hover {
      background-color: #fdf2f2;
    }
  }
`;

const AskQuestion = () => {
  const [titleInput, setTitleInput] = useState("");
  const [contentInput, setContentInput] = useState("");

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const questions = useSelector((state) => state.questions.value);

  const handleHistory = () => {
    navigate(-1);
  };

  return (
    <>
      <FormContainer>
        <BodyContent>
          <main>
            <HeadAndNotice>
              <div className="headline">
                <h1>Ask a public question</h1>
                <img
                  alt="background"
                  src="https://cdn.sstatic.net/Img/ask/background.svg?v=2e9a8205b368"
                />
              </div>
              <QuestionTip />
            </HeadAndNotice>
            <BoxContainer>
              <Box>
                <div className="box-title">Title</div>
                <div className="box-description">
                  Be specific and imagine you're asking a question to another
                  person.
                </div>
                <input
                  className="with-focus-css"
                  placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                  onChange={(e) => setTitleInput(e.target.value)}
                ></input>
              </Box>
            </BoxContainer>
            <BoxContainer>
              <Box>
                <div className="box-title">
                  What are the details of your problem?
                </div>
                <div className="box-description">
                  Introduce the problem and expand on what you put in the title.
                  Minimum 20 characters.
                </div>
                <div>
                  <MDEditor value={contentInput} onChange={setContentInput} />
                  <MDEditor.Markdown
                    source={contentInput}
                    style={{ whiteSpace: "pre-wrap" }}
                  />
                </div>
              </Box>
            </BoxContainer>
            <BoxContainer>
              <Box>
                <div className="box-title">Tags</div>
                <div className="box-description">
                  Add up to 5 tags to describe what your question is about.
                  Start typing to see suggestions.
                </div>
                <TagInput />
              </Box>
            </BoxContainer>
            <ManageButton>
              {/* 글 등록 구현 필요 */}
              {/* 글 등록 시 해당 글의 detail 페이지로 이동 */}
              <button
                className="submit-question"
                onClick={() => {
                  dispatch(
                    postQuestion({
                      id: questions.length + 1,
                      title: titleInput,
                      content: contentInput,
                    })
                  );
                  alert("질문이 등록되었습니다.");
                  // 일단은 home으로 이동하게 해놓기
                  navigate("/");
                }}
              >
                Review your question
              </button>
              <button className="cancel-question" onClick={handleHistory}>
                Cancel
              </button>
            </ManageButton>
          </main>
        </BodyContent>
      </FormContainer>
      <Footer />
    </>
  );
};

export default AskQuestion;
