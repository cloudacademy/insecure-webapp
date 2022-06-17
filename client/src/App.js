import { Routes, Route } from "react-router-dom";
import { LoginPage } from "./pages/Login";
import { HomePage } from "./pages/Home";
import { ProtectedLayout } from "./components/ProtectedLayout";
import { ProfilePage } from "./pages/Profile";
import { SettingsPage } from "./pages/Settings";
import { CommentsPage } from "./pages/Comments";
import { CoderPage } from "./pages/Coder";
import { AsciiArtPage } from "./pages/AsciiArt";
import { HomeLayout } from "./components/HomeLayout";
import "./styles.css";

export default function App() {
  return (
    <Routes>
      <Route element={<HomeLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
      </Route>

      <Route path="/dashboard" element={<ProtectedLayout />}>
        <Route path="profile" element={<ProfilePage />} />
        <Route path="settings" element={<SettingsPage />} />
        <Route path="comments" element={<CommentsPage />} />
        <Route path="coder" element={<CoderPage />} />
        <Route path="ascii" element={<AsciiArtPage />} />
      </Route>
    </Routes>
  );
}
