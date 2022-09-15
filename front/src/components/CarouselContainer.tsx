import { Box, Grid } from "@mui/material";
import React, { FC, ReactNode, useMemo } from "react";
import Carousel from "react-material-ui-carousel";

interface IProps {
  itemList: any[];
  rowSize: number;
  gridSize: number;
  getItemComponent: (item: any) => ReactNode;
}

export const CarouselContainer: FC<IProps> = ({
  itemList,
  rowSize,
  gridSize,
  getItemComponent,
}) => {
  const dataList = useMemo(() => {
    const result = [];
    const temp = [];
    const itemListCopied = itemList.slice();

    while (itemListCopied.length % (rowSize * gridSize) !== 0) {
      itemListCopied.push(null);
    }
    while (itemListCopied.length) {
      temp.push(itemListCopied.splice(0, gridSize));
    }
    while (temp.length) {
      result.push(temp.splice(0, rowSize));
    }
    return result;
  }, [gridSize, itemList, rowSize]);

  return (
    <Carousel autoPlay={false} animation="slide">
      {dataList.map((arr2d, i) => (
        <Box key={i}>
          {arr2d.map((arr1d, i) => (
            <Grid container key={i} justifyContent="space-around">
              {arr1d.map((item, i) => (
                <Grid item key={i} display="flex" justifyContent="center" xs={12 / gridSize}>
                  {getItemComponent(item)}
                </Grid>
              ))}
            </Grid>
          ))}
        </Box>
      ))}
    </Carousel>
  );
};
