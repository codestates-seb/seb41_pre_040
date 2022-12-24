import React from "react";
import styled from "styled-components";
import MDEditor from "@uiw/react-md-editor";
import TagInput from "../components/TagInput";
import { useState } from "react";

const Container = styled.div`
  display: flex;
  flex-direction: row;
  min-height: 1330.13px;
  max-width: 1264px;
  margin-right: auto;
  margin-left: auto;
  > .content {
    display: flex;
    flex-direction: row;
    width: 100%;
    padding: 24px;
    > .EditContainer {
      display: flex;
      flex-direction: column;
      width: 662px;
      > div {
        display: flex;
        flex-direction: column;
      }
      > .topSign {
        background-color: hsl(47, 87%, 94%);
        border: 1px solid hsl(47, 69%, 69%);
        border-radius: 4px;
        height: 115px;
        width: 100%;
        padding: 16px;

        > p {
          font-size: 13px;
          color: hsl(210, 8%, 25%);
          margin: 0%;
          line-height: 17px;
        }
        .first {
          margin-bottom: 13px;
        }
      }
      > .FormContainer {
        > .FormBox {
          width: 100%;
          display: flex;
          flex-direction: column;
          padding-bottom: 15px;
          margin-top: 10px;

          > input {
            border: 1px solid hsl(210, 8%, 75%);
            border-radius: 3px;
            height: 33px;
            width: 100%;

            &:focus {
              border: 1px solid hsl(206, 100%, 40%);
              outline: 3px solid hsl(206, 96%, 90%);
            }
          }
          > label {
            font-weight: bold;
            margin-bottom: 4px;
          }
          > div {
            margin-top: 10px;
            padding-bottom: 15px;
            display: flex;
            flex-direction: column;
            > input {
              border-radius: 3px;
              border: 1px solid hsl(210, 8%, 75%);
              height: 33px;

              &:focus {
                border: 1px solid hsl(206, 100%, 40%);
                outline: 3px solid hsl(206, 96%, 90%);
              }
            }
            > label {
              font-weight: bold;
              margin-bottom: 4px;
            }
            > .BlueButton {
              background-color: hsl(206, 100%, 52%);
              color: white;
              font-size: 13px;
              width: 84px;
              height: 38px;
              border-radius: 4px;

              &:hover {
                background-color: hsl(206, 100%, 40%);
              }
            }
          }
          > .AddComment {
            color: hsl(210, 8%, 55%);
            opacity: 0.6;
            font-size: 13px;
          }
        }
      }
    }
    > .EditSidebar {
      margin-top: 5px;
      width: 365px;
      height: 210px;
      margin-left: 30px;
      > .sidebar-container {
        background-color: hsl(47, 87%, 94%);
        width: 100%;
        height: 100%;
        border: 1px solid hsl(47, 65%, 84%);
        border-radius: 5px;
        > div {
          border-bottom: 1px solid hsl(47, 65%, 84%);
          padding: 12px 15px;
          height: 44.61px;
          color: hsl(210, 8%, 25%);
          font-size: 15px;
          background-color: hsl(47, 83%, 91%);
        }
        > ul {
          margin-left: 25px;
          padding: 4px 15px;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
          background-color: hsl(47, 87%, 94%);

          > li {
            color: hsl(210, 8%, 25%);
            margin: 8px 0px;
            height: 13px;
            width: 333px;
            font-size: 13px;
            background-color: hsl(47, 87%, 94%);
            list-style: disc;
          }
        }
      }
    }
  }
`;

const EditQuestion = () => {
  const [value, setValue] = useState("");
  return (
    <div>
      <Container>
        <div className="content">
          <div className="EditContainer">
            <div className="topSign">
              <p className="first">
                Your edit will be placed in a queue until it is peer reviewed.
              </p>
              <p>
                We welcome edits that make the post easier to understand and
                more valuable for readers. Because community members review
                edits, please try to make the post substantially better than how
                you found it, for example, by fixing grammar or adding
                additional resources and hyperlinks.
              </p>
            </div>
            <div className="FormContainer">
              <div className="FormBox">
                <label>Title</label>
                <input type="text"></input>
              </div>
              <div className="FormBox">
                <label>Body</label>
                <div>
                  <MDEditor value={value} onChange={setValue} />
                  <MDEditor.Markdown
                    source={value}
                    style={{ whiteSpace: "pre-wrap" }}
                  />
                </div>
                <div>
                  <label>Tags</label>
                  <TagInput></TagInput>
                </div>
                <div>
                  <label>Edit Summary</label>
                  <input placeholder="briefly explain your changes (corrected spelling, fixed grammar, improved formatting)"></input>
                </div>
                <div>
                  <button className="BlueButton">Save edits</button>
                </div>
                <div className="AddComment">Add a comment</div>
              </div>
            </div>
          </div>
          <div className="EditSidebar">
            <div className="sidebar-container">
              <div>How to Edit</div>
              <ul>
                <li> Correct minor typos or mistakes</li>
                <li> Clarify meaning without changing it</li>
                <li> Add related resources or links</li>
                <li> Always respect the author’s intent</li>
                <li> Don’t use edits to reply to the author</li>
              </ul>
            </div>
          </div>
        </div>
      </Container>
    </div>
  );
};

export default EditQuestion;
