export interface Transaction {
  id?: number;
  type: string;
  amount: number;
  date: string;
  description: string;
}

const BASE_URL = "http://localhost:8080/api/transactions";

export const fetchTransactions = async (): Promise<Transaction[]> => {
  const res = await fetch(BASE_URL, {
    credentials: "include",
  });

  if (!res.ok) throw new Error("Unauthorized or failed to fetch transactions");
  return res.json();
};

export const saveTransaction = async (transaction: Transaction) => {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(transaction),
    credentials: "include",
  });

  if (!res.ok) throw new Error("Failed to save transaction");
  return res.json();
};

export const deleteTransaction = async (id: number) => {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
    credentials: "include",
  });

  if (!res.ok) throw new Error("Failed to delete transaction");
};

export const askAI = async (userPrompt: string) => {
  const res = await fetch(`${BASE_URL}/chat`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userPrompt }),
    credentials: "include",
  });

  if (!res.ok) throw new Error("Failed to get AI response");
  return res.json();
};
