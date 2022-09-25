import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const MyRecipeRegisterPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <div>MyRecipeRegisterPage {id}</div>;
};

export default MyRecipeRegisterPage;
