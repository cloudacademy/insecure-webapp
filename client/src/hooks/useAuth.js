import { createContext, useContext, useMemo, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocalStorage } from "./useLocalStorage";
import axios from 'axios';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", null);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage["user"]) {
      console.log("useEffect called...");
      console.log(localStorage);
      if (localStorage.getItem("user") != 'null') {
        setUser(localStorage.getItem("user"));
      }
    }
  }, []);

  const login = async (data) => {
    console.log(data);

    var APIHOSTPORT = `${window._env_.REACT_APP_APIHOSTPORT}`;
    var URL = `http://${APIHOSTPORT}/login`;

    axios.post(URL, data).then((response) => {
        var token = response.data.token;
        console.log("token=" + token);
        setUser(token);
        navigate("/dashboard/profile", { replace: true });
    }).catch((err) => {
        console.log("access denied!!");
        console.log(err);
    })
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem("user");
    navigate("/", { replace: true });
  };

  const value = useMemo(
    () => ({
      user,
      login,
      logout
    }),
    [user]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  return useContext(AuthContext);
};
