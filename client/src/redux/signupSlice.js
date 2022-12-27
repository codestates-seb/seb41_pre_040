import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: [  {
    userId: "code@gmail.com",
    display_name: "김코드",
    password: "1234",
    reputation: 0,
    userImg: "",
    location: "서울",
    isLogin: false
  },
  {
    userId: "state@gmail.com",
    display_name: "스테이츠",
    password: "1234",
    reputation: 0,
    userImg: "",
    location: "대전",
    isLogin: false
  },
  {
    userId: "preproject@gmail.com",
    display_name: "프리프로",
    password: "1234",
    reputation: 0,
    userImg: "",
    location: "대구",
    isLogin: false
  },
  ],
};

export const signupSlice = createSlice({
  name: "signup",
  initialState: initialState,
  reducers: {
    signupList: (state, action) => {
      state.value = [...state.value,
        {
          userId: action.payload.userId,
          display_name: action.payload.display_name,
          password: action.payload.password,
          userImg: action.payload.userImg,
          reputation: action.payload.reputation,
          location: action.payload.location,
          isLogin: action.payload.isLogin
        },
      ];
    },
  },
});

export const { signupList } = signupSlice.actions;
export default signupSlice.reducer;
