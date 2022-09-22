import { Box } from "@mui/material";
import { useRouter } from "next/router";
import { FC, useEffect, useState } from "react";
import { SearchHeaderBar } from "../SearchHeaderBar";
import { Desktop } from "../Desktop";
import { Tablet } from "../Tablet";
import { Mobile } from "../Mobile";
import { SearchResultComp } from "./SearchResultComp";
import { RecentSearchComp } from "./RecentSearchComp";
import { getRecentSearchLocalStorage, RecentSearch } from "../../utils/localStorageUtil";

interface IProps {
  showSearch: boolean;
  onCloseSearch: () => void;
}

export const SearchComp: FC<IProps> = ({ showSearch, onCloseSearch }) => {
  const router = useRouter();
  const keyword = router.query.searchKeyword as string;
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  useEffect(() => {
    if (keyword) {
      setSearchKeyword(keyword);
    }
  }, [keyword]);

  const onChange = (keyword: string) => {
    setSearchKeyword(keyword);
  };

  const onClickBack = () => {
    onCloseSearch();
    setSearchKeyword("");
  };

  const [recentList, setRecentList] = useState<RecentSearch[]>([]);
  useEffect(() => {
    setRecentList(getRecentSearchLocalStorage<RecentSearch>());
  }, [showSearch]);

  return (
    <Box>
      <Desktop>
        <Box
          className="page-background"
          borderRadius="20px"
          display="flex"
          flexDirection="column"
          width="698px"
          height="605px"
        >
          <SearchHeaderBar
            searchKeyword={searchKeyword}
            onChange={(e: any) => onChange(e.target.value)}
            onClickBack={onClickBack}
          />
          <Box flex={1} position="relative">
            {searchKeyword.length === 0 ? (
              <RecentSearchComp recentList={recentList} />
            ) : (
              <SearchResultComp searchKeyword={searchKeyword} />
            )}
          </Box>
        </Box>
      </Desktop>
      <Tablet>
        <Box
          className="page-background"
          borderRadius="20px"
          display="flex"
          flexDirection="column"
          width="698px"
          height="605px"
        >
          <SearchHeaderBar
            searchKeyword={searchKeyword}
            onChange={(e: any) => onChange(e.target.value)}
            onClickBack={onClickBack}
          />
          <Box flex={1} position="relative">
            {searchKeyword.length === 0 ? (
              <RecentSearchComp recentList={recentList} />
            ) : (
              <SearchResultComp searchKeyword={searchKeyword} />
            )}
          </Box>
        </Box>
      </Tablet>
      <Mobile>
        <Box
          className="page-background"
          display="flex"
          flexDirection="column"
          width="100vw"
          height="100vh"
        >
          <SearchHeaderBar
            searchKeyword={searchKeyword}
            onChange={(e: any) => onChange(e.target.value)}
            onClickBack={onClickBack}
          />
          <Box flex={1} position="relative">
            {searchKeyword.length === 0 ? (
              <RecentSearchComp recentList={recentList}/>
            ) : (
              <SearchResultComp searchKeyword={searchKeyword} />
            )}
          </Box>
        </Box>
      </Mobile>
    </Box>
  );
};
