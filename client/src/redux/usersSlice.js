import { createSlice } from "@reduxjs/toolkit";
// import { dummyUser } from "./dummyUser";

const initialState = {
  value: [  {
    "userId": 1,
    "display_name": 1,
    "location": "서울",
    "reputation": "3"
  },
  {
    "userId": 1,
    "display_name": 2,
    "location": "대전",
    "reputation": "4"
  },
  {
    "userId": 1,
    "display_name": 3,
    "location": "대구",
    "reputation": "8"
  },
  {
    "userId": 1,
    "display_name": 1,
    "location": "서울",
    "reputation": "3"
  },
  {
    "userId": 1,
    "display_name": 2,
    "location": "대전",
    "reputation": "4"
  },
  {
    "userId": 1,
    "display_name": 3,
    "location": "대구",
    "reputation": "8"
  },
  {
    "userId": 1,
    "display_name": 1,
    "location": "서울",
    "reputation": "3"
  },
  {
    "userId": 1,
    "display_name": 2,
    "location": "대전",
    "reputation": "4"
  },
  {
    "userId": 1,
    "display_name": 3,
    "location": "대구",
    "reputation": "8"
  },
  {
    "userId": 1,
    "display_name": 1,
    "location": "서울",
    "reputation": "3"
  },
  {
    "userId": 1,
    "display_name": 2,
    "location": "대전",
    "reputation": "4"
  },
  {
    "userId": 1,
    "display_name": 3,
    "location": "대구",
    "reputation": "8"
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
        },
        ...state.value,
      ];
    },
  },
});

export const { usersList, paginate } = usersSlice.actions;
export default usersSlice.reducer;
