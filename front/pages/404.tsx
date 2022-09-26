import { NextPage } from "next";
import { Box } from "@mui/system";

interface IProps {}

const NotFoundPage: NextPage<IProps> = (props) => {
  return (
    <Box width="100vw" height="100vh" display="flex" alignItems="center" justifyContent="center">
      <p style={{ fontSize: "20px", fontWeight: "bold" }}>404 Not Found</p>
    </Box>
  );
};

export default NotFoundPage;
