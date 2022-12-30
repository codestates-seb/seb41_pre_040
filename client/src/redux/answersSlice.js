import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

// ** 답변 하나 찾기
export const getAnswer = createAsyncThunk(
  "GET/ANSWER",
  async ({ questionId, answerId }) => {
    const response = await axios
      .get(`/api/questions/${questionId}`)
      .catch((err) => console.error(err));
    return response.data.answerDtoList.filter(
      (el) => el.id === Number(answerId)
    )[0];
  }
);

// ** 답변 등록
export const postAnswer = createAsyncThunk("POST/ANSWER", async (newAnswer) => {
  const response = await axios
    .post(`/api/questions/${newAnswer.questionId}/answers`, newAnswer)
    .catch((err) => console.error(err));
  return response.data;
});

// ** 답변 삭제
export const deleteAnswer = createAsyncThunk(
  "DELETE/ANSWER",
  async ({ questionId, answerId }) => {
    const response = await axios
      .delete(`/api/questions/${questionId}/answers/${answerId}`)
      .catch((err) => console.error(err));
    return response.data;
  }
);

// ** 답변 수정
export const editAnswer = createAsyncThunk(
  "EDIT/ANSWER",
  async (updatedAnswer) => {
    const response = await axios
      .patch(
        `/api/questions/${updatedAnswer.questionId}/answers/${updatedAnswer.answerId}`,
        updatedAnswer
      )
      .catch((err) => console.error(err));
    return response.data;
  }
);

const initialStateValue = {
  value: [],
  single: {},
};

export const answersSlice = createSlice({
  name: "answers",
  initialState: initialStateValue,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(getAnswer.pending, (state, action) => {
      state.single = {};
    });
    builder.addCase(getAnswer.fulfilled, (state, action) => {
      state.single = action.payload;
    });
  },
});

export default answersSlice.reducer;
