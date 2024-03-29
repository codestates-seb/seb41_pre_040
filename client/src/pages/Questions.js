import { useEffect, useState } from "react";
import Question from "../components/Question";
import styled from "styled-components";
import AskButton from "../components/AskButton";
import { useSelector, useDispatch } from "react-redux";
import {
  getQuestionsAll,
  getQuestionsByPage,
  getSearchResult,
  getSearchResultByPage,
} from "../redux/questionsSlice";
import Paging from "../components/Pagination";

const MainContent = styled.div`
  width: calc(100% - 300px - 24px);
  padding: 24px;
`;

const TitleAndButton = styled.div`
  display: flex;
  align-items: center;
  padding-bottom: 30px;
  h1 {
    font-size: 1.6rem;
    margin: 0;
    font-weight: 500;
    flex: 1 auto;
  }
`;

const QuestionsContainer = styled.div`
  margin-left: -24px;
  min-height: 70vh;
  border-top: 1px solid #e3e6e8;
`;

const ButtonContainer = styled.div`
  margin-bottom: 16px;
  display: flex;
  justify-content: flex-end;
  align-items: center;

  .questions-length {
    flex: 1 auto;
    font-size: 1.2rem;
  }
`;

const FilterButtons = styled.div`
  display: inline-flex;
  margin-right: 16px;

  button {
    margin-right: 0;
    padding: 9.6px;
    font-size: 12px;
    border-radius: 3px;
    border: 1px solid #838c95;
    color: #6a737c;

    &:hover {
      background-color: #f8f9f9;
      color: #3b4045;
    }

    &:active {
      background-color: #e3e6e8;
      color: #3b4045;
    }
  }

  .left {
    border-right: 0px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }

  .middle {
    border-right: 0px;
    border-radius: 0px;
  }

  .right {
    border-top-left-radius: 0px;
    border-bottom-left-radius: 0px;
  }
`;

const ConditionFilter = styled.button`
  display: flex;
  align-items: center;
  gap: 3px;
  padding: 9.6px;
  font-size: 12px;
  border-radius: 3px;
  background-color: #dee9f1;
  color: #39739d;
  border: 1px solid #39739d;
  &:hover {
    background-color: #b3d3ea;
  }
`;

const FilterIcon = styled.svg`
  width: 16px;
  height: 16px;
  path {
    fill: #39739d;
  }
`;

const PageContainer = styled.div`
  display: "flex";
  display: flex;
  justify-content: right;
  margin: 20px 0;
`;

const Questions = () => {
  const dispatch = useDispatch();
  const allQuestions = useSelector((state) => state.questions.all);
  const questions = useSelector((state) => state.questions.perPage);
  const isSearchMode = useSelector((state) => state.questions.isSearchMode);
  const keyword = useSelector((state) => state.questions.keyword);

  // useEffect로 첫 번째 페이지의 아이템을 가져옴
  // 페이지가 바뀌면 api 통신으로 페이지별 데이터를 가져옴
  const [page, setPage] = useState(1);
  const handlePageChange = (page) => {
    setPage(page);
  };

  useEffect(() => {
    // console.log("검색 모드: ", isSearchMode);
    // console.log("키워드: ", keyword);
    dispatch(getQuestionsAll());
    if (isSearchMode) {
      dispatch(getSearchResult(keyword));
    } else {
      dispatch(getQuestionsAll());
      console.log("all: ", allQuestions);
    }
  }, []);

  // 페이지별 검색 결과 불러오기
  useEffect(() => {
    if (isSearchMode) {
      dispatch(getSearchResultByPage({ keyword: keyword, page: page }));
    } else {
      dispatch(getQuestionsByPage({ page: page }));
    }
    dispatch(getQuestionsByPage({ page: page }));
    window.scrollTo(0, 0);
  }, [page]);

  return (
    <MainContent>
      <TitleAndButton>
        <h1>{isSearchMode ? "Search Results" : "All Questions"}</h1>
        {/* <h1>All Questions</h1> */}
        <AskButton />
      </TitleAndButton>
      <ButtonContainer>
        <div className="questions-length">{allQuestions.length} questions</div>
        <FilterButtons>
          <button className="left">Newest</button>
          <button className="middle">Active</button>
          <button className="middle">Bountied</button>
          <button className="right">Unanswered</button>
        </FilterButtons>
        <ConditionFilter>
          <FilterIcon>
            <path d="M2 4h14v2H2V4Zm2 4h10v2H4V8Zm8 4H6v2h6v-2Z" />
          </FilterIcon>
          Filter
        </ConditionFilter>
      </ButtonContainer>
      <QuestionsContainer>
        {questions &&
          questions.map((el, idx) => {
            return (
              <Question
                key={idx}
                id={el.questionId}
                title={el.title}
                content={el.content1}
                tags={el.hashtags}
                createdAt={el.createdAt}
                author={el.nickname}
              />
            );
          })}
        <PageContainer>
          <Paging
            activePage={page}
            itemsCountPerPage={10}
            totalItemsCount={allQuestions.length}
            onChange={handlePageChange}
          />
        </PageContainer>
      </QuestionsContainer>
    </MainContent>
  );
};

export default Questions;
