import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: [  {
    userId: "idno1@naver.com",
    display_name: "첫번째 유저",
    location: "서울",
    reputation: "3",
    userImg: "https://user-images.githubusercontent.com/104320234/188269169-bf7b987f-e597-46cd-a8e7-c227efde5679.png",
    isLogin: false
  },
  {
    userId: "idno2@naver.com",
    display_name: "두번째 유저",
    location: "대전",
    reputation: "4",
    userImg: "https://user-images.githubusercontent.com/104320234/188269240-b38cf6ed-34c3-43eb-b63c-51d9619ade19.png",
    isLogin: false
  },
  {
    userId: "idno3@naver.com",
    display_name: "세번째 유저",
    location: "대구",
    reputation: "8",
    userImg: "https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png",
    isLogin: false
  },
  {
    userId: "40fighting@naver.com",
    display_name: "40기 화이팅",
    location: "서울",
    reputation: "3",
    userImg: "https://user-images.githubusercontent.com/104320234/188269240-b38cf6ed-34c3-43eb-b63c-51d9619ade19.png",
    isLogin: false
  },
  {
    userId: 1,
    display_name: 2,
    location: "대전",
    reputation: "4",
    userImg: "https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png",
    isLogin: false
  },
  {
    userId: 1,
    display_name: 3,
    location: "대구",
    reputation: "8",
    userImg: "https://user-images.githubusercontent.com/104320234/188269328-d049a0e0-6514-4ea6-b207-d5f58d02e943.png",
    isLogin: false
  },
  {
    userId: "idno1@naver.com",
    display_name: "첫번째 유저",
    location: "서울",
    reputation: "3",
    userImg: "https://user-images.githubusercontent.com/104320234/188269169-bf7b987f-e597-46cd-a8e7-c227efde5679.png",
    isLogin: false
  },
  {
    userId: "idno2@naver.com",
    display_name: "두번째 유저",
    location: "대전",
    reputation: "4",
    userImg: "https://pic.onlinewebfonts.com/svg/img_312847.png",
    isLogin: true
  },
  {
    userId: "idno3@naver.com",
    display_name: "세번째 유저",
    location: "대구",
    reputation: "8",
    userImg: "https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png",
    isLogin: false
  },
  {
    userId: 1,
    display_name: 2,
    location: "대전",
    reputation: "4",
    userImg: "https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png",
    isLogin: false
  },
  {
    userId: 1,
    display_name: 3,
    location: "대구",
    reputation: "8",
    userImg: "https://user-images.githubusercontent.com/104320234/188269328-d049a0e0-6514-4ea6-b207-d5f58d02e943.png",
    isLogin: false
  },
  {
    userId: "idno1@naver.com",
    display_name: "첫번째 유저",
    location: "서울",
    reputation: "3",
    userImg: "https://user-images.githubusercontent.com/104320234/188269169-bf7b987f-e597-46cd-a8e7-c227efde5679.png",
    isLogin: false
  },
  {
    userId: "idno2@naver.com",
    display_name: "두번째 유저",
    location: "대전",
    reputation: "4",
    userImg: "https://user-images.githubusercontent.com/104320234/188269240-b38cf6ed-34c3-43eb-b63c-51d9619ade19.png",
    isLogin: false
  },
  {
    userId: "idno3@naver.com",
    display_name: "세번째 유저",
    location: "대구",
    reputation: "8",
    userImg: "https://user-images.githubusercontent.com/104320234/188269297-ff59ed55-4627-49f3-a001-9e480ff2bc7d.png",
    isLogin: false
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
    loginSwitch: (state, action) =>{
      state.value = state.value.filter((e) => e.userId !== action.payload[0].userId)
      console.log("액션", action.payload)
      console.log("스테이츠 밸류", state.value)
      state.value.unshift(action.payload[0])
      // console.log("필터드",filtered)
      console.log("언싀프트 후스테이츠 밸류", state.value)

    }
  },
});

export const { usersList, loginSwitch } = usersSlice.actions;
export default usersSlice.reducer;
