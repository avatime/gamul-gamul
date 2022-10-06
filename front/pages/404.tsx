import { NextPage } from "next";
import { Box } from "@mui/system";
import { Page } from "../src/components/Page";

interface IProps {}

const NotFoundPage: NextPage<IProps> = (props) => {
  return (
    <Page>
      <Box width="100vw" height="100vh" display="flex" alignItems="center" justifyContent="center">
        <p style={{ fontSize: "20px", fontWeight: "bold" }}>404 Not Found</p>
      </Box>
    </Page>
  );
};

export default NotFoundPage;
