import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Dashboard from "@/pages/Dashboard";
import LoginPage from "@/pages/LoginPage";
import { useAuth } from "@/hooks/useAuth";
import type { ReactNode } from "react";
import { ThemeProvider } from "./components/theme-provider";

function ProtectedRoute({ children }: { children: ReactNode }) {
  const authenticated = useAuth();
  if (authenticated === null) return null;
  return authenticated ? children : <Navigate to="/login" />;
}

function PublicRoute({ children }: { children: ReactNode }) {
  const authenticated = useAuth();
  if (authenticated === null) return null;
  return authenticated ? <Navigate to="/" /> : children;
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={<ProtectedRoute><ThemeProvider><Dashboard /></ThemeProvider></ProtectedRoute>}
        />
        <Route
          path="/login"
          element={<PublicRoute><LoginPage /></PublicRoute>}
        />
      </Routes>
    </BrowserRouter>
  );
}
