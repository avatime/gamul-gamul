import React, { FC } from "react";
import Input from "@mui/material/Input";
import { Box } from "@mui/material";
import TextField from '@mui/material/TextField';


interface IProps {
  PlaceHolder: string;
  Width : string;

}

/**
 * @author
 * @function @
 **/

export const InputBox: FC<IProps> = ({ PlaceHolder, Width}) => {
  
  return (
    <div>
      <Box
        component="form"
        
        noValidate
        autoComplete="off"
      >
        <Input color="success" placeholder={PlaceHolder} sx={{width:`${Width}`}}/>
        <TextField
          error
          id="standard-error-helper-text"
          label="Error"
          defaultValue="Hello World"
          helperText="Incorrect entry."
          variant="standard"
        />
      </Box>
    </div>
  );
};
