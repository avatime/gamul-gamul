import { Box, Button } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { useState } from "react";
import { SearchHeaderBar } from "../src/components/SearchHeaderBar";
import { SearchKeywordComp } from "../src/components/templates/SearchKeywordComp";
import { useSearchLocalStorage } from "../src/hooks/useSearchLocalStorage";
import { saveSearchLocalStorage } from "../src/utils/localStorageUtil";

interface IProps {}

const SearchPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const [searchKeyword, setSearchKeyword] = useState("");
  const onSearch = (keyword: string) => {
    saveSearchLocalStorage<string>("keyword", keyword);
    router.push({
      pathname: "/search",
      query: {
        keyword: keyword,
      },
    });
  };
  const onChange = (keyword: string) => {
    setSearchKeyword(keyword);
  };

  const searchKeywordList = useSearchLocalStorage<string>("keyword", (item) =>
    item.includes(searchKeyword)
  );

  return (
    <Box className="page-background" display="flex" flexDirection="column">
      <SearchHeaderBar
        searchKeyword={searchKeyword}
        onSearch={() => onSearch(searchKeyword)}
        onChange={(e: any) => onChange(e.target.value)}
      />

      <Box flex={1} position="relative">
        <SearchKeywordComp
          searchKeywordList={searchKeywordList}
          onSearch={onSearch}
        />
      </Box>
    </Box>
  );
};

export default SearchPage;
