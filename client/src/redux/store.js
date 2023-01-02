import { configureStore } from "@reduxjs/toolkit";
import questionsReducer from "./questionsSlice";
import usersReducer from "./usersSlice";
import userReducer from "./user";
import userInfoReducer from "./userInfo";
import answersReducer from "./answersSlice";

export default configureStore({
  reducer: {
    questions: questionsReducer,
    users: usersReducer,
    user: userReducer,
    userInfo: userInfoReducer,
    answers: answersReducer,
  },
});
