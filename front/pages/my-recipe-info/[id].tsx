import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const MyRecipeInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <div>MyRecipeInfoPage {id}</div>;
};

export default MyRecipeInfoPage;