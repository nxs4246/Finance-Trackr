import { useEffect, useState } from "react";
import { fetchTransactions } from "@/api/transactions";

export function useAuth() {
  const [authenticated, setAuthenticated] = useState<boolean | null>(null);

  useEffect(() => {
    fetchTransactions()
      .then(() => setAuthenticated(true))
      .catch(() => setAuthenticated(false));
  }, []);

  return authenticated;
}
