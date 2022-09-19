import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const RecipeDetailPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <div>RecipeDetailPage {id}</div>;
};

export default RecipeDetailPage;