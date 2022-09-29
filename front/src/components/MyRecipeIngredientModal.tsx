import { Box, Stack } from "@mui/material";
import React, { FC, useState } from "react";
import { ButtonFill } from "./button/ButtonFill";
import { IngredientItem } from "./IngredientItem";
import Typography from "@mui/material/Typography";
import Input from "@mui/material/Input";
import { IngredientInfo } from "../apis/responses/ingredientInfo";
import { MyRecipeIngredientInfo } from "../apis/responses/myRecipeIngredientInfo";

interface IProps {
  onClickRegister: (myRecipeIngredientInfo: MyRecipeIngredientInfo) => void;
  ingredientInfo: IngredientInfo;
  myRecipeIngredientInfo: MyRecipeIngredientInfo;
}

const boxStyle = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "300px",
  height: "350px",
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
  borderRadius: "20px",
  textAlign: "center",
};

export const MyRecipeIngredientModal: FC<IProps> = ({
  onClickRegister,
  ingredientInfo,
  myRecipeIngredientInfo,
}) => {
  const [quantity, setQuantity] = useState<number>(myRecipeIngredientInfo.quantity);
  return (
    <Box
      sx={boxStyle}
      display="flex"
      flexDirection="column"
      onClick={(e: any) => e.stopPropagation()}
    >
      <Box flex={1}>
        <IngredientItem
          direction={"column"}
          ingredientInfo={ingredientInfo}
          onClickItem={(id: number) => {}}
        />
      </Box>
      <Stack direction="row" sx={{ justifyContent: "center" }}>
        <Stack direction="row">
          <Input
            type="number"
            color="success"
            sx={{ width: "80px" }}
            onChange={(e: any) => setQuantity(e.target.value.replace(/^0+/, ''))}
            value={quantity}
          />
          <Typography sx={{ fontWeight: "bold", color: "#A1A1AA", paddingTop: "4px" }}>
            {ingredientInfo.unit}
          </Typography>
        </Stack>
      </Stack>
      <Box p={1} />
      <Box>
        <ButtonFill
          text="등록"
          height="50px"
          width="220px"
          maxWidth=""
          onClick={() =>
            onClickRegister({
              ingredient_id: ingredientInfo.ingredient_id,
              quantity,
            })
          }
          fontSize={""}
          disabled={false}
        />
      </Box>
    </Box>
  );
};
