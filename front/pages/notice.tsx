import { Box, List } from "@mui/material";
import { NextPage } from "next";
import { Mobile } from "../src/components/Mobile";
import styles from "../styles/Page.module.css";
import { BackHeader } from "../src/components/BackHeader";
import { ApiClient } from "../src/apis/apiClient";
import { getCookie } from "../src/utils/cookie";
import { NotificationInfo } from "../src/apis/responses/notificationInfo";
import { NotificationItem } from "../src/components/NotificationItem";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";
import { useEffect, useState } from "react";
import { Page } from "../src/components/Page";

interface IProps {}

const NoticePage: NextPage<IProps> = () => {
  const [notificationInfoList, setNotificationInfoList] = useState<NotificationInfo[]>([]);
  useEffect(() => {
    ApiClient.getInstance()
      .getNotificationInfoList(getCookie("userName"))
      .then((data) => setNotificationInfoList(data));
  }, []);

  const Comp = () =>
    notificationInfoList.length ? (
      <List>
        {notificationInfoList.map((v, i) => (
          <NotificationItem key={i} notificationInfo={v} />
        ))}
      </List>
    ) : (
      <p style={{ fontSize: "18px", fontWeight: "bold", margin: "15px" }}>알람 내역이 없습니다.</p>
    );
  return (
    <Page>
      <Box bgcolor="white" minHeight="100vh">
        <Desktop>
          <Box className={styles.PageforDesktop}>
            <Comp />
          </Box>
        </Desktop>
        <Tablet>
          <Box className={styles.PageforTablet}>
            <Comp />
          </Box>
        </Tablet>
        <Mobile>
          <BackHeader backgroundColor="white" />
          <Box className={styles.PageforMobile}>
            <Comp />
          </Box>
        </Mobile>
      </Box>
    </Page>
  );
};

export default NoticePage;
