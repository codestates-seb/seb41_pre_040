import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  value: [
    {
      id: 0,
      title: "제목",
      content: "내용",
      tags: ["태그1", "태그2", "태그3", "태그4"],
    },
    {
      id: 1,
      title: "프로젝트 화이팅",
      content: "저희 되게 잘하고 있어요",
      tags: ["화", "이", "팅"],
    },
    {
      id: 2,
      title: "여러분",
      content: "메리크리스마스입니다",
      tags: ["1", "2", "3", "4"],
    },
    {
      id: 3,
      title: "2023년이라니",
      content: "말도안된다!",
      tags: ["a", "b", "c", "d"],
    },
  ],
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
          tags: action.payload.tags,
        },
        ...state.value,
      ];
    },
    editQuestion: (state, action) => {
      state.value.forEach((el) => {
        if (el.id === action.payload.id) {
          el.title = action.payload.title;
          el.content = action.payload.content;
          el.tags = action.payload.tags;
        }
      });
    },
    deleteQuestion: (state, action) => {
      // 아이디 일치하는 것만 제외하고
      // 그걸 state.value에 재할당하기
      state.value = state.value.filter((el) => el.id !== action.payload.id);
    },
  },
});

export const { postQuestion, editQuestion, deleteQuestion } =
  questionsSlice.actions;
export default questionsSlice.reducer;
