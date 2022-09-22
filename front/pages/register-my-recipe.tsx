import { Box } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const MyRecipeRegisterPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <Box>MyRecipeRegisterPage {id}</Box>;
};

export default MyRecipeRegisterPage;
