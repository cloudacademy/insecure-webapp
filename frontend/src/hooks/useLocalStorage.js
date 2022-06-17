import { useState } from "react";

export const useLocalStorage = (keyName, defaultValue) => {
  const [storedValue, setStoredValue] = useState(() => {
    try {
      const value = window.localStorage.getItem(keyName);

      if (value) {
        console.log("here1...");
        return JSON.parse(value);
      } else {
        console.log("here2...");
        window.localStorage.setItem(keyName, JSON.stringify(defaultValue));
        return defaultValue;
      }
    } catch (err) {
      return defaultValue;
    }
  });

  const setValue = (newValue) => {
    try {
      console.log("here3...");
      console.log(keyName);
      console.log(newValue);
      //window.localStorage.setItem(keyName, JSON.stringify(newValue));
      window.localStorage.setItem(keyName, newValue);
    } catch (err) {}
    setStoredValue(newValue);
  };

  return [storedValue, setValue];
};
