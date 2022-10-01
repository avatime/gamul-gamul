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

interface IProps {
  notificationInfoList: NotificationInfo[];
}

const NoticePage: NextPage<IProps> = ({ notificationInfoList }) => {
  return (
    <Box bgcolor="white" minHeight="100vh">
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <List>
            {notificationInfoList.map((v, i) => (
              <NotificationItem key={i} notificationInfo={v} />
            ))}
          </List>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <List>
            {notificationInfoList.map((v, i) => (
              <NotificationItem key={i} notificationInfo={v} />
            ))}
          </List>
        </Box>
      </Tablet>
      <Mobile>
        <BackHeader backgroundColor="white" />
        <Box className={styles.PageforMobile}>
          <List>
            {notificationInfoList.map((v, i) => (
              <NotificationItem key={i} notificationInfo={v} />
            ))}
          </List>
        </Box>
      </Mobile>
    </Box>
  );
};

export default NoticePage;

export async function getServerSideProps() {
  const notificationInfoList = await ApiClient.getInstance().getNotificationInfoList(
    getCookie("userName")
  );

  return {
    props: {
      notificationInfoList,
    },
  };
}
