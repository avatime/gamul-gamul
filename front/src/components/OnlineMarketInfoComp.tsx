import { Stack, Table, TableCell, TableRow } from "@mui/material";
import React, { FC, useState } from "react";
import { CardContainer } from "./CardContainer";
import { OnlineMartInfo } from "../../src/apis/responses/onlineMartInfo";
import Link from "next/link";
import Image from "next/image";
import router from "next/router";
import path from "path";
import styles from "../../styles/OnlineMarketInfoComp.module.css";
import Lottie from "lottie-react";
import cart from "../../public/assets/cart2.json";

interface IProps {
  onlineMartInfo: OnlineMartInfo[];
  iconSize: string;
}

/**
 * @author
 * @function @OnlineMarketInfoComp
 **/

export const OnlineMarketInfoComp: FC<IProps> = ({ onlineMartInfo, iconSize }) => {
  
  return (
    <div>
      <CardContainer title={"온라인"}>
        <Table sx={{ tableLayout: "fixed" }}>

          {onlineMartInfo.map((row, idx) => (
            <TableRow
              key={idx}
              onClick={() => {
                window.open(row.url);
              }}
              sx={{ cursor: "pointer" }}
              className={styles.hover}
              
            >
              <TableCell
                sx={{
                  textOverflow: "ellipsis",
                  whiteSpace: "nowrap",
                  width: "50%",
                  overflow: "hidden",
                }}
              >{row.name.replaceAll("<b>", "").replaceAll("</b>", "")}
               
              </TableCell>
             
              <TableCell sx={{ whiteSpace: "nowrap",width: "30%", textOverflow: "ellipsis", overflow: "hidden", }}>
                <Stack direction="row" sx={{ alignItems: "center"}}>
                  {row.mall_name}
                </Stack>
              </TableCell>
              <TableCell sx={{ alignItems: "right", whiteSpace: "nowrap" , width: "20%"}}>
                <Stack sx={{ display:"flex", justifyContent:"flex-end" }}>
                {row.price.toLocaleString()}원

                </Stack>
    
              </TableCell>
            </TableRow>
          ))}
        </Table>
      </CardContainer>
    </div>
  );
};
