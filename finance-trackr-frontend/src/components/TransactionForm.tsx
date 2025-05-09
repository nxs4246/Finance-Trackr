import {
    Dialog,
    DialogTrigger,
    DialogContent,
    DialogHeader,
    DialogTitle,
  } from "@/components/ui/dialog";
  import { Input } from "@/components/ui/input";
  import { Button } from "@/components/ui/button";
  import { Label } from "@/components/ui/label";
  import { useEffect, useState } from "react";
  import { saveTransaction } from "@/api/transactions";
  import type { Transaction } from "@/api/transactions";
  
  import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
  } from "@/components/ui/select";
  
  interface Props {
    onSuccess?: () => void;
    triggerLabel?: string;
    initialData?: Transaction;
  }
  
  export default function TransactionForm({
    onSuccess,
    triggerLabel = "Add Transaction",
    initialData,
  }: Props) {
    const [open, setOpen] = useState(false);
    const [type, setType] = useState("Income");
    const [amount, setAmount] = useState("");
    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
  
    useEffect(() => {
      if (initialData) {
        setType(initialData.type);
        setAmount(initialData.amount?.toString() || "");
        setDate(initialData.date);
        setDescription(initialData.description);
      }
    }, [initialData]);
  
    const handleSubmit = async () => {
      try {
        await saveTransaction({
          ...initialData,
          type,
          amount: parseFloat(amount),
          date,
          description,
        });
        setOpen(false);
        if (!initialData) {
          setType("Income");
          setAmount("");
          setDate("");
          setDescription("");
        }
        onSuccess?.();
      } catch (err) {
        console.error("Failed to save transaction", err);
      }
    };
  
    return (
      <Dialog open={open} onOpenChange={setOpen}>
        <DialogTrigger asChild>
          <Button variant={initialData ? "outline" : "default"}>
            {triggerLabel}
          </Button>
        </DialogTrigger>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {initialData ? "Edit Transaction" : "Add Transaction"}
            </DialogTitle>
          </DialogHeader>
  
          <div className="space-y-4 mt-2">
            <div className="space-y-2">
              <Label htmlFor="type">Type</Label>
              <Select value={type} onValueChange={setType}>
                <SelectTrigger id="type" className="w-full">
                  <SelectValue placeholder="Select type" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="Income">Income</SelectItem>
                  <SelectItem value="Expense">Expense</SelectItem>
                </SelectContent>
              </Select>
            </div>
  
            <div className="space-y-2">
              <Label htmlFor="amount">Amount</Label>
              <Input
                id="amount"
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
              />
            </div>
  
            <div className="space-y-2">
              <Label htmlFor="date">Date</Label>
              <Input
                id="date"
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
              />
            </div>
  
            <div className="space-y-2">
              <Label htmlFor="description">Description</Label>
              <Input
                id="description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              />
            </div>
  
            <Button className="w-full mt-2" onClick={handleSubmit}>
              Save
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    );
  }
  