import { IngredientInfo } from "./ingredientInfo";
import { OnlineMartInfo } from "./onlineMartInfo";
import { PriceTransitionInfo } from "./priceTransitionInfo";

export interface IngredientDetailInfo {
  ingredient_info: IngredientInfo;
  price_transition_info: PriceTransitionInfo;
  online_mart_info: OnlineMartInfo[];
}
