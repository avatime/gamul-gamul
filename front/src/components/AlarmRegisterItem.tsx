import { Box, Button, Modal, Paper, Stack } from "@mui/material";
import React, { FC, MouseEventHandler } from "react";
import { ButtonFill } from "./button/ButtonFill";
import { Desktop } from "./Desktop";
import { IngredientItem } from "./IngredientItem";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import Typography from "@mui/material/Typography";
import Input from "@mui/material/Input";

interface IProps {}

/**
 * @author
 * @function @AlarmRegisterItem
 **/

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

export const AlarmRegisterItem: FC<IProps> = () => {
  const [isOpen, setIsOpen] = React.useState(false);
  const handleOpen = () => setIsOpen(true);
  const handleClose = () => setIsOpen(false);
  return (
    <div>
        <Button onClick={handleOpen}>Open modal</Button>
        <Modal
          open={isOpen}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={boxStyle}>
            <IngredientItem
              direction={"column"}
              onClickItem={function (id: number): void {
                throw new Error("Function not implemented.");
              } } ingredientInfo={null}            />
            <Stack direction="row" sx={{justifyContent:"center"}}>
              <Stack direction="column" sx={{marginRight:"40px"}}>
                <Typography sx={{ fontWeight: "bold", color: "#9F1239" }}>상한가</Typography>
                <Stack direction ="row">
                  <Input color="success" sx={{ width: "80px" }} />
                  <Typography sx={{ fontWeight: "bold", color: "#A1A1AA", paddingTop:"4px" }}>원</Typography>
                </Stack>
              </Stack>
              <Stack direction="column" sx={{ marginleft: "40px"}}>
                <Typography sx={{ fontWeight: "bold", color: "#3730A3" }}>하한가</Typography>
                <Stack direction="row">
                  <Input color="success" sx={{ width: "80px" }} />
                  <Typography sx={{ fontWeight: "bold", color: "#A1A1AA" ,paddingTop:"4px"}}>원</Typography>
                </Stack>
              </Stack>
            </Stack>
            <Box p={3}/>
            <Box >
       
            <ButtonFill text={"알림 받기"} height={"50px"} width={"220px"} maxWidth={""} onClick={function (): void {
              throw new Error("Function not implemented.");
            } } />
           </Box>
          </Box>
        </Modal>
     
    </div>
  );
};
