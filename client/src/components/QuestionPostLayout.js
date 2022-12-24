import React from "react";
import styled from "styled-components";
import { Link, useNavigate } from "react-router-dom";
import MarkdownRenderer from "../assets/MarkdownRenderer";
import TagList from "./TagsList";
import CommentsList from "./CommentsList";
import { deleteQuestion } from "../redux/questionsSlice";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";

const Layout = styled.div`
  display: grid;
  grid-template-columns: max-content 1fr;

  .votecell {
    grid-column: 1;
    padding-right: 16px;
  }

  .postcell {
    grid-column: 2;
    padding-right: 16px;
    min-width: 0;

    .tags {
      margin-top: 24px;
      margin-bottom: 16px;
    }
  }
  .comments {
    grid-column: 2;
  }
`;

const VoteContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  > :not(.extra-padding) {
    margin: 2px;
  }

  .extra-padding {
    padding: 6px 0px;
    margin: 0px 11.5px;
  }

  .vote-count {
    display: flex;
    align-items: center;
    color: #6a737c;
    font-size: 1.6rem;
    font-weight: 500;
  }
`;

const UpDownIcon = styled.svg`
  width: 36px;
  height: 36px;
  fill: #babfc4;
`;

const BookmarkIcon = styled.svg`
  width: 18px;
  height: 18px;
  fill: #babfc4;
`;

const HistoryIcon = styled(BookmarkIcon)`
  width: 19px;
`;

const UserAndOptions = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 16px 0px;

  .author {
    font-size: 13px;
  }

  .options {
    display: flex;
    gap: 5px;
    font-size: 13px;
    color: #6a737c;
    #delete {
      color: #c22e32;
      &:hover {
        cursor: pointer;
        font-weight: bold;
      }
    }
  }
`;

const QuestionPostLayout = ({ id, content, tags }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const isLogged = useSelector((state) => state.user.isLogin);

  return (
    <Layout>
      <div className="votecell">
        <VoteContainer>
          <UpDownIcon>
            <path d="M2 25h32L18 9 2 25Z" />
          </UpDownIcon>
          <div className="vote-count">0</div>
          <UpDownIcon>
            <path d="M2 11h32L18 27 2 11Z" />
          </UpDownIcon>
          <BookmarkIcon>
            <path d="m9 10.6 4 2.66V3H5v10.26l4-2.66ZM3 17V3c0-1.1.9-2 2-2h8a2 2 0 0 1 2 2v14l-6-4-6 4Z" />
          </BookmarkIcon>
          <div className="extra-padding">
            <HistoryIcon>
              <path d="M3 9a8 8 0 1 1 3.73 6.77L8.2 14.3A6 6 0 1 0 5 9l3.01-.01-4 4-4-4h3L3 9Zm7-4h1.01L11 9.36l3.22 2.1-.6.93L10 10V5Z" />
            </HistoryIcon>
          </div>
        </VoteContainer>
      </div>
      <div className="postcell">
        <div>
          <MarkdownRenderer content={content} />
        </div>
        <div className="tags">
          <TagList tags={tags} />
        </div>
        <UserAndOptions>
          <div className="options">
            <div>Share</div>
            {isLogged ? <Link to="qrevise">Edit</Link> : <></>}
            <div
              id="delete"
              onClick={() => {
                let confirm = window.confirm("질문을 삭제하시겠습니까?");
                if (confirm === true) {
                  dispatch(
                    deleteQuestion({
                      id: id,
                    })
                  );
                  alert("질문이 삭제되었습니다.");
                  navigate("/questions");
                }
              }}
            >
              Delete
            </div>
          </div>
          <div className="author">
            <div>author: name</div>
          </div>
        </UserAndOptions>
      </div>
      <div className="comments">
        <CommentsList />
      </div>
    </Layout>
  );
};

export default QuestionPostLayout;
