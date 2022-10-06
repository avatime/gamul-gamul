import { Box, List } from "@mui/material";
import React, { FC, useEffect, useState } from "react";
import {
  deleteRecentSearchLocalStorage,
  getRecentSearchLocalStorage,
  RecentSearch,
} from "../../utils/localStorageUtil";
import { CardContainer } from "../CardContainer";
import { RecentSearchItem } from "../RecentSearchItem";

interface IProps {
  onCloseSearch: () => void;
}

export const RecentSearchComp: FC<IProps> = ({ onCloseSearch }) => {
  const [recentList, setRecentList] = useState<RecentSearch[]>([]);
  useEffect(() => {
    setRecentList(getRecentSearchLocalStorage<RecentSearch>());
  }, []);

  const onClickDelete = (recentSearch: RecentSearch) => {
    deleteRecentSearchLocalStorage(recentSearch.recentType, recentSearch.id);
    setRecentList((prev) => {
      prev.splice(
        prev.findIndex((v) => v.recentType === recentSearch.recentType && v.id === recentSearch.id),
        1
      );
      return [...prev];
    });
  };

  return (
    <CardContainer
      title="최근"
      style={{ position: "absolute", top: 0, left: 0, right: 0, bottom: 0, overflow: "auto" }}
    >
      <Box p={1}>
        {recentList.length !== 0 ? (
          <List>
            {recentList.map((v, i) => (
              <RecentSearchItem
                key={i}
                recentSearch={v}
                onCloseSearch={onCloseSearch}
                onClickDelete={onClickDelete}
              />
            ))}
          </List>
        ) : (
          <p style={{ marginTop: 10 }}>최근 검색 목록이 없습니다.</p>
        )}
      </Box>
    </CardContainer>
  );
};
