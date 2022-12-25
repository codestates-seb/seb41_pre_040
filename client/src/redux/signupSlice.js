import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: [  {
    email: "code@gmail.com",
    display_name: "김코드",
    password: "1234",
    id: 1
  },
  {
    email: "state@gmail.com",
    display_name: "스테이츠",
    password: "1234",
    id: 2
  },
  {
    email: "preproject@gmail.com",
    display_name: "프리프로",
    password: "1234",
    id: 3
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
          email: action.payload.email,
          display_name: action.payload.display_name,
          password: action.payload.password,
          id: action.payload.id
        },
      ];
    },
  },
});

export const { signupList } = signupSlice.actions;
export default signupSlice.reducer;
