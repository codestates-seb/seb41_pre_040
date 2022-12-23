import React from "react";
import styled from "styled-components";
import ReactMarkdown from "react-markdown";
// import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";

const MarkdownStyle = styled.div`
  font-size: 0.95rem;
  line-height: 1.3rem;
`;

const dummyData = `
In **SQL Server**, it is possible to insert rows into a table with an \`INSERT.. SELECT\` statement:

    INSERT INTO Table (col1, col2, col3)
    SELECT col1, col2, col3 
    FROM other_table 
    WHERE sql = 'cool'

Is it also possible to _update_ a table with \`SELECT\`? I have a temporary table containing the values and would like to update another table using those values. Perhaps something like this:

    UPDATE Table SET col1, col2
    SELECT col1, col2 
    FROM other_table 
    WHERE sql = 'cool'
    WHERE Table.id = other_table.id

\`\`\`js
    function add(x, y) {
      return x + y;
    }
\`\`\`

> 인용문
`;

// 코드블럭 배경 색상: #f6f6f6;
// 인라인 코드 배경 색상: #e3e6e8;
// 코드 하이라이팅 적용하기

// const InlineCode = styled.span`
//   background-color: #f6f6f6;
//   border: 1px solid green;
// `;

// const InlineCodeBlock = ({ node, ...props }) => {
//   <InlineCode {...props}></InlineCode>;
// };

const MarkdownRenderer = () => {
  return (
    <MarkdownStyle>
      <ReactMarkdown
        children={dummyData}
        components={{
          inline: true,
        }}
      />
    </MarkdownStyle>
  );
};

export default MarkdownRenderer;
