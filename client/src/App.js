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
import EditQuestion from "./pages/EditQusetion";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<WithSidebar />}>
            <Route index element={<Home />}></Route>
            <Route path="/questions" element={<Questions />}></Route>
            <Route path="/users" element={<Users />}></Route>
            <Route path="/question/qrevise" element={<EditQuestion />}></Route>
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
