import { useState } from "react";
import { askAI } from "@/api/transactions";
import {
  Input
} from "@/components/ui/input";
import {
  Button
} from "@/components/ui/button";
import ReactMarkdown from "react-markdown";


export default function ChatAdvisor() {
  const [question, setQuestion] = useState("");
  const [response, setResponse] = useState("");
  const [loading, setLoading] = useState(false);

  const handleAsk = async () => {
    if (!question.trim()) return;

    setLoading(true);
    setResponse("Thinking...");
    try {
      const res = await askAI(question);
      setResponse(res.aiResponse);
    } catch (err) {
      setResponse("Error: Failed to get response.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex flex-col flex-1 min-h-0">
      {/* Input */}
      <div className="flex gap-2 mb-3">
        <Input
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
          placeholder="Ask your financial assistant..."
        />
        <Button onClick={handleAsk} disabled={loading}>
          {loading ? "Asking..." : "Ask"}
        </Button>
      </div>
  
      {/* Response */}
      <div className="flex-1 overflow-auto border rounded-md p-3 bg-muted text-muted-foreground text-sm mb-1">
        <ReactMarkdown
          components={{
            p: ({ children }) => <p className="mb-1">{children}</p>,
            h3: ({ children }) => <h3 className="text-base font-semibold mb-1">{children}</h3>,
            li: ({ children }) => <li className="ml-4 list-disc">{children}</li>,
          }}
        >
          {response}
        </ReactMarkdown>
      </div>
    </div>
  );  
}
