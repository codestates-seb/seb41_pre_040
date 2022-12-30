import { configureStore } from "@reduxjs/toolkit";
import questionsReducer from "./questionsSlice";
import usersReducer from "./usersSlice";
import userReducer from "./user";
import signupReducer from "./signupSlice";
import userInfoReducer from "./userInfo";
import storage from "redux-persist/lib/storage";



export default configureStore({
  reducer: {
    questions: questionsReducer,
    users: usersReducer,
    user: userReducer,
    signupList: signupReducer,
    userInfo: userInfoReducer,
  },
});
