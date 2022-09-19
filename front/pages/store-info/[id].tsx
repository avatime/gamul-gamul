import { NextPage } from "next";
import { useRouter } from "next/router";

interface IProps {}

const StoreInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  return <div>StoreInfoPage {id}</div>;
};

export default StoreInfoPage;