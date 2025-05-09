import { useEffect, useRef } from "react";

const FinanceNewsWidget = () => {
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const script = document.createElement("script");
    script.src = "https://s3.tradingview.com/external-embedding/embed-widget-timeline.js";
    script.async = true;
    script.innerHTML = JSON.stringify({
      feedMode: "all_symbols",
      isTransparent: false,
      displayMode: "adaptive",
      width: "100%",
      height: "100%",
      colorTheme: "light",
      locale: "en",
    });

    if (containerRef.current) {
      containerRef.current.innerHTML = ""; // Clear previous content to avoid duplication
      containerRef.current.appendChild(script);
    }
  }, []);

  return (
    <div className="tradingview-widget-container h-[600px]">
      <div ref={containerRef} className="tradingview-widget-container__widget h-full" />
    </div>
  );
};

export default FinanceNewsWidget;
