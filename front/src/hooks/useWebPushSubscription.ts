import { useEffect } from "react";
import { base64ToUint8Array } from "../utils/fileUtil";

export const useWebPushSubscription = () => {
  useEffect(() => {
    if (typeof window !== "undefined" && "serviceWorker" in navigator) {
      navigator.serviceWorker.ready.then((reg) => {
        reg.pushManager.getSubscription().then(async (sub) => {
          if (!sub) {
            const temp = await reg.pushManager.subscribe({
              userVisibleOnly: true,
              applicationServerKey: base64ToUint8Array(process.env.NEXT_PUBLIC_WEB_PUSH_PUBLIC_KEY!),
            });
            console.log("AAAA", temp);
          } else {
            console.log("AAAA", sub);
          }
        });
      });
    }
  }, []);
};
