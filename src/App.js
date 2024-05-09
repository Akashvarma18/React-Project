import React, { createContext, useState } from "react";
import logo from "./logo.svg";
import "./App.css";
import UserName from "./Components/Login/UserLogin";
import AllRoutes from "./routes/AllRoutes";

let LoginContext = createContext();

function App() {
  let [user, setUser] = useState(null);
  return (
    <div className="App">
      <LoginContext.Provider value={{ user, setUser }}>
        <AllRoutes />
      </LoginContext.Provider>
    </div>
  );
}
export default App;
export { LoginContext };
