import { IngredientInfo } from "./IngredientInfo";
import { OnlineMartInfo } from "./OnlineMartInfo";
import { PriceInfo } from "./PriceInfo";

export interface IngredientDetailInfo {
  ingredient_info: IngredientInfo;
  views: number; // 조회수;
  price_info: PriceInfo;
  online_mart_info: OnlineMartInfo;
}
