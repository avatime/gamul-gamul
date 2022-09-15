import React, { FC, useEffect, useState } from "react";
import dynamic from 'next/dynamic'
import { PriceTransitionInfo } from '../../apis/responses/priceTransitionInfo';

interface IProps {
	priceTransitionInfo: PriceTransitionInfo;
}

const IngredientPriceGraph: FC<IProps> = ({priceTransitionInfo}) => {
	const Chart = dynamic(() => import('react-apexcharts'), { ssr: false });

  return (
	<div>
  		<Chart 
			type="line"
			height={350}
			width={700}
			
			series = { [
				{
				  name: "소매",
				  data: priceTransitionInfo.retailsales.daily.map((v) => v.price)
				},
				{
				  name: "도매",
				  data: priceTransitionInfo.wholesales.daily.map((v) => v.price)
				}
			  ] }

			  options = {{
				chart: {
				  height: 350,
				  type: 'line',
				  dropShadow: {
					enabled: true,
					color: '#000',
					top: 18,
					left: 7,
					blur: 10,
					opacity: 0.2
				  },
				  toolbar: {
					show: false
				  }
				},
				colors: ['#77B6EA', '#545454'],
				dataLabels: {
				  enabled: true,
				},
				stroke: {
				  curve: 'smooth'
				},
				title: {
				  text: 'Average High & Low Temperature',
				  align: 'left'
				},
				grid: {
				  borderColor: '#e7e7e7',
				  row: {
					colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
					opacity: 0.5
				  },
				},
				markers: {
				  size: 1
				},
				xaxis: {
				  categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
				  title: {
					text: 'Month'
				  }
				},
				yaxis: {
				  title: {
					text: 'Temperature'
				  },
				  min: 0,
				  max: 1500
				},
				legend: {
				  position: 'top',
				  horizontalAlign: 'right',
				  floating: true,
				  offsetY: -25,
				  offsetX: -5
				}
			}}
			
		/>
		
		{/* <Chart 
        	type="line" 
            series={ [
                { name: "오늘의 기온",
                  data: [19, 26, 20, 9],
                },
                { name: "내일의 기온",
                  data: [30, 26, 34, 10],
                },
                ]} 
            options={{    
                chart : {
                    height: 500,
                    width: 500,                    
                }, 
            }}></Chart> */}
	</div>
)
};

export default IngredientPriceGraph;
