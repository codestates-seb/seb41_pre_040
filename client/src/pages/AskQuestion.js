import React, { useState } from "react";
import styled from "styled-components";
import MDEditor from "@uiw/react-md-editor";
import Footer from "../components/Footer";
import QuestionTip from "../components/QuestionTip";

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

  input {
    padding: 0.6em 0.7em;
    border: 1px solid #babfc4;
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
    border: 1px solid black;
    border-radius: 3px;
    padding: 10.4px;
  }
`;

const AskQuestion = () => {
  const [value, setValue] = useState("");
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
                <input placeholder="e.g. Is there an R function for finding the index of an element in a vector?"></input>
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
                  <MDEditor value={value} onChange={setValue} />
                  <MDEditor.Markdown
                    source={value}
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
                <input placeholder="e.g. (css sql-server asp.net-mvc)" />
              </Box>
            </BoxContainer>
            <ManageButton>
              <button className="submit-question">Review your question</button>
              <button className="cancel-question">Cancel</button>
            </ManageButton>
          </main>
        </BodyContent>
      </FormContainer>
      <Footer />
    </>
  );
};

export default AskQuestion;
