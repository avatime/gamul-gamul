import { ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { RecentSearch } from "../utils/localStorageUtil";
import EggIcon from "@mui/icons-material/Egg";
import RestaurantIcon from "@mui/icons-material/Restaurant";

interface IProps {
  recentSearch: RecentSearch;
}

export const RecentSearchItem: FC<IProps> = ({ recentSearch }) => {
  const router = useRouter();
  const onClick = () => {
    if (recentSearch.recentType === "ingredient") {
      router.push(`/ingredient-info/${recentSearch.id}`);
    } else {
      router.push(`/recipe-info/${recentSearch.id}`);
    }
  };
  return (
    <ListItem disablePadding>
      <ListItemButton onClick={onClick}>
        <ListItemIcon>
          {recentSearch.recentType === "ingredient" ? <EggIcon /> : <RestaurantIcon />}
        </ListItemIcon>
        <ListItemText primary={recentSearch.name} />
      </ListItemButton>
    </ListItem>
  );
};
