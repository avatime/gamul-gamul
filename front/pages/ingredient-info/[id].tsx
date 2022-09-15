import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const IngredientInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <div>IngredientInfoPage {id}</div>;
};

export default IngredientInfoPage;