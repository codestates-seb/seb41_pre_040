import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Questions from "./pages/Questions";
import WithSidebar from "./pages/WithSidebar";
import Signup from "./pages/Signup";
import Users from "./pages/Users";

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
          </Route>
          {/* 로그인 페이지와 회원가입, 질문 작성 페이지 등 사이드바가 없는 페이지 라우팅은 이 아래에 */}
          <Route path="/signup" element={<Signup />}></Route>
        </Routes>

        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
