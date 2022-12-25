import { configureStore } from "@reduxjs/toolkit";
import questionsReducer from "./questionsSlice";
import usersReducer from "./usersSlice";
import useReducer from "./user";
import signupReducer from "./signupSlice";

export default configureStore({
  reducer: {
    questions: questionsReducer,
    users: usersReducer,
    user: useReducer,
    signupList: signupReducer,

  },
});
