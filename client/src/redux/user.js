import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  email: "",
  nickname: "",
  isLogin: false,
};

export const userSlice = createSlice({
  name: "user",
  initialState: initialStateValue,
  reducers: {
    LoginStatus: (state, action) => {
      state.isLogin = true;
    },
    LogoutStatus: (state, action) => {
      state.isLogin = false;
    },
    getUserInfo: (state, action) => {
      state.email = action.payload;
      state.nickname = action.payload;
    },
  },
});

export const { LoginStatus, LogoutStatus, getUserInfo } = userSlice.actions;

export default userSlice.reducer;
