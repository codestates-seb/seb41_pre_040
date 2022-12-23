import React from "react";
import styled from "styled-components";
import MarkdownRenderer from "../assets/MarkdownRenderer";
import TagList from "./TagsList";
import CommentsList from "./CommentsList";

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

const UserAndOptions = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 16px 0px;

  .options {
    display: flex;
    gap: 5px;
    font-size: 13px;
    color: #6a737c;
  }
`;

const PostLayout = (/* props로 렌더링? */) => {
  return (
    <Layout>
      <div className="votecell">투표칸</div>
      <div className="postcell">
        <div>
          <MarkdownRenderer />
        </div>
        <div className="tags">
          <TagList />
        </div>
        <UserAndOptions>
          <div className="options">
            <div>Share</div>
            <div>Edit</div>
            <div>Follow</div>
          </div>
          <div className="writer">
            <div>유저 정보</div>
          </div>
        </UserAndOptions>
      </div>
      <div className="comments">
        <CommentsList />
      </div>
    </Layout>
  );
};

export default PostLayout;
