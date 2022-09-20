import { Box, IconButton, ListItem, ListItemButton, ListItemText } from "@mui/material";
import React, { FC } from "react";
import CancelIcon from "@mui/icons-material/Cancel";

interface IProps {
  searchKeyword: string;
  onClickDelete: () => void;
  onClickItem: () => void;
}

export const SearchKeywordItem: FC<IProps> = ({ searchKeyword, onClickDelete, onClickItem }) => {
  return (
    <ListItem
      secondaryAction={
        <IconButton edge="end" onClick={onClickDelete}>
          <CancelIcon />
        </IconButton>
      }
      disablePadding
      
    >
      <ListItemButton onClick={onClickItem}>
        <ListItemText primary={searchKeyword} />
      </ListItemButton>
    </ListItem>
  );
};
