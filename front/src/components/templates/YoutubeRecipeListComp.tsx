import { Grid, Stack } from "@mui/material";
import React, { FC } from "react";
import { YoutubeInfo } from "../../apis/responses/youtubeInfo";
import { CardContainer } from "../CardContainer";
import { YoutubeRecipeItem } from "../YoutubeRecipeItem";

interface IProps {
  youtubeInfoList: YoutubeInfo[];
  title?: string;
  gridSize?: number;
}

export const YoutubeRecipeListComp: FC<IProps> = ({
  youtubeInfoList,
  title = "요리법 유튜브",
  gridSize = 1,
}) => {
  return (
    <CardContainer title={title}>
      <Grid container>
        {youtubeInfoList.map((v, i) => (
          <Grid item key={i} xs={12 / gridSize}>
            <YoutubeRecipeItem youtubeInfo={v} />
          </Grid>
        ))}
      </Grid>
    </CardContainer>
  );
};
