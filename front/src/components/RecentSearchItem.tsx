import { IconButton, ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { RecentSearch } from "../utils/localStorageUtil";
import RestaurantIcon from "@mui/icons-material/Restaurant";
import Image from "next/image";
import CancelIcon from "@mui/icons-material/Cancel";

interface IProps {
  recentSearch: RecentSearch;
  onCloseSearch: () => void;
  onClickDelete: (recentSearch: RecentSearch) => void;
}

export const RecentSearchItem: FC<IProps> = ({ recentSearch, onCloseSearch, onClickDelete }) => {
  const router = useRouter();
  const onClick = () => {
    onCloseSearch();
    if (recentSearch.recentType === "ingredient") {
      router.push(`/ingredient-info/${recentSearch.id}`);
    } else {
      router.push(`/recipe-info/${recentSearch.id}`);
    }
  };
  return (
    <ListItem
      disablePadding
      secondaryAction={
        <IconButton onClick={() => onClickDelete(recentSearch)}>
          <CancelIcon />
        </IconButton>
      }
    >
      <ListItemButton onClick={onClick}>
        <ListItemIcon>
          {recentSearch.recentType === "ingredient" ? (
            <Image
              width="24"
              height="24"
              alt={recentSearch.name}
              src={`/assets/ingredientsImg/${recentSearch.id}.jpg`}
              style={{ borderRadius: 20 }}
            />
          ) : (
            <RestaurantIcon />
          )}
        </ListItemIcon>
        <ListItemText primary={recentSearch.name} />
      </ListItemButton>
    </ListItem>
  );
};
