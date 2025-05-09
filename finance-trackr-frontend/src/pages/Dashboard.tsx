import { useEffect, useState } from "react";
import TransactionList from "@/components/TransactionList";
import ChatAdvisor from "@/components/ChatAdvisor";
import TransactionForm from "@/components/TransactionForm";
import { fetchTransactions } from "@/api/transactions";
import type { Transaction } from "@/api/transactions";
import { ModeToggle } from "@/components/mode-toggle";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import FinanceNewsWidget from "@/components/FinanceNewsWidget";
import MarketOverviewWidget from "@/components/MarketOverviewWidget";

export default function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await fetch("http://localhost:8080/logout", {
        method: "POST",
        credentials: "include",
      });
      navigate("/login");
    } catch (err) {
      console.error("Logout failed", err);
    }
  };

  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const transactionsPerPage = 10;
  
  const loadTransactions = async () => {
    const data = await fetchTransactions();
    setTransactions(data);
  };
  
  useEffect(() => {
    loadTransactions();
  }, []);

  useEffect(() => {
    setCurrentPage(1);
  }, [transactions]);
  
  const paginatedTransactions = transactions.slice(
    (currentPage - 1) * transactionsPerPage,
    currentPage * transactionsPerPage
  );

  return (
    <div className="p-4 max-w-7xl mx-auto font-inter">
      <div className="relative mb-4">
        <h1 className="text-2xl font-bold text-center">Finance Tracker Dashboard</h1>
        <div className="absolute top-0 right-0 flex items-center gap-2">
          <ModeToggle />
          <Button onClick={handleLogout}>
            Logout
          </Button>
        </div>
      </div>

      <div className="flex flex-col md:flex-row gap-4">
        {/* Left: Transactions */}
        <div className="w-full md:w-2/3 rounded-xl border bg-card text-card-foreground p-4 shadow h-[700px] flex flex-col">
          <div className="flex justify-between items-center mb-2">
            <h2 className="text-lg font-semibold">Transactions List</h2>
            <TransactionForm onSuccess={loadTransactions} />
          </div>
          <div className="flex-1 overflow-auto">
            <TransactionList transactions={paginatedTransactions} onRefresh={loadTransactions} />
          </div>
          
          <div className="mt-4 flex justify-center gap-2 ">
            {Array.from({ length: Math.ceil(transactions.length / transactionsPerPage) }).map((_, i) => (
              <Button
                key={i}
                variant={currentPage === i + 1 ? "default" : "outline"}
                size="sm"
                onClick={() => setCurrentPage(i + 1)}
              >
                {i + 1}
              </Button>
            ))}
          </div>
        </div>

        {/* Right: AI Assistant */}
        <div className="w-full md:w-1/3 rounded-xl border bg-card text-card-foreground p-4 shadow h-[700px] flex flex-col">
          <h2 className="text-lg font-semibold mb-2">AI Assistant</h2>
          <div className="flex-1 flex flex-col min-h-0"> 
            <ChatAdvisor />
          </div>
        </div>
      </div>

      <div className="flex flex-col md:flex-row gap-4 mt-4">
        {/* Left 2/3: News */}
        <div className="w-full md:w-2/3 rounded-xl border bg-card text-card-foreground p-4 shadow max-h-[700px] flex flex-col">
          <h2 className="text-lg font-semibold mb-2">Finance News</h2>
          <FinanceNewsWidget />
        </div>

        {/* Right 1/3: Market Overview */}
        <div className="w-full md:w-1/3 rounded-xl border bg-card text-card-foreground p-4 shadow max-h-[700px] flex flex-col">
          <h2 className="text-lg font-semibold mb-2">Market Overview</h2>
          <MarketOverviewWidget />
        </div>
      </div>

    </div>
  );
}
