import React, { FC } from "react";
import { Box, List } from "@mui/material";
import { CardContainer } from "../CardContainer";
import { SearchKeywordItem } from "../SearchKeywordItem";
import { deleteSearchLocalStorage } from "../../utils/localStorageUtil";

interface IProps {
  searchKeywordList: string[];
  onSearch: (keyword: string) => void;
}

export const SearchKeywordComp: FC<IProps> = ({ searchKeywordList, onSearch }) => {
  const onDelete = (keyword: string) => {
    deleteSearchLocalStorage("keyword", keyword);
  };

  return (
    <CardContainer
      title="최근 검색어"
      style={{ position: "absolute", top: 0, left: 0, right: 0, bottom: 0 }}
    >
      <Box p={1}>
        {searchKeywordList ? (
          <List>
            {searchKeywordList.map((v, i) => (
              <SearchKeywordItem
                key={i}
                searchKeyword={v}
                onClickDelete={() => onDelete(v)}
                onClickItem={() => onSearch(v)}
              />
            ))}
          </List>
        ) : (
          <p style={{ marginTop: 10 }}>최근 검색어가 없습니다.</p>
        )}
      </Box>
    </CardContainer>
  );
};
