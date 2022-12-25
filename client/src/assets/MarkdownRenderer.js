import React from "react";
import styled from "styled-components";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";

const MarkdownStyle = styled.div`
  font-size: 0.95rem;
  line-height: 1.3rem;
  pre,
  code {
    font-size: 13px;
  }
`;

const MarkdownRenderer = ({ content }) => {
  return (
    <MarkdownStyle>
      <ReactMarkdown
        children={content}
        remarkPlugins={[remarkGfm]}
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
