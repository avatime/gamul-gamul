import { ListItem, ListItemAvatar, ListItemText } from "@mui/material";
import { grey } from "@mui/material/colors";
import Image from "next/image";
import React, { FC } from "react";
import { NotificationInfo } from "../apis/responses/notificationInfo";
import { useRouter } from "next/router";

interface IProps {
  notificationInfo: NotificationInfo;
}

export const NotificationItem: FC<IProps> = ({ notificationInfo }) => {
  const router = useRouter();
  const onClick = () => {
    router.push(`/ingredient-info/${notificationInfo.ingredient_id}`);
  };
  return (
    <ListItem
      alignItems="flex-start"
      style={{ backgroundColor: grey[100], padding: "15px" }}
      onClick={onClick}
    >
      <ListItemAvatar>
        <Image
          width={42}
          height={42}
          alt={String(notificationInfo.ingredient_id)}
          src={`/assets/ingredientsImg/${notificationInfo.ingredient_id}.jpg`}
          style={{ borderRadius: 42 }}
        />
      </ListItemAvatar>
      <ListItemText primary={notificationInfo.title} secondary={notificationInfo.message} />
    </ListItem>
  );
};
