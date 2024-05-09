import axios from "axios";
import React, { useRef, useState } from "react";
import "./ForgotPassword.css"; // Import the CSS file
import { useNavigate } from "react-router-dom";

const ForgotPassword = () => {
  let emailRef = useRef();
  let pass1Ref = useRef();
  let pass2Ref = useRef();
  let [message, setMessage] = useState("");
  let [passMsg, setPassMsg] = useState("");
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

  const checkPass = () => {
    if (pass1Ref.current.value !== pass2Ref.current.value) {
      setPassMsg("Passwords don't match");
    } else {
      setPassMsg("");
    }
  };

  const forgotPassword = () => {
    if (pass1Ref.current.value !== pass2Ref.current.value) {
      console.log("Passwords don't match");
    } else {
      let obj = {
        email: emailRef.current.value,
        password: pass2Ref.current.value,
      };
      axios
        .put("http://localhost:8080/api/v1/login/forgot-password", obj)
        .then(() => {
          nav("/login");
        })
        .catch((error) => {
          console.log("Failed to reset password:", error);
        });
    }
  };

  return (
    <div className="forgot-password-container">
      <span>{message}</span>
      <label className="input-label">Email</label>
      <input
        type="email"
        ref={emailRef}
        placeholder="Enter your Email"
        onBlur={() => validateEmail()}
        className="input-field"
      ></input>
      <label className="input-label">Password</label>
      <input
        type="password"
        ref={pass1Ref}
        placeholder="Enter your password"
        className="input-field"
      ></input>
      <label className="input-label">Confirm Password</label>
      <input
        type="password"
        ref={pass2Ref}
        placeholder="Re-enter your password"
        onBlur={() => checkPass()}
        className="input-field"
      ></input>
      <span className="error-message">{passMsg}</span>
      <button onClick={forgotPassword} className="submit-button">
        Submit
      </button>
    </div>
  );
};

export default ForgotPassword;
