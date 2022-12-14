import React, { FC, useEffect, useState } from "react";
import dynamic from "next/dynamic";
import { PriceTransitionInfo } from "../apis/responses/priceTransitionInfo";
import moment from "moment";

type Type = "line" | "bar"

interface IProps {
  priceTransitionInfo: PriceTransitionInfo;
  inputWidth: any;
  inputHeight: number;
  type: Type;
  myRecipe?: boolean;
}

moment.locale("ko");

const IngredientPriceGraph: FC<IProps> = ({ priceTransitionInfo, inputWidth, inputHeight, type, myRecipe }) => {
  const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });
  const Chart2 = dynamic(() => import("react-apexcharts"), { ssr: false });
  const Chart3 = dynamic(() => import("react-apexcharts"), { ssr: false });
  const Chart4 = dynamic(() => import("react-apexcharts"), { ssr: false });

  const retailMax = Math.max(...priceTransitionInfo.retailsales.daily.map((v) => v.price));
  const wholeMax = Math.max(...priceTransitionInfo.wholesales.daily.map((v) => v.price));
  const max = Math.floor(Math.max(retailMax, wholeMax) * 1.1 / 100) * 100 < 1000 ? 1000 : Math.floor(Math.max(retailMax, wholeMax) * 1.1 / 100) * 100;
  const max2 = Math.floor(retailMax * 1.1 / 100 ) * 100 < 1000 ? 1000 : Math.floor(retailMax * 1.1 / 100 ) * 100;

  const retailMin = Math.min(...priceTransitionInfo.retailsales.daily.map((v) => v.price));
  const wholeMin = Math.min(...priceTransitionInfo.wholesales.daily.map((v) => v.price));
  const min = Math.floor(Math.min(retailMin, wholeMin) * 0.9 / 100) * 100 < 1000 ? 0 : Math.floor(Math.min(retailMin, wholeMin) * 0.9 / 100) * 100;
  const min2 = Math.floor(retailMin * 0.9 / 100) * 100 < 1000 ? 0 : Math.floor(retailMin * 0.9 / 100) * 100;

  const retailStatus = priceTransitionInfo.wholesales.daily.length == 0 && priceTransitionInfo.retailsales.daily.length != 0 ? true : false;
  const wholeStatus = priceTransitionInfo.retailsales.daily.length == 0 && priceTransitionInfo.wholesales.daily.length != 0 ? true : false;
  const allStatus = priceTransitionInfo.wholesales.daily.length == 0 && priceTransitionInfo.retailsales.daily.length == 0 ? true : false;

  var quantity = "";
  var unit = "";
  if (!myRecipe && !allStatus) {
  quantity = String(priceTransitionInfo?.retailsales.daily.length != 0 ? priceTransitionInfo.retailsales.daily[0].quantity : priceTransitionInfo.wholesales.daily[0].quantity);
  unit = String(priceTransitionInfo?.retailsales.daily.length != 0 ? priceTransitionInfo.retailsales.daily[0].unit : priceTransitionInfo.wholesales.daily[0].unit);
  } 

  return (
    <div>
      {!retailStatus && !wholeStatus && !allStatus && type === "line" && <Chart
        height={inputHeight}
        width={inputWidth}
        series={[
          {
            name: "??????",
            data: priceTransitionInfo.retailsales.daily.map((v) => v.price),
          },
          {
            name: "??????",
            data: priceTransitionInfo.wholesales.daily.map((v) => v.price),
          },
        ]}
        options={{
          chart: {
            type: "line",
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
            text: "????????? ????????? ?????? ??????",
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
            categories: priceTransitionInfo.retailsales.daily.length > 0 ? priceTransitionInfo.retailsales.daily.map((v) =>
              moment(v.date).format("MM.DD")
            ) : priceTransitionInfo.wholesales.daily.map((v) =>
            moment(v.date).format("MM.DD")
          ),
            title: {
              text: "??????",
            },
          },
          yaxis: {
            title: {
              text: !!!myRecipe ? `??????(${quantity}${unit})` : "??????(???)",
            },
            min: min,
            max: max,
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
      }
      {type === "bar" && <Chart2
        height={inputHeight}
        width={inputWidth}
        type="bar"
        series={[
          {
            name: "??????",
            data: priceTransitionInfo.retailsales.daily.map((v) => v.price),
          },
        ]}
        options={{
          chart: {
            type: "area",
            toolbar: {
              show: false,
            },
          },
          plotOptions: {
            bar: {
              horizontal: false,
              columnWidth: '55%',
            },
          },
          dataLabels: {
            enabled: false,
          },
          stroke: {
            show: true,
            width: 2,
            colors: ['transparent']
          },          
          title: {
            text: "?????? ??????",
            align: "left",
          },
          markers: {
            size: 1,
          },
          xaxis: {
            categories: priceTransitionInfo.retailsales.daily.map((v) =>
              moment(v.date).format("MM.DD")
            ),
          },
          yaxis: {
            min: min2,
            max: max2,
          },
          legend: {
            position: "top",
            horizontalAlign: "right",
            floating: true,
            offsetY: -20,
            offsetX: -5,
          },  
          fill: {
            opacity: 1
          },
        }}
      />
      }
      {retailStatus && !wholeStatus && type === "line" && <Chart3
        height={inputHeight}
        width={inputWidth}
        series={[
          {
            name: "??????",
            data: priceTransitionInfo.retailsales.daily.map((v) => v.price),
          },
        ]}
        options={{
          chart: {
            type: "line",
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
            text: "?????? ????????? ?????? ??????",
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
            categories: priceTransitionInfo.retailsales.daily.length > 0 ? priceTransitionInfo.retailsales.daily.map((v) =>
              moment(v.date).format("MM.DD")
            ) : priceTransitionInfo.wholesales.daily.map((v) =>
            moment(v.date).format("MM.DD")
          ),
            title: {
              text: "??????",
            },
          },
          yaxis: {
            title: {
              text: !!!myRecipe ? `??????(${quantity}${unit})` : "??????(???)",
            },
            min: min,
            max: max,
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
      }
      {wholeStatus && !retailStatus && type === "line" && <Chart4
        height={inputHeight}
        width={inputWidth}
        series={[
          {
            name: "??????",
            data: priceTransitionInfo.wholesales.daily.map((v) => v.price),
          },
        ]}
        options={{
          chart: {
            type: "line",
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
            text: "?????? ????????? ?????? ??????",
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
            categories: priceTransitionInfo.retailsales.daily.length > 0 ? priceTransitionInfo.retailsales.daily.map((v) =>
              moment(v.date).format("MM.DD")
            ) : priceTransitionInfo.wholesales.daily.map((v) =>
            moment(v.date).format("MM.DD")
          ),
            title: {
              text: "??????",
            },
          },
          yaxis: {
            title: {
              text: !!!myRecipe ? `??????(${quantity}${unit})` : "??????(???)",
            },
            min: min,
            max: max,
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
      }
      </div>
  );
};

export default IngredientPriceGraph;
