import React from "react";
import "./Sidebar.css";

const Sidebar = () => {
  return (
    <div className="sidebar-container">
      <div className="sidebar">
        <div className="sidebar-header d-inline">APPLE</div>
        <ul className="sidebar-menu">
          <li>HOME</li>
          <li>BLOG</li>
          <li>CHANGE PASSWORD</li>
          
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;
