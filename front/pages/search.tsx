import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const SearchPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { keyword } = router.query;
  return <div>SearchPage {keyword}</div>;
};

export default SearchPage;
