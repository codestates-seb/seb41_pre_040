import React from "react";
import styled from "styled-components";
import ReactMarkdown from "react-markdown";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";

const MarkdownStyle = styled.div`
  font-size: 0.95rem;
  line-height: 1.3rem;
  pre,
  code {
    font-size: 13px;
  }
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
// 자바스크립트 코드 렌더링 테스트
function add(x, y) {
  return x + y;
}
\`\`\`
\`\`\`java
// 자바 코드 렌더링 테스트
public class Main {
  public static void main(String[] args) {
      System. out.println("Hello World" );
  }
}
\`\`\`
`;

// 코드블럭 배경 색상: #f6f6f6;
// 인라인 코드 배경 색상: #e3e6e8;
// 코드 하이라이팅 적용하기

const MarkdownRenderer = () => {
  return (
    <MarkdownStyle>
      <ReactMarkdown
        children={dummyData}
        components={{
          blockquote({ node, children, ...props }) {
            return (
              <div
                style={{
                  background: "#f6f6f6",
                  padding: "1px 15px",
                  borderLeft: "5px solid orange",
                }}
                {...props}
              >
                {children}
              </div>
            );
          },
          code({ node, inline, className, children, ...props }) {
            const match = /language-(\w+)/.exec(className || "");
            return inline ? (
              <code
                style={{
                  backgroundColor: "#e3e6e8",
                  padding: "2px",
                  borderRadius: "3px",
                }}
              >
                {children}
              </code>
            ) : match ? (
              <SyntaxHighlighter
                children={String(children).replace(/\n$/, "")}
                language={match[1]}
                PreTag="div"
                {...props}
              />
            ) : (
              <SyntaxHighlighter
                children={String(children).replace(/\n$/, "")}
                language="textile"
                PreTag="div"
                {...props}
              />
            );
          },
        }}
      />
    </MarkdownStyle>
  );
};

export default MarkdownRenderer;
