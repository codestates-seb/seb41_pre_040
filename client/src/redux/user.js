import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  isLogin: true,
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
  },
});

export const { LoginStatus, LogoutStatus } = userSlice.actions;

export default userSlice.reducer;
