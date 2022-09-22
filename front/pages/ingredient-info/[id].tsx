import { NextPage } from "next";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { saveRecentSearchLocalStorage, RecentSearch } from "../../src/utils/localStorageUtil";

interface IProps {}

const IngredientInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  useEffect(() => {
    if (id) {
      saveRecentSearchLocalStorage("ingredient", +(id as string), `이름 ${id}`);
    }
  }, [id]);
  return <div>IngredientInfoPage {id}</div>;
};

export default IngredientInfoPage;
