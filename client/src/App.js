import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Home from "./pages/Home";
import Questions from "./pages/Questions";
import WithSidebar from "./pages/WithSidebar";
import Signup from "./pages/Signup";
import Users from "./pages/Users";
import AskQuestion from "./pages/AskQuestion";
import Login from "./pages/Login";
import EditQuestion from "./pages/EditQuestion";
import QuestionDetail from "./pages/QuestionDetail";
import UserInfo from "./pages/UserInfo";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { LoginStatus } from "./redux/user";
import EditAnswer from "./pages/EditAnswer";


function App() {
  const dispatch = useDispatch();
  const authHandler = () => {
    /*
    초기 화면 렌더링시, 세션 스토리지를 확인하여 로그인 상태 유지 여부 결정
    */
    if (sessionStorage.getItem("user_id") === null) {
      return;
    } else {
      // 세션에 user_id라는 이름의 정보가 있다면 로그인을 유지해야 하므로 상태를 true로 바꾼다
      dispatch(LoginStatus());
    }
  };

  useEffect(() => {
    authHandler();
  }, []);

  return (
    <BrowserRouter>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<WithSidebar />}>
            <Route index element={<Home />}></Route>
            <Route path="/questions" element={<Questions />}></Route>
            <Route path="/users" element={<Users />}></Route>
            <Route
              path="/question/:questionId"
              element={<QuestionDetail />}
            ></Route>
            <Route path="/userinfo" element={<UserInfo />}></Route>
            <Route
              path="/question/:questionId/qrevise"
              element={<EditQuestion />}
            ></Route>
            <Route
              path="question/:questionId/answers/:answerId/arevise"
              element={<EditAnswer />}
            ></Route>
          </Route>
          {/* 로그인 페이지와 회원가입, 질문 작성 페이지 등 사이드바가 없는 페이지 라우팅은 이 아래에 */}
          <Route path="/signup" element={<Signup />}></Route>
          <Route path="/ask" element={<AskQuestion />}></Route>
          <Route path="/login" element={<Login />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
