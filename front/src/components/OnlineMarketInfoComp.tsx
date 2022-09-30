import { Stack, Table, TableCell, TableRow } from "@mui/material";
import React, { FC } from "react";
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
  width: string;
  iconSize: string;
}

/**
 * @author
 * @function @OnlineMarketInfoComp
 **/

export const OnlineMarketInfoComp: FC<IProps> = ({ onlineMartInfo, width, iconSize }) => {
  return (
    <div>
      <CardContainer title={"온라인"} style={{ width }}>
        <Table>
          {onlineMartInfo.map((row, idx) => (
            <TableRow
              key={idx}
              onClick={() => {
                window.open(row.url);
              }}
              sx={{ cursor: "pointer" }}
              className={styles.hover}
            >
              <TableCell>{row.name}</TableCell>
              <TableCell>
                <Stack direction="row" sx={{ alignItems: "center" }}>
                  {/* <Lottie
                    animationData={cart}
                    loop={true}
                    autoPlay={false}
                    style={{ width: iconSize, height: iconSize }}
                  /> */}
                  {row.mall_name}
                </Stack>
              </TableCell>
              <TableCell>{row.price.toLocaleString()}원</TableCell>
            </TableRow>
          ))}
        </Table>
      </CardContainer>
    </div>
  );
};
