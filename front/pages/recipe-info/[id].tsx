import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const RecipeInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <div>RecipeInfoPage {id}</div>;
};

export default RecipeInfoPage;