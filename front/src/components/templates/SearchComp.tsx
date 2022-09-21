import { Box } from "@mui/material";
import { useRouter } from "next/router";
import { FC, useEffect, useState } from "react";
import { SearchHeaderBar } from "../SearchHeaderBar";
import { Desktop } from "../Desktop";
import { Tablet } from "../Tablet";
import { Mobile } from "../Mobile";
import { SearchResultComp } from "./SearchResultComp";
import { RecentSearchComp } from "./RecentSearchComp";

interface IProps {
  onCloseSearch: () => void;
}

export const SearchComp: FC<IProps> = ({ onCloseSearch }) => {
  const router = useRouter();
  const keyword = router.query.searchKeyword as string;
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  useEffect(() => {
    if (keyword) {
      setSearchKeyword(keyword);
    }
  }, [keyword]);

  const onSearch = (searchKeyword: string) => {
    if (!searchKeyword) {
      return;
    }

    router.push({
      pathname: "/search",
      query: {
        searchKeyword,
      },
    });
  };
  const onChange = (keyword: string) => {
    setSearchKeyword(keyword);
  };

  const onClickBack = () => {
    onCloseSearch();
    setSearchKeyword("");
  };

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
            onSearch={() => onSearch(searchKeyword)}
            onChange={(e: any) => onChange(e.target.value)}
            onClickBack={onClickBack}
          />
          <Box flex={1} position="relative">
            {searchKeyword.length === 0 ? (
              <RecentSearchComp />
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
            onSearch={() => onSearch(searchKeyword)}
            onChange={(e: any) => onChange(e.target.value)}
            onClickBack={onClickBack}
          />
          <Box flex={1} position="relative">
            {searchKeyword.length === 0 ? (
              <RecentSearchComp />
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
            onSearch={() => onSearch(searchKeyword)}
            onChange={(e: any) => onChange(e.target.value)}
            onClickBack={onClickBack}
          />
          <Box flex={1} position="relative">
            {searchKeyword.length === 0 ? (
              <RecentSearchComp />
            ) : (
              <SearchResultComp searchKeyword={searchKeyword} />
            )}
          </Box>
        </Box>
      </Mobile>
    </Box>
  );
};
