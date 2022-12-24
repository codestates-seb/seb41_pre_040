import { createSlice } from "@reduxjs/toolkit";
// import { dummyUser } from "./dummyUser";

const initialState = {
  value: [  {
    "userId": 1,
    "id": 1,
    "location": "서울",
    "vote": "3"
  },
  {
    "userId": 1,
    "id": 2,
    "location": "대전",
    "vote": "4"
  },
  {
    "userId": 1,
    "id": 3,
    "location": "대구",
    "vote": "8"
  },
  {
    "userId": 1,
    "id": 1,
    "location": "서울",
    "vote": "3"
  },
  {
    "userId": 1,
    "id": 2,
    "location": "대전",
    "vote": "4"
  },
  {
    "userId": 1,
    "id": 3,
    "location": "대구",
    "vote": "8"
  },
  {
    "userId": 1,
    "id": 1,
    "location": "서울",
    "vote": "3"
  },
  {
    "userId": 1,
    "id": 2,
    "location": "대전",
    "vote": "4"
  },
  {
    "userId": 1,
    "id": 3,
    "location": "대구",
    "vote": "8"
  },
  {
    "userId": 1,
    "id": 1,
    "location": "서울",
    "vote": "3"
  },
  {
    "userId": 1,
    "id": 2,
    "location": "대전",
    "vote": "4"
  },
  {
    "userId": 1,
    "id": 3,
    "location": "대구",
    "vote": "8"
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
          id: action.payload.id,
          location: action.payload.location,
          vote: action.payload.vote,
        },
        ...state.value,
      ];
    },
  },
});

export const { usersList, paginate } = usersSlice.actions;
export default usersSlice.reducer;
