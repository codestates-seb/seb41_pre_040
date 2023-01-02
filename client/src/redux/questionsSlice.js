import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

// ** 질문 받아오기 (전체)
export const getQuestionsAll = createAsyncThunk(
  "GET/ALLQUESTIONS",
  async () => {
    const response = await axios
      .get("/api/")
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 질문 받아오기 (페이지별)
export const getQuestionsByPage = createAsyncThunk(
  "GET/QUESTIONS",
  async ({ page }) => {
    const response = await axios
      .get(`/api/questions?pages=${page - 1}`)
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 질문 받아오기 (개별)
export const getQuestionById = createAsyncThunk("GET/QUESTION", async (id) => {
  const response = await axios
    .get(`/api/questions/${id}`)
    .catch((err) => console.error(err));
  return response.data;
});

// ** 질문 받아오기 (검색 결과 전체)
export const getSearchResult = createAsyncThunk(
  "GET/SEARCH",
  async (keyword) => {
    const response = await axios
      .get(`/api/questions/search?searchKeyword=${keyword}`)
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 질문 받아오기 (검색 결과 페이지별)
export const getSearchResultByPage = createAsyncThunk(
  "GET/SEARCH/PAGE",
  async ({ keyword, page }) => {
    const response = await axios
      .get(
        `/api/questions/search?searchKeyword=${keyword}&page=${
          page - 1
        }&size=10`
      )
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 질문 수정
export const editQuestion = createAsyncThunk(
  "EDIT/QUESTION",
  async (updatedQuestion) => {
    const response = await axios
      .patch(`/api/questions/${updatedQuestion.id}`, updatedQuestion)
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 질문 등록
export const postQuestion = createAsyncThunk(
  "POST/QUESTION",
  async (newQuestion) => {
    const response = await axios
      .post("/api/questions", newQuestion)
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 질문 삭제
export const deleteQuestion = createAsyncThunk(
  "DELETE/QUESTION",
  // 해당하는 id만 받아와서 서버로 delete 요청 보내기
  async (questionId) => {
    const response = await axios
      .delete(`/api/questions/${questionId}`)
      .catch((err) => console.error(err));
    return questionId;
  }
);

const initialStateValue = {
  all: [],
  perPage: [],
  single: {},
  isSearchMode: false,
  keyword: "",
  answers: [],
};

export const questionsSlice = createSlice({
  name: "questions",
  initialState: initialStateValue,
  reducers: {
    setKeyword: (state, action) => {
      state.keyword = action.payload;
    },
    setIsSearchMode: (state, action) => {
      state.isSearchMode = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(getQuestionsAll.fulfilled, (state, action) => {
      state.isSearchMode = false;
      state.all = action.payload;
    });
    builder.addCase(getQuestionsByPage.fulfilled, (state, action) => {
      state.isSearchMode = false;
      state.perPage = action.payload;
    });
    builder.addCase(getSearchResult.fulfilled, (state, action) => {
      state.all = action.payload.content;
    });
    builder.addCase(getSearchResultByPage.fulfilled, (state, action) => {
      state.perPage = action.payload.content;
    });
    builder.addCase(getQuestionById.pending, (state, action) => {
      state.single = {};
    });
    builder.addCase(getQuestionById.fulfilled, (state, action) => {
      state.single = action.payload;
      state.answers = action.payload.answerDtoList;
    });
  },
});

export const { setKeyword, setIsSearchMode } = questionsSlice.actions;
export default questionsSlice.reducer;
