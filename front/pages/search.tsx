import { Box, Button } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { SearchHeaderBar } from "../src/components/SearchHeaderBar";
import { SearchKeywordComp } from "../src/components/templates/SearchKeywordComp";
import { saveSearchLocalStorage, getSearchLocalStorage } from "../src/utils/localStorageUtil";

interface IProps {}

const SearchPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const keyword = router.query.searchKeyword as string;
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  useEffect(() => {
    setSearchKeyword(keyword);
  }, [keyword]);

  const onSearch = (searchKeyword: string) => {
    if (!searchKeyword) {
      return;
    }
    saveSearchLocalStorage<string>("keyword", searchKeyword);
    router.push({
      pathname: "/search-result",
      query: {
        searchKeyword,
      },
    });
  };
  const onChange = (keyword: string) => {
    setSearchKeyword(keyword);
  };

  const [searchKeywordList, setSearchKeywordList] = useState<string[]>([]);
  useEffect(() => {
    setSearchKeywordList(
      getSearchLocalStorage<string>("keyword", (item) => item.includes(searchKeyword))
    );
  }, [searchKeyword]);

  return (
    <Box className="page-background" display="flex" flexDirection="column">
      <SearchHeaderBar
        searchKeyword={searchKeyword}
        onSearch={() => onSearch(searchKeyword)}
        onChange={(e: any) => onChange(e.target.value)}
      />
      <Box flex={1} position="relative" marginTop="60px">
        <SearchKeywordComp searchKeywordList={searchKeywordList} onSearch={onSearch} />
      </Box>
    </Box>
  );
};

export default SearchPage;
