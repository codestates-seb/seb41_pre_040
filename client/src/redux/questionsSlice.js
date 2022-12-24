import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  value: [],
};

export const questionsSlice = createSlice({
  name: "questions",
  initialState: initialStateValue,
  reducers: {
    postQuestion: (state, action) => {
      state.value = [
        {
          id: action.payload.id,
          title: action.payload.title,
          content: action.payload.content,
        },
        ...state.value,
      ];
    },
  },
});

export const { postQuestion } = questionsSlice.actions;
export default questionsSlice.reducer;
