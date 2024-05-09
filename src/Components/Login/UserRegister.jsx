import axios from "axios";
import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
import "./UserRegister.css";

export const UserRegister = () => {
  let nav = useNavigate();
  let firstNameRef = useRef();
  let lastNameRef = useRef();
  let emailRef = useRef();
  let designationRef = useRef();
  let roleIdRef = useRef();

  let saveUser = () => {
    const userDetails = {
      firstName: firstNameRef.current.value,
      lastName: lastNameRef.current.value,
      email: emailRef.current.value,
      designation: designationRef.current.value,
      roleId: roleIdRef.current.value,
    };

    axios
      .post("http://localhost:8080/api/v1/user/save", userDetails)
      .then((res) => {
        nav("/login");
      })
      .catch(() => {
        nav("/signup");
      });
  };

  return (
    <div className="container">
      <label htmlFor="firstName">First Name:</label>
      <input
        type="text"
        id="firstName"
        ref={firstNameRef}
        placeholder="Enter First Name"
      />
      <label htmlFor="lastName">Last Name:</label>
      <input
        type="text"
        id="lastName"
        ref={lastNameRef}
        placeholder="Enter Last Name"
      />
      <label htmlFor="email">Email:</label>
      <input type="email" id="email" ref={emailRef} placeholder="Enter Email" />
      <label htmlFor="designation">Designation:</label>
      <input
        type="text"
        id="designation"
        ref={designationRef}
        placeholder="Enter Designation"
      />
      <label htmlFor="roleId">Role ID:</label>
      <input
        type="text"
        id="roleId"
        ref={roleIdRef}
        placeholder="Enter Role ID"
      />
      <button onClick={saveUser}>Register</button>
    </div>
  );
};

export default UserRegister;
