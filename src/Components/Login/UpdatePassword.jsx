import React, { useContext, useRef, useState } from "react";
import "./UpdatePassword.css";
import { LoginContext } from "../../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const UpdatePassword = () => {
  const passRef1 = useRef(null);
  const passRef2 = useRef(null);
  const [message, setMessage] = useState("");
  const { user } = useContext(LoginContext);
  let nav = useNavigate();
  const checkPasswords = () => {
    if (passRef1.current.value !== passRef2.current.value) {
      setMessage("Passwords do not match");
    }
  };

  const ChangePassword = () => {
    if (passRef1.current.value === passRef2.current.value) {
      let obj = {
        email: user.email,
        password: passRef1.current.value,
      };

      let response = axios.put(
        "http://localhost:8080/api/v1/login/change-password",
        obj
      );
      response
        .then(() => {
          nav("/login");
        })
        .catch(() => {
          console.log("Failed");
        });
    }
  };

  return (
    <div className="update-password-container">
      <h2>Change Password</h2>
      <div className="input-group">
        <label>New Password:</label>
        <input
          type="password"
          ref={passRef1}
          placeholder="Enter new password"
        />
      </div>
      <div className="input-group">
        <label>Confirm Password:</label>
        <input
          type="password"
          ref={passRef2}
          onBlur={() => checkPasswords()}
          placeholder="Re-enter your password"
        />
        <button onClick={() => ChangePassword()}>Submit</button>
        {message && <span className="error-message">{message}</span>}
      </div>
    </div>
  );
};

export default UpdatePassword;
