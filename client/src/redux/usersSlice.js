import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: [  
  {
    // userId: sessionStorage.getItem("userId"),
    // display_name: sessionStorage.getItem("display_name"),
    userId: "",
    display_name: "",
    location: "",
    reputation: "",
    userImg: "",
    // https://pic.onlinewebfonts.com/svg/img_312847.png
  },
  ],
};

export const usersSlice = createSlice({
  name: "users",
  initialState: initialState,
  reducers: {
    usersList: (state, action) => {
      state.value = [
        {
          display_name: action.payload.display_name,
          location: action.payload.location,
          reputation: action.payload.reputation,
          userImg: action.payload.userImg,
          userId: action.payload.userId
        },
      ];
    },
    loginSwitch: (state, action) =>{
      state.value = state.value.filter((e) => e.userId !== action.payload[0].userId)
      state.value.unshift(action.payload[0])

    }
  },
});

export const { usersList, loginSwitch } = usersSlice.actions;
export default usersSlice.reducer;
