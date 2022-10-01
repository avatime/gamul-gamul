import { useEffect } from "react";
import { ApiClient } from "../apis/apiClient";
import { getCookie } from "../utils/cookie";
import { base64ToUint8Array } from "../utils/fileUtil";

export const useWebPushSubscription = () => {
  useEffect(() => {
    if (typeof window !== "undefined" && "serviceWorker" in navigator) {
      navigator.serviceWorker.ready.then((reg) => {
        reg.pushManager.getSubscription().then(async (res) => {
          let subscription;
          if (res) {
            subscription = res;
          } else {
            subscription = await reg.pushManager.subscribe({
              userVisibleOnly: true,
              applicationServerKey: base64ToUint8Array(
                process.env.NEXT_PUBLIC_WEB_PUSH_PUBLIC_KEY!
              ),
            });
          }

          ApiClient.getInstance().postSubscription(getCookie("userName"), JSON.stringify(subscription));
        });
      });
    }
  }, []);
};
