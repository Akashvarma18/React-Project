import React from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "../Components/Home/HomePage";
import UserName from "../Components/Login/UserLogin";
import { LoginPage } from "../Components/Login/LoginPage";
import UserRegister from "../Components/Login/UserRegister";
import UpdatePassword from "../Components/Login/UpdatePassword";
import ForgotPassword from "../Components/Login/ForgotPassword";

const AllRoutes = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<UserName />} />
        <Route path="/login" element={<UserName />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/signup" element={<UserRegister />} />
        <Route path="/changePassword" element={<UpdatePassword />} />
        <Route path="/forgotPassword" element={<ForgotPassword />} />
      </Routes>
    </div>
  );
};

export default AllRoutes;
