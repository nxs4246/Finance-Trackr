import type { Transaction } from "@/api/transactions";
import { deleteTransaction } from "@/api/transactions";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import TransactionForm from "@/components/TransactionForm";
import { Button } from "@/components/ui/button";


export default function TransactionList({transactions, onRefresh,}: {transactions: Transaction[]; onRefresh: () => void;}) {
  return (
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>Date</TableHead>
          <TableHead>Type</TableHead>
          <TableHead>Amount</TableHead>
          <TableHead>Description</TableHead>
          <TableHead className="text-right">Actions</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {transactions.map((tx) => (
          <TableRow key={tx.id}>
            <TableCell>{tx.date}</TableCell>
            <TableCell>{tx.type}</TableCell>
            <TableCell>${tx.amount}</TableCell>
            <TableCell>{tx.description}</TableCell>
            <TableCell className="text-right">
              <div className="inline-flex items-center gap-2 justify-end">
                <TransactionForm
                  triggerLabel="Edit"
                  initialData={tx}
                  onSuccess={onRefresh}
                />
                <Button
                  variant="destructive"
                  size="sm"
                  className="h-9"
                  onClick={async () => {
                    if (confirm("Are you sure you want to delete this transaction?")) {
                      await deleteTransaction(tx.id!);
                      onRefresh();
                    }
                  }}
                >
                  Delete
                </Button>
              </div>
            </TableCell>

          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
}
