import React, { FC, useEffect, useState } from "react";
import dynamic from "next/dynamic";
import { PriceTransitionInfo } from "../apis/responses/priceTransitionInfo";
import moment from "moment";

interface IProps {
  priceTransitionInfo: PriceTransitionInfo;
  inputWidth: any;
  inputHeight: number;
  type: string;
}

moment.locale("ko");

const IngredientPriceGraph: FC<IProps> = ({ priceTransitionInfo, inputWidth, inputHeight, type }) => {
  const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

  return (
    <div>
      <Chart
        height={inputHeight}
        width={inputWidth}
        series={[
          {
            name: "소매",
            data: priceTransitionInfo.retailsales.daily.map((v) => v.price),
          },
          {
            name: "도매",
            data: priceTransitionInfo.wholesales.daily.map((v) => v.price),
          },
        ]}
        options={{
          chart: {
            type: type === "line" ? "line" : "bar",
            dropShadow: {
              enabled: true,
              color: "#000",
              top: 18,
              left: 7,
              blur: 10,
              opacity: 0.2,
            },
            toolbar: {
              show: false,
            },
          },
          colors: ["#4411AA", "#163ED9"],
          dataLabels: {
            enabled: true,
          },
          stroke: {
            curve: "smooth",
          },
          title: {
            text: "도소매 일자별 가격 변화",
            align: "left",
          },
          grid: {
            borderColor: "#e7e7e7",
            row: {
              colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
              opacity: 0.5,
            },
          },
          markers: {
            size: 1,
          },
          xaxis: {
            categories: priceTransitionInfo.retailsales.daily.map((v) =>
              moment(v.date).format("MM.D")
            ),
            title: {
              text: "일자",
            },
          },
          yaxis: {
            title: {
              text: `가격(${priceTransitionInfo.retailsales.daily[0].quantity}${priceTransitionInfo.retailsales.daily[0].unit})`,
            },
            min: 0,
            max: 1500,
          },
          legend: {
            position: "top",
            horizontalAlign: "right",
            floating: true,
            offsetY: -20,
            offsetX: -5,
          },
        }}
      />
    </div>
  );
};

export default IngredientPriceGraph;
