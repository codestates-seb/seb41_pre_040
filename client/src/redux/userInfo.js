import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  value: { nickname: "", email: "" },
};

export const userInfo = createSlice({
  name: "userInfo",
  initialState: initialStateValue,
  reducers: {
    GetUserInfo: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { GetUserInfo } = userInfo.actions;

export default userInfo.reducer;
