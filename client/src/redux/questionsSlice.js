import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  value: [
    {
      id: 2,
      title: "시간 변환 함수를 만들었어요",
      content:
        "근데 스택오버플로우 가보니까 질문 목록 페이지에 있는거랑 상세조회 페이지랑 변환이 다르게 되더라고요?",
      tags: ["ㅎㅎ"],
      created_at: "2022-12-25T08:18:11.687Z",
      modified_at: "2022-12-25T08:18:11.687Z",
    },
    {
      title: "그건 시간이 남는다면 나중에 더 해보겠습니다....",
      content: "ㅠㅠ",
      tags: ["s", "h", "sa"],
      created_at: "2022-12-25T08:10:58.169Z",
      modified_at: "2022-12-25T08:10:58.169Z",
      id: 1,
    },
    {
      id: 0,
      title: "오류를 찾게 된다면",
      content: "슬며시 알려주세요",
      tags: [],
      created_at: "2022-12-22T05:38:20.025Z",
      modified_at: "2022-12-22T05:38:20.025Z",
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
          created_at: action.payload.created_at,
          modified_at: action.payload.modified_at,
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
