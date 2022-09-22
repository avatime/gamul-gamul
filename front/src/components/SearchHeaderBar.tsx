import { Box, IconButton } from "@mui/material";
import React, { FC } from "react";
import CloseIcon from "@mui/icons-material/Close";
import styles from "../../styles/SearchHeaderBar.module.css";
import SearchIcon from "@mui/icons-material/Search";
import { DebounceInput } from "react-debounce-input";

interface IProps {
  searchKeyword: string;
  onChange: (e: any) => void;
  onClickBack: () => void;
}

export const SearchHeaderBar: FC<IProps> = ({ searchKeyword, onChange, onClickBack }) => {
  return (
    <Box height="60px" display="flex" justifyContent="center" paddingX="15px">
      <Box
        flex={1}
        height="40px"
        borderRadius="20px"
        bgcolor="white"
        display="flex"
        alignItems="center"
        pl={0.5}
        pr={0.5}
        marginTop="10px"
      >
        <SearchIcon color="success" style={{ width: "20px", height: "20px", margin: "8px" }} />
       
        <DebounceInput
          className={styles.input}
          forceNotifyByEnter={true}
          forceNotifyOnBlur={true}
          value={searchKeyword}
          onChange={onChange}
          debounceTimeout={300}
        />
      </Box>
      <IconButton onClick={onClickBack} style={{ height: "40px", marginTop: "10px" }}>
        <CloseIcon />
      </IconButton>
    </Box>
  );
};
