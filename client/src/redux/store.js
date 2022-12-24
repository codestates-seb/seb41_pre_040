import { configureStore } from "@reduxjs/toolkit";
import questionsReducer from "./questionsSlice";
import useReducer from "./user";

export default configureStore({
  reducer: {
    questions: questionsReducer,
    user: useReducer,
  },
});
