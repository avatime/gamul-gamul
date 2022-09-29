import { Box, Stack } from "@mui/material";
import React, { FC, useState } from "react";
import { ButtonFill } from "./button/ButtonFill";
import { IngredientItem } from "./IngredientItem";
import Typography from "@mui/material/Typography";
import Input from "@mui/material/Input";
import { IngredientInfo } from "../apis/responses/ingredientInfo";
import { LimitPriceNoticeInfo } from "../apis/responses/limitPriceNoticeInfo";

interface IProps {
  onClickRegister: (limitPriceNoticeInfo: LimitPriceNoticeInfo) => void;
  ingredientInfo: IngredientInfo | undefined;
  limitPriceNoticeInfo: LimitPriceNoticeInfo;
}

const boxStyle = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "300px",
  height: "400px",
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
  borderRadius: "20px",
  textAlign: "center",
};

export const AlarmRegisterModal: FC<IProps> = ({
  onClickRegister,
  ingredientInfo,
  limitPriceNoticeInfo,
}) => {
  const [upperLimitPrice, setUpperLimitPrice] = useState<number>(
    limitPriceNoticeInfo.upper_limit_price
  );
  const [lowerLimitPrice, setLowerLimitPrice] = useState<number>(
    limitPriceNoticeInfo.lower_limit_price
  );

  return (
    <Box sx={boxStyle} onClick={(e: any) => e.stopPropagation()}>
      {ingredientInfo && (
        <IngredientItem
          direction={"column"}
          ingredientInfo={ingredientInfo}
          onClickItem={(id: number) => {}}
        />
      )}
      <Stack direction="row" sx={{ justifyContent: "center" }}>
        <Stack direction="column" sx={{ marginRight: "40px" }}>
          <Typography sx={{ fontWeight: "bold", color: "#9F1239" }}>상한가</Typography>
          <Stack direction="row">
            <Input
              type="number"
              color="success"
              sx={{ width: "80px" }}
              onChange={(e: any) => setUpperLimitPrice(e.target.value.replace(/^0+/, ''))}
              value={upperLimitPrice}
            />
            <Typography sx={{ fontWeight: "bold", color: "#A1A1AA", paddingTop: "4px" }}>
              원
            </Typography>
          </Stack>
        </Stack>
        <Stack direction="column" sx={{ marginleft: "40px" }}>
          <Typography sx={{ fontWeight: "bold", color: "#3730A3" }}>하한가</Typography>
          <Stack direction="row">
            <Input
              type="number"
              color="success"
              sx={{ width: "80px" }}
              onChange={(e: any) => setLowerLimitPrice(e.target.value.replace(/^0+/, ''))}
              value={lowerLimitPrice}
            />
            <Typography sx={{ fontWeight: "bold", color: "#A1A1AA", paddingTop: "4px" }}>
              원
            </Typography>
          </Stack>
        </Stack>
      </Stack>
      <Box p={3} />
      <Box>
        <ButtonFill
          text="등록"
          height="50px"
          width="220px"
          maxWidth=""
          onClick={() =>
            onClickRegister({
              ingredient_id: limitPriceNoticeInfo.ingredient_id,
              upper_limit_price: upperLimitPrice,
              lower_limit_price: lowerLimitPrice,
            })
          }
          fontSize={""}
          disabled={false}
        />
      </Box>
    </Box>
  );
};
