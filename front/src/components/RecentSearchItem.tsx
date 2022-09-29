import { ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { RecentSearch } from "../utils/localStorageUtil";
import RestaurantIcon from "@mui/icons-material/Restaurant";
import Image from "next/image";

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
