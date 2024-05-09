import React, { useContext, useState } from "react";
import { useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { LoginContext } from "../../App";
import "./UserLogin.css";

export const UserName = () => {
  const emailRef = useRef();
  const passRef = useRef();
  const [message, setMessage] = useState("");
  const { setUser } = useContext(LoginContext); // Remove unused 'user' state
  const nav = useNavigate();

  const validateEmail = async () => {
    try {
      await axios.get(
        `http://localhost:8080/api/v1/login/validate/email/${emailRef.current.value}`
      );
      setMessage("");
    } catch (error) {
      setMessage("Invalid Credentials");
    }
  };

  const validateUser = async () => {
    try {
      const obj = {
        email: emailRef.current.value,
        password: passRef.current.value,
      };

      const response = await axios.post(
        "http://localhost:8080/api/v1/login/validate/user",
        obj
      );

      setUser(response.data);

      if (response.data.logInStatus === "0") {
        nav("/changePassword");
      } else {
        nav("/home");
      }
    } catch (error) {
      console.log("Error occurred during user validation:", error);
      // Handle error accordingly, such as displaying a message to the user
    }
  };

  return (
    <div className="container">
      <div className="input-group">
        <div>{message}</div>
        <label htmlFor="username">Username:</label>
        <input
          type="email"
          ref={emailRef}
          onBlur={validateEmail}
          id="username"
          name="username"
          placeholder="Enter your Email"
        />
      </div>
      <div className="input-group">
        <label htmlFor="password">Password:</label>
        <input
          ref={passRef}
          type="password"
          id="password"
          name="password"
          placeholder="Enter your password"
        />
      </div>
      <button className="buttonshome" onClick={validateUser}>
        Login
      </button>
      <br></br>
      <button className="buttonshome" onClick={() => nav("/signup")}>
        Signup
      </button>
      <div className="forgot-password">
        <a onClick={() => nav("/forgotPassword")}>Forgot Password?</a>
      </div>
    </div>
  );
};

export default UserName;
