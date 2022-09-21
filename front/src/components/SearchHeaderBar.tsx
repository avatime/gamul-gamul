import { Box, IconButton } from "@mui/material";
import React, { FC } from "react";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { useRouter } from "next/router";
import styles from "../../styles/SearchHeaderBar.module.css";
import SearchIcon from "@mui/icons-material/Search";

interface IProps {
  searchKeyword: string;
  onSearch?: () => void;
  onChange?: (e: any) => void;
  onClickInput?: () => void;
}

export const SearchHeaderBar: FC<IProps> = ({
  searchKeyword,
  onSearch,
  onChange,
  onClickInput,
}) => {
  const router = useRouter();

  const onEnter = (e: any) => {
    if (e.key === "Enter") {
      e.preventDefault();
      onSearch?.();
    }
  };
  return (
    <Box
      className="page-background"
      width="100vw"
      height="60px"
      position="fixed"
      display="flex"
      justifyContent="center"
      paddingX="15px"
      paddingTop="10px"
      zIndex="5"
    >
      <Box
        width="100vw"
        height="40px"
        borderRadius="20px"
        bgcolor="white"
        display="flex"
        alignItems="center"
        pl={0.5}
        pr={0.5}
      >
        <IconButton style={{ color: "#A1A1AA" }} onClick={() => router.push("/")}>
          <ArrowBackIcon style={{ width: "20px", height: "20px" }} />
        </IconButton>
        <input
          className={styles.input}
          type="text"
          value={searchKeyword}
          onChange={onChange}
          autoFocus={typeof onSearch !== "undefined"}
          readOnly={typeof onSearch === "undefined"}
          onKeyDown={onEnter}
          onClick={onClickInput}
        />
        <IconButton onClick={onSearch}>
          <SearchIcon color="success" style={{ width: "20px", height: "20px" }} />
        </IconButton>
      </Box>
    </Box>
  );
};
