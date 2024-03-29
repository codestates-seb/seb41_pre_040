import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from 'styled-components';
import UserSlot from "../components/UserSlot";
import Paging from "../components/Pagination";
// import { useSelector } from "react-redux";




const Userlist = styled.div`
  height: 100%;
  padding: 24px;
  border-left: 1px solid rgb(227, 230, 232);
  h1 {
    margin-bottom: 27px;
    color: rgb(35, 38, 41);
    font-size: 27px;
  }
`;

const FilterUser = styled.section`
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
`;


const SearchUser = styled.div`
  width: 200px;
`;

const SearchBar = styled.input`
  position: relative;
  display: flex;
  /* flex: 1 1 180px; */
  margin: 7px;
  width: 185px;
  height: 33px;
  font-size: 20px;
`
const FilterButtons = styled.div`
  display: inline-flex;
  button {
    margin-top: 6px;
    padding: 10.4px;
    font-size: 13px;
    border-radius: 3px;
    border: 1px solid #838c95;
    color: #6a737c;
    height: 35px;

    &:hover {
      background-color: #f8f9f9;
      color: #3b4045;
    }

    &:active {
      background-color: #e3e6e8;
      color: #3b4045;
    }
  }

  .left {
    border-right: 0px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }

  .middle {
    border-right: 0px;
    border-radius: 0px;
  }

  .right {
    border-top-left-radius: 0px;
    border-bottom-left-radius: 0px;
  }
`;

const DateButtons = styled.section`
  display: flex;
  justify-content: right;
  margin-bottom: 10px;

  button {
    padding: 8px 8px 10px 8px;
    border: none;
    color: #6a737c;
    font-size: 12px;
  }
`
const Userinfos = styled.section`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px;
  margin-top: 25px;
  margin-bottom: 40px;
`
const PageContainer = styled.div`
  display: 'flex';
  display: flex;
  justify-content: right;
  margin: 20px 0;
`;


const Users = () => {

  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);

  useEffect( ()=>{
    axios
    .get("https://api.stackexchange.com/2.3/users?page=2&pagesize=100&order=desc&sort=reputation&site=stackoverflow")
    .then((res)=> {
      setData([res.data.items]);
    })
    .then(()=> {
      setIsPending(false);
    })
    .catch((err)=>console.error(err));

  }, [])

  console.log("data: ",data)
  // const users = useSelector((state) => state.users.value);

  const [page, setPage] = useState(1);
  const handlePageChange = (page) => {setPage(page);};

  return (
    
    <Userlist>

      <h1>Users</h1>
      <FilterUser>
        <SearchUser>
          <SearchBar placeholder="Filter by user"/>
        </SearchUser>
        <FilterButtons>
          <button className="left">Reputation</button>
          <button className="middle">Creation</button>
          <button className="middle">Name</button>
          <button className="right">Modified</button>
        </FilterButtons>
      </FilterUser>
      <DateButtons>
        <button >All</button>
        <button >week</button>
        <button >month</button>
        <button >quater</button>
        <button >year</button>
      </DateButtons>
    {isPending ? ( <div> Loading </div> ) : ( 
      <Userinfos>
        {data[0].slice(12*(page-1), 12*(page-1)+12).map(  (el) => {
          return <UserSlot display_name={el.display_name} location={el.location} reputation={el.reputation} profile_image={el.profile_image } />;
        })}
      </Userinfos>
        )}
      {isPending ? ( <div>  </div> ) : ( 
      <PageContainer>
        <Paging activePage={page} itemsCountPerPage={12} totalItemsCount={data[0].length} onChange={handlePageChange} />
      </PageContainer>
      )}
    </Userlist>
  )
      
};

export default Users;
