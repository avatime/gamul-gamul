import { Box, Button, Grid, IconButton } from "@mui/material";
import React, { FC, ReactNode, useMemo } from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";

const arrowStyles = {
  position: "absolute",
  zIndex: 2,
  top: "calc(50% - 25px)",
  width: 50,
  height: 50,
  cursor: "pointer",
  color: "#bdbdbd",
};

const indicatorStyles = {
  background: "#bdbdbd",
  width: 8,
  height: 8,
  display: "inline-block",
  margin: "0 4px",
  borderRadius: 8,
};

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
    <Carousel
      showThumbs={false}
      autoPlay={false}
      showStatus={false}
      infiniteLoop={true}
      renderArrowNext={(onClickHandler, hasNext, label) =>
        hasNext && (
          <IconButton onClick={onClickHandler} title={label} sx={{ ...arrowStyles, right: 0 }}>
            <ChevronRightIcon sx={{ width: 40, height: 40 }} />
          </IconButton>
        )
      }
      renderArrowPrev={(onClickHandler, hasPrev, label) =>
        hasPrev && (
          <IconButton onClick={onClickHandler} title={label} sx={{ ...arrowStyles, left: 0 }}>
            <ChevronLeftIcon sx={{ width: 40, height: 40 }} />
          </IconButton>
        )
      }
      renderIndicator={(onClickHandler, isSelected, index, label) => {
        if (isSelected) {
          return (
            <li
              style={{ ...indicatorStyles, background: "#4d4d4d" }}
              aria-label={`Selected: ${label} ${index + 1}`}
              title={`Selected: ${label} ${index + 1}`}
            />
          );
        }
        return (
          <li
            style={indicatorStyles}
            onClick={onClickHandler}
            onKeyDown={onClickHandler}
            value={index}
            key={index}
            role="button"
            tabIndex={0}
            title={`${label} ${index + 1}`}
            aria-label={`${label} ${index + 1}`}
          />
        );
      }}
    >
      {dataList.map((arr2d, i) => (
        <Box key={i} pb={3}>
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
