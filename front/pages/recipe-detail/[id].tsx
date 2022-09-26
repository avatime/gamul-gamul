import { Box } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { BackHeader } from "../../src/components/BackHeader";
import { CardContainer } from "../../src/components/CardContainer";
import { InfoTitle } from "../../src/components/InfoTitle";
import { Mobile } from "../../src/components/Mobile";
import { MyInfoItem } from "../../src/components/MyInfoItem";
import { Navbar } from "../../src/components/Navbar";
import { getCookie } from "../../src/utils/cookie";
import styles from "../../styles/Page.module.css";

interface IProps {}

const RecipeDetailPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;

  const userId = getCookie("userName");
  return (
    <div>
      <Mobile>
        <BackHeader text={""} textColor={""} />

        <InfoTitle
          name={""}
          bookmark={false}
          onClickBookmark={function (): void {
            throw new Error("Function not implemented.");
          } }
          views={0}
          imagePath={""} />
        <Box className={styles.PageforMobile} sx={{ overflowX: "auto" }}>
          <Box sx={{ backgroundColor: "#000", width: "900px", heigth: "100vh" }}>
            <CardContainer title={"dd"}>dd</CardContainer>
          </Box>
          <Navbar activeIndex={3} />
        </Box>
      </Mobile>
    </div>
  );
};

export default RecipeDetailPage;
